package cc.catface.iflytek.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import com.iflytek.aiui.AIUIAgent;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIListener;
import com.iflytek.aiui.AIUIMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.Nullable;

public class AIUIServer extends Service {
    private final String TAG = getClass().getName();

    private static final long AUTO_STOP_VOICE_NLP_TIME = 30000;     // 30秒后关闭语音识别功能，此后必须通过广播唤醒功能
    private int mAIUIState;             // AIUI目前的状态
    private String speechText;          // 即将朗读的文字
    private AIUIHandler mHandler;        // 用于自动停止识别
    private AIUIAgent mAIUIAgent;       // AIUI功能模块
    private RecognizeCallback callback; // 识别结果回调

    private AIUIListener mAIUIListener = new AIUIResultListener();  // AIUI状态监听，从中可获取识别和语义匹配数据

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new AIUIHandler(Looper.getMainLooper());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        startRecognizeNlp(); // 服务被绑定时，开始识别
        return new Binder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        startRecognizeNlp();// 被再次绑定服务时，开启识别
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecognizeNlp();// 再次开启服务时，开启识别
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopVoiceNlp();
        if (null != mHandler) mHandler = null;
    }

    /** 唤醒AIUI内部状态后进行收音识别语义 */
    private void startRecognizeNlp() {
        if (checkAIUIAgent()) {
            if (AIUIConstant.STATE_WORKING != this.mAIUIState) {    // 唤醒aiui内部状态方可收音
                mAIUIAgent.sendMessage(new AIUIMessage(AIUIConstant.CMD_WAKEUP, 0, 0, "", null));
            }

            String params = "sample_rate=16000,data_type=audio";
            mAIUIAgent.sendMessage(new AIUIMessage(AIUIConstant.CMD_START_RECORD, 0, 0, params, null));
            mHandler.sendEmptyMessage(mHandler.WAIT_FOR_STOP);
        }
    }

    /** 创建代理 */
    private boolean checkAIUIAgent() {
        if (null == mAIUIAgent) {
            mAIUIAgent = AIUIAgent.createAgent(this, getAIUIParams(), mAIUIListener);
            mAIUIAgent.sendMessage(new AIUIMessage(AIUIConstant.CMD_START, 0, 0, null, null));
        }

        return null != mAIUIAgent;
    }

    /** AIUI是连续收音返回语义结果的 */
    private void stopVoiceNlp() {
        String params = "sample_rate=16000,data_type=audio";
        mAIUIAgent.sendMessage(new AIUIMessage(AIUIConstant.CMD_STOP_RECORD, 0, 0, params, null));
    }

    private String getAIUIParams() {
        String params = "";
        InputStream is = null;
        AssetManager assetManager = getResources().getAssets();
        try {
            is = assetManager.open("cfg/aiui_phone.cfg");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            params = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return params;
    }


    public class Binder extends android.os.Binder {
        public AIUIServer getServer() {
            return AIUIServer.this;
        }
    }

    /**
     * 回调给Activity用于识别结果的界面展示
     */
    public interface RecognizeCallback {
        void result(String content, String understanding);

        void state(String state);
    }

    public void setCallback(RecognizeCallback callback) {
        this.callback = callback;
    }

    private void setState(String state) {
        if (callback != null)
            callback.state(state);
    }

    /**
     * 通过Handler来实现语音识别的自动关闭
     */
    private class AIUIHandler extends Handler {
        final int WAIT_FOR_STOP = 0x1000;
        final int STOP_VOICE_NLP = 0x1010;

        AIUIHandler(Looper mainLooper) {
            super(mainLooper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WAIT_FOR_STOP:
                    removeMessages(STOP_VOICE_NLP);
                    sendEmptyMessageDelayed(STOP_VOICE_NLP, AUTO_STOP_VOICE_NLP_TIME);
                    break;
                case STOP_VOICE_NLP:
                    stopVoiceNlp();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }

    /**
     * 语音识别和语义数据监听器
     */
    private class AIUIResultListener implements AIUIListener {

        @Override
        public void onEvent(AIUIEvent event) {
            switch (event.eventType) {
                case AIUIConstant.EVENT_WAKEUP:
                    setState("[listener-->EVENT_WAKEUP]");
                    break;

                case AIUIConstant.EVENT_RESULT: {
                    setState("[listener-->EVENT_RESULT]");
                    try {
                        String info = event.info;
                        setState("[listener-->EVENT_RESULT]-->event.info：" + info);
                        JSONObject bizParamJson = new JSONObject(info);
                        JSONObject data = bizParamJson.getJSONArray("data").getJSONObject(0);
                        JSONObject params = data.getJSONObject("params");
                        JSONObject content = data.getJSONArray("content").getJSONObject(0);
                        if (content.has("cnt_id")) {
                            String cnt_id = content.getString("cnt_id");
                            JSONObject cntJson = new JSONObject(new String(event.data.getByteArray(cnt_id), "utf-8"));

                            setState("[listener-->EVENT_RESULT]-->event.info/cnt_id-->" + cntJson.toString());
                            String sub = params.optString("sub");
                            if ("nlp".equals(sub)) {
                                String intentStr = cntJson.optString("intent");
                                setState("[listener-->EVENT_RESULT]-->event.info/cnt_id/intent-->" + intentStr);
                                if ("{}".equals(intentStr)) return;
                                speechText = "";
                                try {
                                    JSONObject jsonObject = new JSONObject(intentStr);
                                    JSONArray semanticJson = jsonObject.optJSONArray("semantic");
                                    if (null == semanticJson || semanticJson.length() < 1) {
                                        speechText = "这个问题没有回答结果";
                                    } else {
                                        speechText = new JSONObject(semanticJson.get(0).toString()).optString("intent");
                                    }
                                    if (null != callback)
                                        callback.result(jsonObject.optString("service"), speechText);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }

                }
                break;

                case AIUIConstant.EVENT_ERROR:
                    setState("[listener-->EVENT_ERROR]-->" + event.arg1 + " || " + event.info);
                    break;

                case AIUIConstant.EVENT_VAD:
                    if (AIUIConstant.VAD_BOS == event.arg1) {
                        setState("[listener-->EVENT_VAD]-->找到vad-bos...");
                    } else if (AIUIConstant.VAD_EOS == event.arg1) {
                        setState("[listener-->EVENT_VAD]-->找到vad-eos...");
                    } else {
                        setState("[listener-->EVENT_VAD]-->" + event.arg2);
                    }

                    break;

                case AIUIConstant.EVENT_START_RECORD:
                    setState("[listener-->EVENT_START_RECORD]");
                    break;

                case AIUIConstant.EVENT_STOP_RECORD:
                    setState("[listener-->EVENT_STOP_RECORD]");
                    break;

                case AIUIConstant.EVENT_STATE:
                    mAIUIState = event.arg1;
                    if (AIUIConstant.STATE_IDLE == mAIUIState) {
                        setState("[listener-->EVENT_STATE]-->STATE_IDLE");
                    } else if (AIUIConstant.STATE_READY == mAIUIState) {
                        setState("[listener-->EVENT_STATE]-->STATE_READY");
                    } else if (AIUIConstant.STATE_WORKING == mAIUIState) {
                        setState("[listener-->EVENT_STATE]-->STATE_WORKING");
                    }
                    break;

                case AIUIConstant.EVENT_CMD_RETURN:
                    if (AIUIConstant.CMD_UPLOAD_LEXICON == event.arg1) {
                        setState("[listener-->EVENT_CMD_RETURN]-->上传" + (0 == event.arg2 ? "成功" : "失败"));
                    }
                    break;
            }
        }
    }
}

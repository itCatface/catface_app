package cc.catface.iflytek;

import android.app.Application;
import android.os.StrictMode;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class IflytekApp extends Application {

    @Override public void onCreate() {
        super.onCreate();

        /* android 7.0系统解决拍照的问题 */
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        /* iflytek语音能力注册 */
        String param = ("appid=" + getString(R.string.app_id)) + "," + SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_MSC;
        SpeechUtility.createUtility(this, param);
    }
}

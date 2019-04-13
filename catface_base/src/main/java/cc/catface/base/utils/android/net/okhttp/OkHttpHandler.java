package cc.catface.base.utils.android.net.okhttp;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

public class OkHttpHandler extends Handler {

    static final int MESSAGE_REQUEST_SUCCESS = 1;
    static final int MESSAGE_REQUEST_FAILURE = 2;

    @Override
    public void handleMessage(Message message) {
        OkHttpRequestCallback okHttpRequestCallback;
        String result;
        switch (message.what) {
            case MESSAGE_REQUEST_SUCCESS:
                okHttpRequestCallback = (OkHttpRequestCallback) message.obj;
                result = message.getData().getString("result");
                if (null != okHttpRequestCallback) {
                    okHttpRequestCallback.onSuccess(result);
                }
                break;
            case MESSAGE_REQUEST_FAILURE:
                okHttpRequestCallback = (OkHttpRequestCallback) message.obj;
                result = message.getData().getString("result");
                if (null != okHttpRequestCallback && !TextUtils.isEmpty(result)) {
                    okHttpRequestCallback.onFailure(result);
                }
                break;
            default:
                break;
        }
    }
}

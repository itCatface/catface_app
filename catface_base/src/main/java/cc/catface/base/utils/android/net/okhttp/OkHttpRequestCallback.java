package cc.catface.base.utils.android.net.okhttp;

public interface OkHttpRequestCallback {
    void onSuccess(String result);
    void onFailure(String error);
}

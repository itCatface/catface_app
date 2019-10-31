package cc.catface.start.login.model;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface LoginModel {

    interface Callback {
        void onSuccess(String result);

        void onError(String error);
    }

    void request(Callback callback);
}

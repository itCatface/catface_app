package cc.catface.api.frame.mvp;

import android.content.Context;
import android.text.TextUtils;

import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.system.Timer.TTimer;
import cc.catface.base.utils.java.TNumber;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class FrameMVPPresenterImp extends LightPresenter<FrameMVP_VP.View> implements FrameMVP_VP.Presenter {

    FrameMVPPresenterImp(FrameMVP_VP.View view, Context context) {
        super(view, context);
    }

    private String mUsername, mPassword;

    @Override public void checkAccount(String username, String password) {
        mUsername = username;
        mPassword = password;

        if (!TextUtils.isEmpty(mUsername) && mUsername.equals(mPassword)) {
            getView().checkAccountPass();
            return;
        }
        if (TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mPassword)) {
            getView().checkAccountNotPass("账号或密码不能为空~");
        } else {
            getView().checkAccountNotPass("账号或密码错误");
        }
    }

    @Override public void login() {
        TTimer.timeFinished(TNumber.getRandom(1_000, 4_000), () -> {
            if (1 == TNumber.getRandom(0, 2)) {
                getView().loginSuccess("恭喜~登陆成功-->" + mUsername + " - " + mPassword);
            } else {
                getView().loginFailure("糟糕~登录超时...");
            }
        });
    }

}

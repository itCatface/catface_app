package cc.catface.api.frame.mvp;

import android.text.TextUtils;

import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.utils.android.Timer.TTimer;
import cc.catface.base.utils.java.TNumber;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class FrameMVPPresenterImp extends MvpPresenter<FrameMVP_VP.View> implements FrameMVP_VP.Presenter {


    private String mUsername, mPassword;

    @Override
    public void checkAccount(String username, String password) {
        mUsername = username;
        mPassword = password;

        if (!TextUtils.isEmpty(mUsername) && mUsername.equals(mPassword)) {
            mView.checkAccountPass();
            return;
        }
        if (TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mPassword)) {
            mView.checkAccountNotPass("账号或密码不能为空~");
        } else {
            mView.checkAccountNotPass("账号或密码错误");
        }
    }

    @Override
    public void login() {
        TTimer.timeFinished(TNumber.getRandom(1_000, 4_000), () -> {
            if (1 == TNumber.getRandom(0, 2)) {
                mView.loginSuccess("恭喜~登陆成功-->" + mUsername + " - " + mPassword);
            } else {
                mView.loginFailure("糟糕~登录超时...");
            }
        });
    }

}

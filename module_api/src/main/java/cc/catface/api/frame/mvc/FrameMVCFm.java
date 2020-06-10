package cc.catface.api.frame.mvc;

import android.text.TextUtils;
import android.view.View;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFragmentFrameBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.system.Timer.TTimer;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.java.TNumber;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class FrameMVCFm extends LightFm<LightPresenter, ApiFragmentFrameBinding> {

    @Override public int layoutId() {
        return R.layout.api_fragment_frame;
    }

    @Override protected void initAction() {
        mBinding.btLogin.setOnClickListener(v -> {
            if (checkAccount()) login();
            else {
                mBinding.tvResult.setText(mInfo);
                TToast.get(mActivity).showBShortView(mInfo, TToast.B_WARNING);
            }
        });
    }

    @Override protected void initView() {
        mBinding.btLogin.setText("SIGN IN(by mvc...)");
    }

    private String mInfo = "";
    private String mUsername, mPassword;

    private boolean checkAccount() {
        boolean result = false;
        mUsername = mBinding.etUsername.getText().toString().trim();
        mPassword = mBinding.etPassword.getText().toString().trim();
        if (!TextUtils.isEmpty(mUsername) && mUsername.equals(mPassword)) result = true;
        if (TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mPassword)) {
            mInfo = "账号或密码不能为空~";
        } else {
            mInfo = "账号或密码错误";
        }

        return result;
    }

    private void login() {
        mBinding.loadingCgv.setVisibility(View.VISIBLE);
        mBinding.btLogin.setClickable(false);
        mBinding.btLogin.setText("signing...");
        TTimer.timeFinished(TNumber.getRandom(1_000, 4_000), () -> {
            mBinding.loadingCgv.setVisibility(View.GONE);
            mBinding.btLogin.setClickable(true);
            mBinding.btLogin.setText("SIGN IN(by mvc...)");
            mInfo = mUsername + " - " + mPassword;

            if (1 == TNumber.getRandom(0, 2)) {
                mBinding.tvResult.setText("恭喜~登陆成功-->" + mInfo);
            } else {
                mBinding.tvResult.setText("糟糕~登录超时...");
            }
        });
    }
}

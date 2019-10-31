package cc.catface.api.frame.mvp;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFragmentFrameBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.utils.android.common_print.toast.TToast;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class FrameMVPFm extends LightFm<FrameMVPPresenterImp, ApiFragmentFrameBinding> implements FrameMVP_VP.View {

    @Override public int layoutId() {
        return R.layout.api_fragment_frame;
    }

    @Override protected void initPresenter() {
        mPresenter = new FrameMVPPresenterImp(this, mActivity);
    }

    @Override protected void initView() {
        mBinding.btLogin.setText("SIGN IN(by mvp...)");
    }

    @Override protected void initAction() {
        mBinding.btLogin.setOnClickListener(v -> checkAccount());
    }


    /** private's */
    private void checkAccount() {
        mPresenter.checkAccount(mBinding.etUsername.getText().toString().trim(), mBinding.etPassword.getText().toString().trim());
    }

    private void login() {
        mPresenter.login();
    }


    /** View's */
    @Override public void checkAccountPass() {
        login();
    }

    @Override public void checkAccountNotPass(String error) {
        mBinding.tvResult.setText(error);
        TToast.get(mActivity).showBShortView(error, TToast.B_WARNING);
    }

    @Override public void loginSuccess(String info) {
        mBinding.tvResult.setText(info);
    }

    @Override public void loginFailure(String error) {
        mBinding.tvResult.setText(error);
        TToast.get(mActivity).showBShortView(error, TToast.B_ERROR);
    }
}

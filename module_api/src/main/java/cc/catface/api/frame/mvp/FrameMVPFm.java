package cc.catface.api.frame.mvp;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFragmentFrameBinding;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.base.utils.android.common_print.toast.TToast;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(FrameMVPPresenterImp.class)
public class FrameMVPFm extends MvpFragment<FrameMVPView, FrameMVPPresenterImp, ApiFragmentFrameBinding> implements FrameMVPView {
    @Override public int layoutId() {
        return R.layout.api_fragment_frame;
    }

    @Override protected void initAction() {
        mBinding.btLogin.setOnClickListener(v -> checkAccount());
    }

    @Override protected void viewCreated() {
        mBinding.btLogin.setText("SIGN IN(by mvp...)");
    }


    private void checkAccount() {
        mPresenter.checkAccount(mBinding.etUsername.getText().toString().trim(), mBinding.etPassword.getText().toString().trim());
    }

    private void login() {
        mPresenter.login();
    }


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

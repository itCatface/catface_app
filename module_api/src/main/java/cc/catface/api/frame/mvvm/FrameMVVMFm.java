package cc.catface.api.frame.mvvm;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFragmentFrameBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class FrameMVVMFm extends LightFm<LightPresenter, ApiFragmentFrameBinding> {

    @Override public int layoutId() {
        return R.layout.api_fragment_frame;
    }

    @Override protected void initView() {
        mBinding.btLogin.setText("SIGN IN(by mvvm...)");
    }
}

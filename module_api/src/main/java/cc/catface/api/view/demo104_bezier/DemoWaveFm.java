package cc.catface.api.view.demo104_bezier;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityWaveBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;


public class DemoWaveFm extends LightFm<LightPresenter, ApiActivityWaveBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_wave;
    }

    @Override protected void initView() {
        mBinding.btStartX.setOnClickListener(view -> mBinding.w1v.startAnimX());
        mBinding.btStartY.setOnClickListener(view -> mBinding.w1v.startAnimY());
    }


}

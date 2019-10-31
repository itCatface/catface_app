package cc.catface.api.view.demo104_bezier;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityBezierMainBinding;
import cc.catface.api.view.DemoViewHolderActivity;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

public class DemoBezierMainFm extends LightFm<LightPresenter, ApiActivityBezierMainBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_bezier_main;
    }

    @Override protected void initView() {


        mBinding.btReset.setOnClickListener(view -> mBinding.pv.reset());
        mBinding.btPaint2.setOnClickListener(view -> {
            ((DemoViewHolderActivity) mActivity).title("paint Bezier");
            ((DemoViewHolderActivity) mActivity).replace(new DemoPaintBezierFm());
        });
        mBinding.btWave.setOnClickListener(view -> {
            ((DemoViewHolderActivity) mActivity).title("wave");
            ((DemoViewHolderActivity) mActivity).replace(new DemoWaveFm());
        });
    }
}

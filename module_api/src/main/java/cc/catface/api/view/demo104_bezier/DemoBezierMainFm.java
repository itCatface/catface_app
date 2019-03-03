package cc.catface.api.view.demo104_bezier;

import android.content.Intent;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityBezierMainBinding;
import cc.catface.api.view.DemoViewHolderActivity;
import cc.catface.base.core_framework.base_normal.NormalFragment;

public class DemoBezierMainFm extends NormalFragment<ApiActivityBezierMainBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_bezier_main;
    }

    @Override public void createView() {


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

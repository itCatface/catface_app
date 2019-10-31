package cc.catface.api.view.demo101_paint_canvas;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityCanvasBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

public class DemoCanvasFm extends LightFm<LightPresenter, ApiActivityCanvasBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_canvas;
    }

    @Override protected void initView() {
        mBinding.fl.addView(new CustomView(mActivity, null));
    }

}

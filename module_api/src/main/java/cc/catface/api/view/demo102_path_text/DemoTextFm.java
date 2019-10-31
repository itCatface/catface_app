package cc.catface.api.view.demo102_path_text;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityTextBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

public class DemoTextFm extends LightFm<LightPresenter, ApiActivityTextBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_text;
    }

    @Override protected void initView() {
        mBinding.fl.addView(new CustomView(mActivity, null));
    }
}

package cc.catface.api.view.demo103_range;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityRangeBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

public class DemoRangeFm extends LightFm<LightPresenter, ApiActivityRangeBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_range;
    }

}

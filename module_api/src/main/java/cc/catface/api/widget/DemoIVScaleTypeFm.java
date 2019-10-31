package cc.catface.api.widget;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityIvScaleTypeBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoIVScaleTypeFm extends LightFm<LightPresenter, ApiActivityIvScaleTypeBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_iv_scale_type;
    }
}

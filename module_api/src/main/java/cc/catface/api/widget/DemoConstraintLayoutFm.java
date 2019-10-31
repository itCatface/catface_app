package cc.catface.api.widget;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityTestConstraintLayoutBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoConstraintLayoutFm extends LightFm<LightPresenter, ApiActivityTestConstraintLayoutBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_test_constraint_layout;
    }
}

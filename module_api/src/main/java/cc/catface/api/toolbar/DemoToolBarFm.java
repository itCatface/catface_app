package cc.catface.api.toolbar;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFmDemoToolBarBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoToolBarFm extends LightFm<LightPresenter, ApiFmDemoToolBarBinding> {

    @Override public int layoutId() {
        return R.layout.api_fm_demo_tool_bar;
    }
}

package cc.catface.api.scrollview;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityScrollBarBinding;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ScrollBarActivity extends LightAct<LightPresenter, ApiActivityScrollBarBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_scroll_bar;
    }

}

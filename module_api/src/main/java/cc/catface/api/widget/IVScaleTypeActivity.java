package cc.catface.api.widget;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityIvScaleTypeBinding;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.AROUTER.api_attrs_ivScaleType)
public class IVScaleTypeActivity extends NormalActivity<ApiActivityIvScaleTypeBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_iv_scale_type;
    }

    @Override public void create() {
        title();
    }

    private void title() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.AROUTER.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
    }
}

package cc.catface.api.widget;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityTestConstraintLayoutBinding;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.AROUTER.api_test_constraint_layout)
public class TestConstraintLayoutActivity extends NormalActivity<ApiActivityTestConstraintLayoutBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_test_constraint_layout;
    }

    @Override public void create() {
        initTitle();
    }

    private void initTitle() {
        title();
    }

    private void title() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.AROUTER.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
    }
}

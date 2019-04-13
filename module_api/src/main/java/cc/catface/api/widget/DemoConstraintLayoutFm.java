package cc.catface.api.widget;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityTestConstraintLayoutBinding;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.core_framework.base_normal.NormalFragment;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoConstraintLayoutFm extends NormalFragment<ApiActivityTestConstraintLayoutBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_test_constraint_layout;
    }

    @Override public void createView() {
        initTitle();
    }

    private void initTitle() {
        title();
    }

    private void title() {
        mBinding.tfa.setTitle("ConstraintLayout").setIcon1(R.string.fa_chevron_left);
    }
}

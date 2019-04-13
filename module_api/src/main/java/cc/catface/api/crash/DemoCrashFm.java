package cc.catface.api.crash;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityCrashBinding;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.toast.TToast;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoCrashFm extends NormalFragment<ApiActivityCrashBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_crash;
    }

    @Override protected void initAction() {
        mBinding.btNo.setOnClickListener(v -> {int i = 3 / 0;});
        mBinding.btOk.setOnClickListener(v -> TToast.get(mActivity).showBShortView("一来来玩呀~", TToast.B_SUCCESS));
    }

    @Override public void createView() {
        title();
    }

    private void title() {
        mBinding.tfaCrash.setTitle("crash").setIcon1(R.string.fa_chevron_left);
    }
}

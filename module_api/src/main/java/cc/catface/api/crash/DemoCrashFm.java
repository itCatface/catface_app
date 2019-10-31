package cc.catface.api.crash;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityCrashBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.common_print.toast.TToast;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoCrashFm extends LightFm<LightPresenter, ApiActivityCrashBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_crash;
    }

    @Override protected void initAction() {
        mBinding.btNo.setOnClickListener(v -> {int i = 3 / 0;});
        mBinding.btOk.setOnClickListener(v -> TToast.get(mActivity).showBShortView("一来来玩呀~", TToast.B_SUCCESS));
    }

}

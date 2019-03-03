package cc.catface.api.common;

import android.annotation.SuppressLint;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityAppInfoBinding;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.TAppInfo;

public class DemoSystemInfoFm extends NormalFragment<ApiActivityAppInfoBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_app_info;
    }

    @SuppressLint("SetTextI18n") @Override public void createView() {
        title();

        mBinding.tvVerName.setText("verName:" + TAppInfo.getVerName(mActivity));
        mBinding.tvVerCode.setText("verCode:" + TAppInfo.getVerCode(mActivity));
        mBinding.tvRomAvailSpace.setText("romAvailSpace:" + TAppInfo.getRomSpace(mActivity));
        mBinding.tvSdAvailSpace.setText("sdAvailSpace:" + TAppInfo.getSDSpace(mActivity));
        mBinding.tvAppNums.setText("appNums:" + TAppInfo.getInstalledAPP(mActivity).size());
    }

    private void title() {
        mBinding.tfa.setTitle("系统信息").setIcon1(R.string.fa_chevron_left);
    }
}

package cc.catface.api.common;

import android.annotation.SuppressLint;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityAppInfoBinding;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.utils.android.TAppInfo;

@Route(path = Const.AROUTER.api_appInfo)
public class AppInfoActivity extends NormalActivity<ApiActivityAppInfoBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_app_info;
    }

    @SuppressLint("SetTextI18n") @Override public void create() {
        title();

        mBinding.tvVerName.setText("verName:" + TAppInfo.getVerName(this));
        mBinding.tvVerCode.setText("verCode:" + TAppInfo.getVerCode(this));
        mBinding.tvRomAvailSpace.setText("romAvailSpace:" + TAppInfo.getRomSpace(this));
        mBinding.tvSdAvailSpace.setText("sdAvailSpace:" + TAppInfo.getSDSpace(this));
        mBinding.tvAppNums.setText("appNums:" + TAppInfo.getInstalledAPP(this).size());
    }

    private void title() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.AROUTER.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
    }
}

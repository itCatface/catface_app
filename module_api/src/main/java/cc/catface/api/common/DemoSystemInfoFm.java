package cc.catface.api.common;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityAppInfoBinding;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.domain.AppInfo;
import cc.catface.base.utils.android.TAppInfo;

public class DemoSystemInfoFm extends NormalFragment<ApiActivityAppInfoBinding> {
    @SuppressLint("HandlerLeak") private Handler mHandler = new Handler() {
        @SuppressLint("SetTextI18n") @Override public void handleMessage(Message msg) {
            mBinding.rlLoading.setVisibility(View.GONE);
            mBinding.tvVerName.setText("verName:" + mVersionName);
            mBinding.tvVerCode.setText("verCode:" + mVersionCode);
            mBinding.tvRomAvailSpace.setText("romAvailSpace:" + mRomAvailSpace);
            mBinding.tvSdAvailSpace.setText("sdAvailSpace:" + mAvailSDSpace);
            mBinding.tvAppNums.setText("appNums:" + mApps.size());
        }
    };


    @Override public int layoutId() {
        return R.layout.api_activity_app_info;
    }


    private String mVersionName, mRomAvailSpace, mAvailSDSpace;
    private int mVersionCode;
    private List<AppInfo> mApps = new ArrayList<>();

    @Override protected void initData() {
        new Thread(() -> {
            mVersionName = TAppInfo.getVerName(mActivity);
            mVersionCode = TAppInfo.getVerCode(mActivity);
            mRomAvailSpace = TAppInfo.getRomSpace(mActivity);
            mAvailSDSpace = TAppInfo.getSDSpace(mActivity);
            mApps = TAppInfo.getInstalledAPP(mActivity);
            mHandler.obtainMessage().sendToTarget();
        }).start();
    }

    @SuppressLint("SetTextI18n") @Override public void createView() {

    }
}

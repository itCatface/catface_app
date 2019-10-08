package cc.catface.api.common;

import android.os.Message;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityAppInfoBinding;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.ctool.system.AppInfo;
import cc.catface.ctool.system.TAppInfo;
import cc.catface.ctool.system.TScreen;
import cc.catface.ctool.system.TString;
import cc.catface.ctool.system.TWeakHandler;

public class DemoSystemInfoFm extends NormalFragment<ApiActivityAppInfoBinding> implements TWeakHandler.MessageListener {

    private TWeakHandler<DemoSystemInfoFm> mHandler;

    @Override public void handleMessage(Message msg) {
        mBinding.rlLoading.setVisibility(View.GONE);
        mBinding.tiVerName.setContent(mVersionName);
        mBinding.tiVerCode.setContent(TString.convert2String(mVersionCode));
        mBinding.tiRomAvailSpace.setContent(mRomAvailSpace);
        mBinding.tiSdAvailSpace.setContent(mAvailSDSpace);
        mBinding.tiAppNums.setContent(TString.convert2String(mApps.size()));
        mBinding.tiIp.setContent(TAppInfo.getIp(mActivity));
        mBinding.tiStatusBarHeight.setContent(TString.convert2String(TScreen.getStatusBarHeight(mActivity)));
        mBinding.tiActionBarHeight.setContent(TString.convert2String(TScreen.getActionBarHeight(mActivity)));
        mBinding.tiScreenWidth.setContent(TString.convert2String(TScreen.getScreenWidth(mActivity)));
        mBinding.tiScreenHeight.setContent(TString.convert2String(TScreen.getScreenHeight(mActivity)));
    }


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

    @Override public void createView() {
        mBinding.rlLoading.setVisibility(View.VISIBLE);
        mHandler = new TWeakHandler<>(this);
    }
}

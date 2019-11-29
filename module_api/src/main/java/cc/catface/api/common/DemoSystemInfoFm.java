package cc.catface.api.common;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityAppInfoBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.context.AppInfo;
import cc.catface.ctool.context.TAppInfo;
import cc.catface.ctool.context.TSystemAction;
import cc.catface.ctool.system.TScreen;
import cc.catface.ctool.system.TString;
import cc.catface.ctool.system.TWeakHandler;
import cc.catface.ctool.context.netstate.NetBroadcastReceiver;
import cc.catface.ctool.context.netstate.NetStateUtil;
import cc.catface.ctool.context.netstate.TNetwork;

public class DemoSystemInfoFm extends LightFm<LightPresenter, ApiActivityAppInfoBinding> {


    @Override public void handleMessage(Message msg) {
        mBinding.rlLoading.setVisibility(View.GONE);
        mBinding.tiVerName.setContent(mVersionName);
        mBinding.tiVerCode.setContent(TString.convert2String(mVersionCode));
        mBinding.tiRomAvailSpace.setContent(mRomAvailSpace);
        mBinding.tiSdAvailSpace.setContent(mAvailSDSpace);
        mBinding.tiAppNums.setContent(TString.convert2String(mApps.size()));
        mBinding.tiIp.setContent(TNetwork.getIp());
        mBinding.tiStatusBarHeight.setContent(TString.convert2String(TScreen.getStatusBarHeight()));
        mBinding.tiActionBarHeight.setContent(TString.convert2String(TScreen.getActionBarHeight()));
        mBinding.tiScreenWidth.setContent(TString.convert2String(TScreen.getScreenWidth()));
        mBinding.tiScreenHeight.setContent(TString.convert2String(TScreen.getScreenHeight()));
        mBinding.tiRamRemain.setContent(mRAMRemain);
        mBinding.tiRamTotal.setContent(mRAMTotal);
        mBinding.tiRunningProcessCount.setContent(TString.convert2String(mRunningProcessCount));
    }

    @Override public int layoutId() {
        return R.layout.api_activity_app_info;
    }

    @Override protected void initView() {
        mBinding.rlLoading.setVisibility(View.VISIBLE);
    }

    @Override protected void initHandler() {
        mHandler = new TWeakHandler<>(this);
    }

    private String mVersionName, mRomAvailSpace, mAvailSDSpace, mRAMRemain, mRAMTotal;
    private long mRunningProcessCount;
    private int mVersionCode;
    private List<AppInfo> mApps = new ArrayList<>();

    @Override protected void initData() {
        new Thread(() -> {
            mVersionName = TAppInfo.getVerName();
            mVersionCode = TAppInfo.getVerCode();
            mRomAvailSpace = TAppInfo.getROMRemain();
            mAvailSDSpace = TAppInfo.getSDRemain();
            mApps = TAppInfo.getInstalledAPP();
            mRAMRemain = TAppInfo.getRAMRemain();
            mRAMTotal = TAppInfo.getTotalRAM();
            mRunningProcessCount = TAppInfo.getRunningProcessCount();
            mHandler.obtainMessage().sendToTarget();
        }).start();
    }

    @Override protected void initAction() {
        mBinding.tiOpenNetSettings.setOnClickListener(v -> TSystemAction.openAction(TSystemAction.ACTION_WIRELESS_SETTINGS));
    }

    private NetBroadcastReceiver mReceiver;

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mReceiver == null) {
            mReceiver = new NetBroadcastReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            mActivity.registerReceiver(mReceiver, filter);
            mReceiver.setListener(netWorkType -> {
                String netState = "无网络连接";
                if (NetStateUtil.NETWORK_WIFI == netWorkType) {
                    netState = "wifi";
                } else if (NetStateUtil.NETWORK_MOBILE == netWorkType) {
                    netState = "移动网络";
                }
                mBinding.tiNetState.setContent("当前网络状态：" + netState);
            });
        }
    }

    @Override public void onDestroy() {
        super.onDestroy();
        if (null != mReceiver) mActivity.unregisterReceiver(mReceiver);
    }
}

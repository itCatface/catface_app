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
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.ctool.system.AppInfo;
import cc.catface.ctool.system.TAppInfo;
import cc.catface.ctool.system.TScreen;
import cc.catface.ctool.system.TString;
import cc.catface.ctool.system.TWeakHandler;
import cc.catface.ctool.system.netstate.NetBroadcastReceiver;
import cc.catface.ctool.system.netstate.NetStateUtil;

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

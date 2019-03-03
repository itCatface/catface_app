package cc.catface.module_apis.nano_httpd.view;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.io.IOException;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpActivity;
import cc.catface.base.utils.android.common_title.TitleFontAwesome;
import cc.catface.base.utils.android.view.TFontType;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisActivityServerBinding;
import cc.catface.module_apis.nano_httpd.engine.NanoHTTPDServer;
import cc.catface.module_apis.nano_httpd.presenter.ServerPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.apis_nano)
@CreatePresenter(ServerPresenterImp.class)
public class ServerActivity extends MvpActivity<ServerView, ServerPresenterImp, ApisActivityServerBinding> implements ServerView {
    @Override public int layoutId() {
        return R.layout.apis_activity_server;
    }

    private NanoHTTPDServer server = new NanoHTTPDServer(9093);

    @Override public void initData() {
        TFontType.use(mBinding.tvIp, "fonts/noto_sans_semi_bold.ttf", getCurrentIpAndPort());
    }

    @Override protected void initAction() {
        mBinding.tfa.setOnClickListener((TitleFontAwesome.OnClickListener) view -> {
            if(R.id.ttv1 == view.getId()) finish();
        });
    }

    @Override public void create() {
        title();
    }

    private void title() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.ARouter.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
    }

    @Override protected void onResume() {
        super.onResume();
        try {
            server.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override protected void onStop() {
        super.onStop();
        server.stop();
    }

    private String getCurrentIpAndPort() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        return convertIp(connectionInfo.getIpAddress());
    }

    private String convertIp(int ip) {
        return (ip & 0xff) + "." + ((ip >> 8) & 0xff) + "." + ((ip >> 16) & 0xff) + "." + ((ip >> 24) & 0xff);
    }
}

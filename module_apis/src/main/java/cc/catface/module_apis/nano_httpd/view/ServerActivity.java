package cc.catface.module_apis.nano_httpd.view;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.io.IOException;

import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsAppCompatActivityID;
import cc.catface.base.utils.android.common_title.TitleFontAwesome;
import cc.catface.module_apis.R;
import cc.catface.module_apis.nano_httpd.engine.NanoHTTPDServer;
import cc.catface.module_apis.nano_httpd.presenter.ServerPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = "/apis/nano")
@CreatePresenter(ServerPresenterImp.class)
public class ServerActivity extends AbsAppCompatActivityID<ServerView, ServerPresenterImp> implements ServerView {

    private NanoHTTPDServer server = new NanoHTTPDServer(9093);


    @Override public int layoutId() {
        return R.layout.apis_activity_server;
    }

    private TitleFontAwesome tfa_server;
    private TextView tv_ip;

    @Override public void ids() {
        tfa_server = (TitleFontAwesome) findViewById(R.id.tfa_server);
        tv_ip = (TextView) findViewById(R.id.tv_ip);
    }

    @Override public void create() {
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        tfa_server.setTitle("nano服务器");
    }

    private void initEvent() {
        tfa_server.setOnClickListener((TitleFontAwesome.OnClickListener) view -> {
            if (R.id.tv_serial_1 == view.getId()) finish();
        });
    }

    private void initData() {
        tv_ip.setText(getCurrentIpAndPort());
    }

    @Override protected void onResume() {
        super.onResume();
        try {
            server.start();
        } catch (IOException e) {
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

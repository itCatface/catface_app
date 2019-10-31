package cc.catface.module_apis.nano_httpd.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.io.IOException;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.ctool.context.netstate.TNetwork;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisActivityServerBinding;
import cc.catface.module_apis.nano_httpd.engine.NanoHTTPDServer;
import cc.catface.module_apis.nano_httpd.vp.ServerPresenterImp;
import cc.catface.module_apis.nano_httpd.vp.ServerVP;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.apis_nano) public class ServerActivity extends LightAct<ServerPresenterImp, ApisActivityServerBinding> implements ServerVP.ServerView {

    @Override public int layoutId() {
        return R.layout.apis_activity_server;
    }

    private NanoHTTPDServer server = new NanoHTTPDServer(9093);

    @Override public void created() {
        initToolBar();
        if (null != mBar) mBar.setSubtitle(TNetwork.getIp());
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


    /** tool bar */
    private ActionBar mBar;

    private void initToolBar() {
        Toolbar toolbar = mBinding.iTbApis.tbTitle;
        setSupportActionBar(toolbar);
        mBar = getSupportActionBar();
        if (null != mBar) {
            mBar.setDisplayShowHomeEnabled(true);
            mBar.setTitle("nano httpd组件使用");
        }
        toolbar.setNavigationIcon(R.mipmap.flaticon_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}

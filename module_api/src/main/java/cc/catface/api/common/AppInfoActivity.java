package cc.catface.api.common;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.api.R;
import cc.catface.base.core_framework.base_normal.NormalBaseActivityID;
import cc.catface.base.utils.android.TAppInfo;

@Route(path = "/api/appInfo")
public class AppInfoActivity extends NormalBaseActivityID {

    @Override public int layoutId() {
        return R.layout.api_activity_app_info;
    }

    @SuppressLint("SetTextI18n") @Override public void ids() {
        ((TextView) findViewById(R.id.tv_verName)).setText("verName:" + TAppInfo.getVerName(this));
        ((TextView) findViewById(R.id.tv_verCode)).setText("verCode:" + TAppInfo.getVerCode(this));
        ((TextView) findViewById(R.id.tv_romAvailSpace)).setText("romAvailSpace:" + TAppInfo.getRomSpace(this));
        ((TextView) findViewById(R.id.tv_sdAvailSpace)).setText("sdAvailSpace:" + TAppInfo.getSDSpace(this));
        ((TextView) findViewById(R.id.tv_appNums)).setText("appNums:" + TAppInfo.getInstalledAPP(this).size());
    }

    @Override public void create() {

    }
}

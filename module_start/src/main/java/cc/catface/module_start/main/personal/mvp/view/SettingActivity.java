package cc.catface.module_start.main.personal.mvp.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;

import butterknife.internal.Utils;
import cc.catface.base.core_framework.base_normal.NormalBaseActivityID;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.module_start.R;

public class SettingActivity extends NormalBaseActivityID {

    @Override public int layoutId() {
        return R.layout.start_activity_setting;
    }

    private ListView lv_version;
    private final String item_version = "版本";
    private final String item_about = "关于";
    private final String[] items_version = {item_version, item_about};

    @Override public void ids() {
        lv_version = (ListView) findViewById(R.id.lv_version);
    }

    @Override public void create() {
        TListView.str(this, lv_version, items_version, pos -> {
            switch (items_version[pos]) {
                case item_version:
                    checkUpgrade(this);
                    break;
                case item_about:

                    break;
            }
        });
    }


    public void checkUpgrade(final Context context) {
        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();
        if (null == upgradeInfo) {
            Toast.makeText(context, "无升级信息", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(context, "有升级信息" + upgradeInfo.versionName, Toast.LENGTH_SHORT).show();
            Beta.checkUpgrade();
        }

        return;

//        Beta.upgradeStateListener = new UpgradeStateListener() {
//
//            @Override public void onUpgradeSuccess(boolean isManual) {
//                Toast.makeText(getApplicationContext(), "UPGRADE_SUCCESS", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override public void onUpgradeFailed(boolean isManual) {
//                Toast.makeText(getApplicationContext(), "UPGRADE_FAILED", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override public void onUpgrading(boolean isManual) {
//                Toast.makeText(getApplicationContext(), "UPGRADE_CHECKING", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override public void onDownloadCompleted(boolean b) {
//                Toast.makeText(getApplicationContext(), "onDownloadCompleted", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override public void onUpgradeNoVersion(boolean isManual) {
//                Toast.makeText(getApplicationContext(), "UPGRADE_NO_VERSION", Toast.LENGTH_SHORT).show();
//            }
//
//        };
//
//
//        Beta.checkUpgrade(true, false);

    }


}

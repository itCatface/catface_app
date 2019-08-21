package cc.catface.module_start.main.personal.mvp.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.module_start.R;
import cc.catface.module_start.databinding.StartActivitySettingBinding;

public class SettingActivity extends NormalActivity<StartActivitySettingBinding> {
    @Override public int layoutId() {
        return R.layout.start_activity_setting;
    }

    private final String item_version = "版本";
    private final String item_about = "关于";
    private final String[] items_version = {item_version, item_about};

    @Override public void create() {
        initToolBar();

        TListView.str(this, mBinding.lvVersion, items_version, pos -> {
            switch (items_version[pos]) {
                case item_version:
                    break;
                case item_about:

                    break;
            }
        });
    }


    /** tool bar */
    private void initToolBar() {
        Toolbar toolbar = mBinding.iTbStart.tbTitle;
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (null != bar) {
            bar.setDisplayShowHomeEnabled(true);
            bar.setTitle("系统设置");
        }
        toolbar.setNavigationIcon(R.mipmap.flaticon_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}

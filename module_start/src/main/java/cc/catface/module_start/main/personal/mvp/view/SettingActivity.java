package cc.catface.module_start.main.personal.mvp.view;

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
        TListView.str(this, mBinding.lvVersion, items_version, pos -> {
            switch (items_version[pos]) {
                case item_version:
                    break;
                case item_about:

                    break;
            }
        });
    }
}

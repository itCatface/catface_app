package cc.catface.module_start.main.personal.mvp.view;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;

import butterknife.internal.Utils;
import cc.catface.base.core_framework.base_mvp.view.AbsFragmentID;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.utils.android.common_intent.TIntent;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.module_start.R;
import cc.catface.module_start.main.personal.mvp.presenter.PersonalPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(PersonalPresenterImp.class)
public class PersonalFm extends AbsFragmentID<PersonalView, PersonalPresenterImp> implements PersonalView {
    @Override public int layoutId() {
        return R.layout.fm_fourth_personal;
    }

    private ListView lv_setting;
    private final String ITEM_SETTING = "系统设置";
    private String[] mSettingItems = {ITEM_SETTING};

    @Override public void ids(View v) {
        lv_setting = (ListView) v.findViewById(R.id.lv_setting);
    }

    @Override public void viewCreated() {
        TListView.str(mActivity, lv_setting, mSettingItems, pos -> {
            switch (mSettingItems[pos]) {
                case ITEM_SETTING:
                    TIntent.startActivity(mActivity, SettingActivity.class, true);
                    break;
            }
        });
    }

    @Override public void loadBaiduSuc(String result) {
    }

    @Override public void loadBaiduErr(String error) {

    }





}

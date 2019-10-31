package cc.catface.start.main.personal.view;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.ctool.view.activity.TActivity;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.start.R;
import cc.catface.start.databinding.FmFourthPersonalBinding;
import cc.catface.start.main.personal.vp.PersonalPresenterImp;
import cc.catface.start.main.personal.vp.PersonalVP;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class PersonalFm extends LightFm<PersonalPresenterImp, FmFourthPersonalBinding> implements PersonalVP.PersonalView {

    @Override public int layoutId() {
        return R.layout.fm_fourth_personal;
    }

    private final String ITEM_SETTING = "系统设置";
    private String[] mSettingItems = {ITEM_SETTING};

    @Override protected void initView() {
        TListView.str(mActivity, mBinding.lvSetting, mSettingItems, pos -> {
            switch (mSettingItems[pos]) {
                case ITEM_SETTING:
                    TActivity.startActivity(mActivity, SettingActivity.class);
                    break;
            }
        });
    }

    @Override public void loadBaiduSuc(String result) {
    }

    @Override public void loadBaiduErr(String error) {

    }


}

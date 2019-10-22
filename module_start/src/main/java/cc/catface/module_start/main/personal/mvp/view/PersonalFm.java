package cc.catface.module_start.main.personal.mvp.view;

import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.ctool.view.activity.TActivity;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.module_start.R;
import cc.catface.module_start.databinding.FmFourthPersonalBinding;
import cc.catface.module_start.main.personal.mvp.presenter.PersonalPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(PersonalPresenterImp.class)
public class PersonalFm extends MvpFragment<PersonalView, PersonalPresenterImp, FmFourthPersonalBinding> implements PersonalView {
    @Override public int layoutId() {
        return R.layout.fm_fourth_personal;
    }

    private final String ITEM_SETTING = "系统设置";
    private String[] mSettingItems = {ITEM_SETTING};

    @Override public void viewCreated() {
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

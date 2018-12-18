package cc.catface.app.module.personal.view;

import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.app.R;
import cc.catface.app.module.personal.presenter.PersonalPresenterImp;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsFragment;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(PersonalPresenterImp.class)
public class PersonalFm extends AbsFragment<PersonalView, PersonalPresenterImp> implements PersonalView {
    @Override public int layoutId() {
        return R.layout.fm_fourth_personal;
    }

    @Override public void viewCreated() {
        mPresenter.t();

        mPresenter.loadBaidu();
    }

    @Override public void loadBaiduSuc(String result) {
        TToast.get(mActivity).showShortNormal(result);
    }

    @Override public void loadBaiduErr(String error) {

    }
}

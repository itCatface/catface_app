package cc.catface.module_start.main.personal.mvp.presenter;

import java.util.Date;

import cc.catface.base.core_framework.base_mvp.presenter.BaseMVPPresenter;
import cc.catface.module_start.main.personal.mvp.view.PersonalView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class PersonalPresenterImp extends BaseMVPPresenter<PersonalView> implements PersonalPresenter {

    public void t() {
        mView.loadBaiduSuc(new Date().toLocaleString());
    }


    @Override public void loadBaidu() {

    }
}

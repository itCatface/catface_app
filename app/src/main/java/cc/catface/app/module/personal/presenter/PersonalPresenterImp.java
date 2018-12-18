package cc.catface.app.module.personal.presenter;

import java.util.Date;

import cc.catface.app.module.personal.view.PersonalView;
import cc.catface.base.core_framework.base_mvp.presenter.BaseMVPPresenter;

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

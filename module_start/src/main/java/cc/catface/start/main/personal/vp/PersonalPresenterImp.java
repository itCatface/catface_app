package cc.catface.start.main.personal.vp;

import java.util.Date;

import cc.catface.base.core_framework.light_mvp.LightPresenter;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class PersonalPresenterImp extends LightPresenter<PersonalVP.PersonalView> implements PersonalVP.PersonalPresenter {

    public void t() {
        getView().loadBaiduSuc(new Date().toLocaleString());
    }


    @Override public void loadBaidu() {

    }
}

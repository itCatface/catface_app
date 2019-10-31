package cc.catface.start.main.personal.vp;

import cc.catface.base.core_framework.light_mvp.LightView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface PersonalVP {

    interface PersonalPresenter {
        void loadBaidu();
    }

    interface PersonalView extends LightView {
        void loadBaiduSuc(String result);

        void loadBaiduErr(String error);
    }


}

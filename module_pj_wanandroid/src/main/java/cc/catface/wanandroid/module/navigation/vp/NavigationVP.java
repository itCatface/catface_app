package cc.catface.wanandroid.module.navigation.vp;

import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.wanandroid.engine.domain.NavigationData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface NavigationVP {

    interface NavigationPresenter {
        void request();
    }

    interface NavigationView extends LightView {
        void requestSuccess(NavigationData data);

        void requestFailure(String error);
    }

}

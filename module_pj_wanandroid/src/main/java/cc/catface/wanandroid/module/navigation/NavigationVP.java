package cc.catface.wanandroid.module.navigation;

import cc.catface.base.core_framework.base_mvp.view.MvpView;
import cc.catface.wanandroid.engine.domain.NavigationData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface NavigationVP {

    interface NavigationPresenter {
        void request();
    }

    interface NavigationView extends MvpView {
        void requestSuccess(NavigationData data);

        void requestFailure(String error);
    }

}

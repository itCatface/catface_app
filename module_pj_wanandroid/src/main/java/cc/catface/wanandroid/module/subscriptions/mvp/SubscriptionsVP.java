package cc.catface.wanandroid.module.subscriptions.mvp;

import cc.catface.base.core_framework.base_mvp.view.MvpView;
import cc.catface.wanandroid.engine.domain.SubscriptionsData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface SubscriptionsVP {

    interface SubscriptionsPresenter {
        void request();
    }

    interface SubscriptionsView extends MvpView {
        void requestSuccess(SubscriptionsData data);

        void requestFailure(String error);
    }
}

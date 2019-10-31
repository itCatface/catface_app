package cc.catface.wanandroid.module.subscriptions.vp;

import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.wanandroid.engine.domain.SubscriptionsData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface SubscriptionsVP {

    interface SubscriptionsPresenter {
        void request();
    }

    interface SubscriptionsView extends LightView {
        void requestSuccess(SubscriptionsData data);

        void requestFailure(String error);
    }
}

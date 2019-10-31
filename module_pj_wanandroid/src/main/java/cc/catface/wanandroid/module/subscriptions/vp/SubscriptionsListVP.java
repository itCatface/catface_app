package cc.catface.wanandroid.module.subscriptions.vp;

import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.wanandroid.engine.domain.SubscriptionsListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface SubscriptionsListVP {

    interface SubscriptionsListPresenter {
        void request(int page, int cid);
    }

    interface SubscriptionsListView extends LightView {
        void requestSuccess(SubscriptionsListData data);

        void requestFailure(String error);
    }

}

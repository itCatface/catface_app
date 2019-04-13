package cc.catface.wanandroid.module.subscriptions;

import cc.catface.base.core_framework.base_mvp.view.MvpView;
import cc.catface.wanandroid.engine.domain.ProjectsListData;
import cc.catface.wanandroid.engine.domain.SubscriptionsListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface SubscriptionsListVP {

    interface SubscriptionsListPresenter {
        void request(int page, int cid);
    }

    interface SubscriptionsListView extends MvpView {
        void requestSuccess(SubscriptionsListData data);

        void requestFailure(String error);
    }

}

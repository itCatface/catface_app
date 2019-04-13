package cc.catface.wanandroid.module.projects.mvp.projects_list;

import cc.catface.base.core_framework.base_mvp.view.MvpView;
import cc.catface.wanandroid.engine.domain.ProjectsListData;
import cc.catface.wanandroid.engine.domain.SubscriptionsListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface ProjectsListVP {

    interface ProjectsListPresenter {
        void request(int page, int cid);
    }

    interface ProjectsListView extends MvpView {
        void requestSuccess(ProjectsListData data);

        void requestFailure(String error);
    }

}

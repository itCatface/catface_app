package cc.catface.wanandroid.module.projects.mvp.projects;

import cc.catface.base.core_framework.base_mvp.view.MvpView;
import cc.catface.wanandroid.engine.domain.ProjectsData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface ProjectsVP {

    interface ProjectsPresenter {
        void request();
    }

    interface ProjectsView extends MvpView {
        void requestSuccess(ProjectsData data);

        void requestFailure(String error);
    }

}

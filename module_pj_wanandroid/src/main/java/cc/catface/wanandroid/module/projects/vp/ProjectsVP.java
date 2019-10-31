package cc.catface.wanandroid.module.projects.vp;

import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.wanandroid.engine.domain.ProjectsData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface ProjectsVP {

    interface ProjectsPresenter {
        void request();
    }

    interface ProjectsView extends LightView {
        void requestSuccess(ProjectsData data);

        void requestFailure(String error);
    }

}

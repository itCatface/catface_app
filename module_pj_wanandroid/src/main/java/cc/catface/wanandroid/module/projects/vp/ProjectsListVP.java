package cc.catface.wanandroid.module.projects.vp;

import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.wanandroid.engine.domain.ProjectsListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface ProjectsListVP {

    interface ProjectsListPresenter {
        void request(int page, int cid);
    }

    interface ProjectsListView extends LightView {
        void requestSuccess(ProjectsListData data);

        void requestFailure(String error);
    }

}

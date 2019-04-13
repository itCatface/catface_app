package cc.catface.wanandroid.module.projects.mvp.projects;

import com.google.gson.Gson;

import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.ProjectsData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ProjectsPresenterImpl extends MvpPresenter<ProjectsVP.ProjectsView> implements ProjectsVP.ProjectsPresenter {
    @Override public void request() {
        OkHttpUtil.get("https://www.wanandroid.com/project/tree/json", new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                TLog.d("result: " + result);
                ProjectsData data = new Gson().fromJson(result, ProjectsData.class);
                mView.requestSuccess(data);
            }

            @Override public void onFailure(String error) {
                mView.requestFailure(error);
            }
        });
    }
}

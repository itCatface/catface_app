package cc.catface.wanandroid.module.projects.vp;

import android.content.Context;

import com.google.gson.Gson;

import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.system.TLog;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.ProjectsData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ProjectsPresenterImpl extends LightPresenter<ProjectsVP.ProjectsView> implements ProjectsVP.ProjectsPresenter {

    public ProjectsPresenterImpl(ProjectsVP.ProjectsView view, Context context) {
        super(view, context);
    }

    @Override public void request() {
        OkHttpUtil.get("https://www.wanandroid.com/project/tree/json", new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                TLog.d("result: " + result);
                ProjectsData data = new Gson().fromJson(result, ProjectsData.class);
                getView().requestSuccess(data);
            }

            @Override public void onFailure(String error) {
                getView().requestFailure(error);
            }
        });
    }
}

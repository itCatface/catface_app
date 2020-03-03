package cc.catface.wanandroid.module.projects.vp;

import android.content.Context;

import com.google.gson.Gson;

import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.system.TLog;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.ProjectsListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ProjectsListPresenterImpl extends LightPresenter<ProjectsListVP.ProjectsListView> implements ProjectsListVP.ProjectsListPresenter {

    public ProjectsListPresenterImpl(ProjectsListVP.ProjectsListView view, Context context) {
        super(view, context);
    }

    @Override public void request(int page, int cid) {
        String url = String.format("https://www.wanandroid.com/project/list/%s/json?cid=%s", page, cid);
        OkHttpUtil.get(url, new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                TLog.d("SubscriptionsListPresenterImpl", "SubscriptionsListPresenterImpl：" + result);
                ProjectsListData data = new Gson().fromJson(result, ProjectsListData.class);
                mView.requestSuccess(data);
            }

            @Override public void onFailure(String error) {
                mView.requestFailure(error);
            }
        });
    }
}

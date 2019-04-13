package cc.catface.wanandroid.module.projects.mvp.projects_list;

import com.google.gson.Gson;

import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.ProjectsListData;
import cc.catface.wanandroid.engine.domain.SubscriptionsListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ProjectsListPresenterImpl extends MvpPresenter<ProjectsListVP.ProjectsListView> implements ProjectsListVP.ProjectsListPresenter {
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

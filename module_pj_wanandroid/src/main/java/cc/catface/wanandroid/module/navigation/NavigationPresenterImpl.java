package cc.catface.wanandroid.module.navigation;

import com.google.gson.Gson;

import java.util.LinkedHashSet;
import java.util.Set;

import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.NavigationData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class NavigationPresenterImpl extends MvpPresenter<NavigationVP.NavigationView> implements NavigationVP.NavigationPresenter {
    @Override public void request() {
        OkHttpUtil.get("https://www.wanandroid.com/navi/json", new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                NavigationData data = new Gson().fromJson(result, NavigationData.class);
                mView.requestSuccess(data);
            }

            @Override public void onFailure(String error) {
                mView.requestFailure(error);
            }
        });
    }
}

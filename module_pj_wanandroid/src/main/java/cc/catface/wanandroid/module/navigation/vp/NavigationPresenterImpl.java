package cc.catface.wanandroid.module.navigation.vp;

import android.content.Context;

import com.google.gson.Gson;

import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.NavigationData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class NavigationPresenterImpl extends LightPresenter<NavigationVP.NavigationView> implements NavigationVP.NavigationPresenter {

    public NavigationPresenterImpl(NavigationVP.NavigationView view, Context context) {
        super(view, context);
    }

    @Override public void request() {
        OkHttpUtil.get("https://www.wanandroid.com/navi/json", new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                NavigationData data = new Gson().fromJson(result, NavigationData.class);
                getView().requestSuccess(data);
            }

            @Override public void onFailure(String error) {
                getView().requestFailure(error);
            }
        });
    }
}

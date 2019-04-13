package cc.catface.wanandroid.module.subscriptions.mvp;

import com.google.gson.Gson;

import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.SubscriptionsData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SubscriptionsPresenterImpl extends MvpPresenter<SubscriptionsVP.SubscriptionsView> implements SubscriptionsVP.SubscriptionsPresenter {
    @Override public void request() {
        OkHttpUtil.get("https://wanandroid.com/wxarticle/chapters/json", new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                SubscriptionsData data = new Gson().fromJson(result, SubscriptionsData.class);
                mView.requestSuccess(data);
            }

            @Override public void onFailure(String error) {
                mView.requestFailure(error);
            }
        });
    }
}

package cc.catface.wanandroid.module.subscriptions.vp;

import android.content.Context;

import com.google.gson.Gson;

import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.SubscriptionsData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SubscriptionsPresenterImpl extends LightPresenter<SubscriptionsVP.SubscriptionsView> implements SubscriptionsVP.SubscriptionsPresenter {

    public SubscriptionsPresenterImpl(SubscriptionsVP.SubscriptionsView view, Context context) {
        super(view, context);
    }

    @Override public void request() {
        OkHttpUtil.get("https://wanandroid.com/wxarticle/chapters/json", new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                SubscriptionsData data = new Gson().fromJson(result, SubscriptionsData.class);
                getView().requestSuccess(data);
            }

            @Override public void onFailure(String error) {
                getView().requestFailure(error);
            }
        });
    }
}

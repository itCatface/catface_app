package cc.catface.wanandroid.module.subscriptions.vp;

import android.content.Context;

import com.google.gson.Gson;

import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.system.TLog;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.SubscriptionsListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SubscriptionsListPresenterImpl extends LightPresenter<SubscriptionsListVP.SubscriptionsListView> implements SubscriptionsListVP.SubscriptionsListPresenter {

    public SubscriptionsListPresenterImpl(SubscriptionsListVP.SubscriptionsListView view, Context context) {
        super(view, context);
    }

    @Override public void request(int page, int cid) {
        String url = String.format("https://wanandroid.com/wxarticle/list/%s/%s/json", cid, page);
        OkHttpUtil.get(url, new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                TLog.d("SubscriptionsListPresenterImpl", "SubscriptionsListPresenterImpl：" + result);
                SubscriptionsListData data = new Gson().fromJson(result, SubscriptionsListData.class);
                getView().requestSuccess(data);
            }

            @Override public void onFailure(String error) {
                getView().requestFailure(error);
            }
        });
    }
}

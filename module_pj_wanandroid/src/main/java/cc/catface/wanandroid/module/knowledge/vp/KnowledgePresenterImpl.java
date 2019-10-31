package cc.catface.wanandroid.module.knowledge.vp;


import android.content.Context;

import com.google.gson.Gson;

import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.KnowledgeData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class KnowledgePresenterImpl extends LightPresenter<KnowledgeVP.KnowledgeView> implements KnowledgeVP.KnowledgePresenter {

    public KnowledgePresenterImpl(KnowledgeVP.KnowledgeView view, Context context) {
        super(view, context);
    }

    @Override public void request() {
        OkHttpUtil.get("http://www.wanandroid.com/tree/json", new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                TLog.d("result: " + result);
                KnowledgeData data = new Gson().fromJson(result, KnowledgeData.class);
                mView.requestSuccess(data);
            }

            @Override public void onFailure(String error) {
                mView.requestFailure();
            }
        });
    }
}

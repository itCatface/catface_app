package cc.catface.wanandroid.module.knowledge.vp;

import android.content.Context;

import com.google.gson.Gson;

import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.system.TLog;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.KnowledgeColumnListData;
import cc.catface.wanandroid.engine.domain.WanandroidConst;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class KnowledgeColumnListPresenterImpl extends LightPresenter<KnowledgeColumnListVP.KnowledgeColumnListView> implements KnowledgeColumnListVP.KnowledgeColumnListPresenter {

    public KnowledgeColumnListPresenterImpl(KnowledgeColumnListVP.KnowledgeColumnListView view, Context context) {
        super(view, context);
    }

    @Override public void request(int page, int cid) {
        String url = String.format(WanandroidConst.url_knowledge_tree_list, page, cid);
        OkHttpUtil.get(url, new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                TLog.d("SubscriptionsListPresenterImpl", "SubscriptionsListPresenterImpl：" + result);
                KnowledgeColumnListData data = new Gson().fromJson(result, KnowledgeColumnListData.class);
                mView.requestSuccess(data);
            }

            @Override public void onFailure(String error) {
                mView.requestFailure(error);
            }
        });
    }
}

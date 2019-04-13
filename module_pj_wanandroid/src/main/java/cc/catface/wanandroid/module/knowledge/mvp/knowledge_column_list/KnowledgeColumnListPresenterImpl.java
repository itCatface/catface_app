package cc.catface.wanandroid.module.knowledge.mvp.knowledge_column_list;

import com.google.gson.Gson;

import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.KnowledgeColumnListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class KnowledgeColumnListPresenterImpl extends MvpPresenter<KnowledgeColumnListVP.KnowledgeColumnListView> implements KnowledgeColumnListVP.KnowledgeColumnListPresenter {
    @Override public void request(int page, int cid) {
        String url = String.format("https://www.wanandroid.com/article/list/%s/json?cid=%s", page, cid);
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

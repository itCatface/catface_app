package cc.catface.wanandroid.module.knowledge.mvp.knowledge_column_list;

import cc.catface.base.core_framework.base_mvp.view.MvpView;
import cc.catface.wanandroid.engine.domain.KnowledgeColumnListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface KnowledgeColumnListVP {

    interface KnowledgeColumnListPresenter {
        void request(int page, int cid);
    }

    interface KnowledgeColumnListView extends MvpView {
        void requestSuccess(KnowledgeColumnListData data);

        void requestFailure(String error);
    }

}

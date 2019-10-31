package cc.catface.wanandroid.module.knowledge.vp;

import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.wanandroid.engine.domain.KnowledgeColumnListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface KnowledgeColumnListVP {

    interface KnowledgeColumnListPresenter {
        void request(int page, int cid);
    }

    interface KnowledgeColumnListView extends LightView {
        void requestSuccess(KnowledgeColumnListData data);

        void requestFailure(String error);
    }

}

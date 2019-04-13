package cc.catface.wanandroid.module.knowledge.mvp.knowledge;

import cc.catface.base.core_framework.base_mvp.view.MvpView;
import cc.catface.wanandroid.engine.domain.KnowledgeData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface KnowledgeVP {

    interface KnowledgePresenter {
        void request();
    }

    interface KnowledgeView extends MvpView {
        void requestSuccess(KnowledgeData data);

        void requestFailure();
    }

}

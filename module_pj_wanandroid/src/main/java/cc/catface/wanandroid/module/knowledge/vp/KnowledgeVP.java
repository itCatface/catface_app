package cc.catface.wanandroid.module.knowledge.vp;

import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.wanandroid.engine.domain.KnowledgeData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface KnowledgeVP {

    interface KnowledgePresenter {
        void request();
    }

    interface KnowledgeView extends LightView {
        void requestSuccess(KnowledgeData data);

        void requestFailure();
    }

}

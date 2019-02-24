package cc.catface.module_apis.memo.view;

import cc.catface.base.core_framework.base_mvp.view.MvpView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface MemoView extends MvpView {

    void daoOperationSuccess();

    void daoOperationFailure(String reason);


}

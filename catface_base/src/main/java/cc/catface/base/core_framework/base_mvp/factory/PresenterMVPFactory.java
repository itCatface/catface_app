package cc.catface.base.core_framework.base_mvp.factory;

import cc.catface.base.core_framework.base_mvp.presenter.BaseMVPPresenter;
import cc.catface.base.core_framework.base_mvp.view.BaseMVPView;

public interface PresenterMVPFactory<V extends BaseMVPView, P extends BaseMVPPresenter<V>> {

    /** 实现类将根据注解创建Presenter */
    P createMvpPresenter();
}

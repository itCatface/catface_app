package cc.catface.base.core_framework.base_mvp.factory;

import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpView;

public interface PresenterMvpFactory<V extends MvpView, P extends MvpPresenter<V>> {

    /** 实现类将根据注解创建Presenter */
    P createMVPPresenter();
}

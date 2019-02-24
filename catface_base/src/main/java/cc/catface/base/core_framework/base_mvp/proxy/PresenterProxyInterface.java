package cc.catface.base.core_framework.base_mvp.proxy;

import cc.catface.base.core_framework.base_mvp.factory.PresenterMvpFactory;
import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpView;

/**
 * 代理接口
 */
public interface PresenterProxyInterface<V extends MvpView, P extends MvpPresenter<V>> {


    /** 设置创建Presenter的工厂 */
    void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory);


    /** 获取Presenter的工厂类 */
    PresenterMvpFactory<V, P> getPresenterFactory();


    /** 获取创建的Presenter */
    P getMvpPresenter();
}

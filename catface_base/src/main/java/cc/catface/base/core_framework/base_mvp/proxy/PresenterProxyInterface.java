package cc.catface.base.core_framework.base_mvp.proxy;


import cc.catface.base.core_framework.base_mvp.factory.PresenterMVPFactory;
import cc.catface.base.core_framework.base_mvp.presenter.BaseMVPPresenter;
import cc.catface.base.core_framework.base_mvp.view.BaseMVPView;

/**
 * 代理接口
 */
public interface PresenterProxyInterface<V extends BaseMVPView, P extends BaseMVPPresenter<V>> {


    /** 设置创建Presenter的工厂 */
    void setPresenterFactory(PresenterMVPFactory<V, P> presenterFactory);


    /** 获取Presenter的工厂类 */
    PresenterMVPFactory<V, P> getPresenterFactory();


    /** 获取创建的Presenter */
    P getMvpPresenter();
}

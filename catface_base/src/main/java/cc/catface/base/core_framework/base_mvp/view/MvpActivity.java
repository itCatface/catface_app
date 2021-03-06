package cc.catface.base.core_framework.base_mvp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import cc.catface.base.core_framework.BaseFunAct;
import cc.catface.base.core_framework.base_mvp.factory.PresenterMvpFactory;
import cc.catface.base.core_framework.base_mvp.factory.PresenterMvpFactoryImpl;
import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.core_framework.base_mvp.proxy.BaseMvpProxy;
import cc.catface.base.core_framework.base_mvp.proxy.PresenterProxyInterface;

/**
 * exposed property: mPresenter[or getMvpPresenter()]
 */
@Deprecated public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>, B extends ViewDataBinding> extends BaseFunAct implements PresenterProxyInterface<V, P> {


    /** 创建被代理对象[传入默认Presenter的工厂] */
    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.createFactory(getClass()));
    protected P mPresenter;
    protected B mBinding;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = mProxy.getMvpPresenter();
        if (null != savedInstanceState) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
        mProxy.onCreate((V) this, this);
        mBinding = DataBindingUtil.setContentView(this, layoutId());
        super.init();
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
        if (null != mBinding) mBinding.unbind();
    }


    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";

    @Override protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }


    /* 可以实现自己的PresenterMvpFactory工厂 */
    @Override public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    /* 获取自己创建的Presenter工厂 */
    @Override public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    /* V中获取当前V对应绑定的Presenter实例 */
    @Override public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }
}

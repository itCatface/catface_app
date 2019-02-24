package cc.catface.base.core_framework.base_mvp.view;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import cc.catface.base.core_framework.base_mvp.factory.PresenterMvpFactory;
import cc.catface.base.core_framework.base_mvp.factory.PresenterMvpFactoryImpl;
import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.core_framework.base_mvp.proxy.BaseMvpProxy;
import cc.catface.base.core_framework.base_mvp.proxy.PresenterProxyInterface;

/**
 * 所有子类共有的实例：mActivity、mBinding、mPresenter
 */
public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>, B extends ViewDataBinding> extends Fragment implements PresenterProxyInterface<V, P> {
    protected Activity mActivity;       // 当前fragment依附的activity
    protected B mBinding;               // 当前fragment对应的databinding

    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.createFactory(getClass()));      // 创建被代理对象[传入默认Presenter的工厂]
    protected P mPresenter;             // 当前fragment对应的presenter


    @Override public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mPresenter = mProxy.getMvpPresenter();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState) mProxy.onRestoreInstanceState(savedInstanceState);
    }

    @Nullable @Override public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
        initData();
        initAction();
        return mBinding.getRoot();
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProxy.onCreate((V) this, mActivity);
        viewCreated();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";

    @Override public void onSaveInstanceState(@NonNull Bundle outState) {
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


    /**
     * ==============================================================================================================
     * ================================================ 抽象/重写方法 ================================================
     * ==============================================================================================================
     */
    public abstract int layoutId();

    protected void initData() { }

    protected void initAction() { }

    protected void viewCreated() { }
}

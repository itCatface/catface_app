package cc.catface.base.core_framework.base_mvp.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cc.catface.base.core_framework.base_mvp.factory.PresenterMVPFactory;
import cc.catface.base.core_framework.base_mvp.factory.PresenterMVPFactoryImpl;
import cc.catface.base.core_framework.base_mvp.presenter.BaseMVPPresenter;
import cc.catface.base.core_framework.base_mvp.proxy.BaseMVPProxy;
import cc.catface.base.core_framework.base_mvp.proxy.PresenterProxyInterface;

/**
 * exposed property: 1. mActivity 2. mPresenter[or getMvpPresenter()]
 */
public abstract class AbsFragmentID<V extends BaseMVPView, P extends BaseMVPPresenter<V>> extends Fragment implements PresenterProxyInterface<V, P> {


    /** 创建被代理对象[传入默认Presenter的工厂] */
    private BaseMVPProxy<V, P> mProxy = new BaseMVPProxy<>(PresenterMVPFactoryImpl.<V, P>createFactory(getClass()));


    public Activity mActivity;
    protected P mPresenter;


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
        View view = inflater.inflate(layoutId(), container, false);
        ButterKnife.bind(this, view);
        ids(view);
        return view;
    }


    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProxy.onResume((V) this, mActivity);

        viewCreated();
    }


    @Override public void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }


    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }


    /** 可以实现自己PresenterMvpFactory工厂 */
    @Override public void setPresenterFactory(PresenterMVPFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }


    /** 获取自己创建的Presenter工厂 */
    @Override public PresenterMVPFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    /** V中获取当前V对应绑定的Presenter实例 */
    @Override public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }


    /**
     * =========================================================================================================
     * ================================================ 抽象方法 ================================================
     * =========================================================================================================
     */
    public abstract int layoutId();

    public abstract void ids(View v);

    public abstract void viewCreated();
}

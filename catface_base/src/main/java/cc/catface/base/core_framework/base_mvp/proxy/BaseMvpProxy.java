package cc.catface.base.core_framework.base_mvp.proxy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import cc.catface.base.core_framework.base_mvp.factory.PresenterMvpFactory;
import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpView;

/**
 * 代理实现类[用来关联Presenter和View的生命周期-->方便管理]
 */
public class BaseMvpProxy<V extends MvpView, P extends MvpPresenter<V>> implements PresenterProxyInterface<V, P> {

    private static final String PRESENTER_KEY = "presenter_key";

    private PresenterMvpFactory<V, P> mFactory;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttachView;


    public BaseMvpProxy(PresenterMvpFactory<V, P> presenterMvpFactory) {
        this.mFactory = presenterMvpFactory;
    }


    @Override public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        if (mPresenter != null) {
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }
        this.mFactory = presenterFactory;
    }


    @Override public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mFactory;
    }


    @Override public P getMvpPresenter() {
        Log.e("perfect-mvp", "Proxy getMvpPresenter");
        if (mFactory != null) {
            if (mPresenter == null) {
                mPresenter = mFactory.createMVPPresenter();
                mPresenter.onCreatePresenter(mBundle == null ? null : mBundle.getBundle(PRESENTER_KEY));
            }
        }
        Log.e("perfect-mvp", "Proxy getMvpPresenter = " + mPresenter);
        return mPresenter;
    }


    public void onCreate(V mvpView, Activity activity) {
        Log.e("perfect-mvp", "Proxy onCreate");
        if (mPresenter != null && !mIsAttachView) {
            mPresenter.onAttachMvpView(mvpView, activity);
            mIsAttachView = true;
        }
    }


    private void onDetachMvpView() {
        Log.e("perfect-mvp", "Proxy onDetachMvpView = ");
        if (mPresenter != null && mIsAttachView) {
            mPresenter.onDetachMvpView();
            mIsAttachView = false;
        }
    }


    public void onDestroy() {
        Log.e("perfect-mvp", "Proxy onDestroy = ");
        if (mPresenter != null) {
            onDetachMvpView();
            mPresenter.onDestroyPresenter();
            mPresenter = null;
        }
    }


    public Bundle onSaveInstanceState() {
        Log.e("perfect-mvp", "Proxy onSaveInstanceState = ");
        Bundle bundle = new Bundle();
        getMvpPresenter();
        if (mPresenter != null) {
            Bundle presenterBundle = new Bundle();
            //回调Presenter
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY, presenterBundle);
        }
        return bundle;
    }


    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("perfect-mvp", "Proxy onRestoreInstanceState = ");
        Log.e("perfect-mvp", "Proxy onRestoreInstanceState Presenter = " + mPresenter);
        mBundle = savedInstanceState;
    }
}

package cc.catface.base.core_framework.base_mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Date;

import cc.catface.base.core_framework.base_mvp.factory.PresenterMVPFactory;
import cc.catface.base.core_framework.base_mvp.factory.PresenterMVPFactoryImpl;
import cc.catface.base.core_framework.base_mvp.presenter.BaseMVPPresenter;
import cc.catface.base.core_framework.base_mvp.proxy.BaseMVPProxy;
import cc.catface.base.core_framework.base_mvp.proxy.PresenterProxyInterface;
import cc.catface.base.utils.android.common_print.log.TLog;


/**
 * 使用代理模式来代理Presenter的创建、销毁、绑定、解绑以及Presenter的状态保存[其实就是管理Presenter的生命周期]
 */
public abstract class AbsActivity<V extends BaseMVPView, P extends BaseMVPPresenter<V>> extends Activity implements PresenterProxyInterface<V, P> {


    /** 创建被代理对象[传入默认Presenter的工厂] */
    private BaseMVPProxy<V, P> mProxy = new BaseMVPProxy<>(PresenterMVPFactoryImpl.<V, P>createFactory(getClass()));

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TLog.d("V[" + this.hashCode() + "]-->onCreate() at: " + new Date() + " || mProxy=" + mProxy);

        if (null != savedInstanceState)
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));

        mProxy.onResume((V) this, this);
    }


    /*@Override
    protected void onResume() {
        super.onResume();
        Log.e("perfect-mvp","V onResume");
        mProxy.onResume((V) this);
    }*/


    @Override protected void onDestroy() {
        super.onDestroy();
        TLog.d("V[" + this.hashCode() + "]-->onDestroy() at: " + new Date());
        mProxy.onDestroy();
    }


    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TLog.d("V[" + this.hashCode() + "]-->onSaveInstanceState() at: " + new Date());
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }


    @Override public void setPresenterFactory(PresenterMVPFactory<V, P> presenterFactory) {
        TLog.d("V[" + this.hashCode() + "]-->setPresenterFactory() at: " + new Date());
        mProxy.setPresenterFactory(presenterFactory);
    }


    @Override public PresenterMVPFactory<V, P> getPresenterFactory() {
        TLog.d("V[" + this.hashCode() + "]-->getPresenterFactory() at: " + new Date());
        return mProxy.getPresenterFactory();
    }


    @Override public P getMvpPresenter() {
        TLog.d("V[" + this.hashCode() + "]-->getMvpPresenter() at: " + new Date());
        return mProxy.getMvpPresenter();
    }
}

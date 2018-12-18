package cc.catface.base.core_framework.base_mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import butterknife.ButterKnife;
import cc.catface.base.core_framework.base_mvp.factory.PresenterMVPFactory;
import cc.catface.base.core_framework.base_mvp.factory.PresenterMVPFactoryImpl;
import cc.catface.base.core_framework.base_mvp.presenter.BaseMVPPresenter;
import cc.catface.base.core_framework.base_mvp.proxy.BaseMVPProxy;
import cc.catface.base.core_framework.base_mvp.proxy.PresenterProxyInterface;

/**
 * exposed property: mPresenter[or getMvpPresenter()]
 */
public abstract class AbsAppCompatActivity<V extends BaseMVPView, P extends BaseMVPPresenter<V>> extends AppCompatActivity implements PresenterProxyInterface<V, P> {


    /** 创建被代理对象[传入默认Presenter的工厂] */
    private BaseMVPProxy<V, P> mProxy = new BaseMVPProxy<>(PresenterMVPFactoryImpl.<V, P>createFactory(getClass()));
    protected P mPresenter;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = mProxy.getMvpPresenter();
        if (null != savedInstanceState) mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));

        baseInit();
    }


    @Override protected void onResume() {
        super.onResume();
        mProxy.onResume((V) this, this);
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }


    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }


    @Override public void setPresenterFactory(PresenterMVPFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }


    @Override public PresenterMVPFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }


    @Override public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }


    /** ------------------------------------------ 基本方法 ------------------------------------------ */
    void baseInit() {
        setFeature();
        setContentView(layoutId());
        ButterKnife.bind(this);

        create();
    }

    public void setFeature() {

    }


    /** PART_A 布局和初始抽象方法 */
    public abstract int layoutId();

    public abstract void create();


    /** PART_B fragment */
    FragmentManager mFmManager;

    // 获取fragment管理器
    public FragmentManager getBaseFragmentManager() {
        if (mFmManager == null) mFmManager = getSupportFragmentManager();
        return mFmManager;
    }

    // 获取fragment事务管理
    public FragmentTransaction getFragmentTransaction() {
        return getBaseFragmentManager().beginTransaction();
    }

    // 替换一个fragment
    public void replaceFragment(int res, Fragment fragment) {
        replaceFragment(res, fragment, false);
    }

    // 替换fragment并设置是否加入回退栈
    public void replaceFragment(int res, Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);       // 添加转场动画(or setCustomAnimations)

        fragmentTransaction.replace(res, fragment);

        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    // 添加一个fragment
    public void addFragment(int res, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.add(res, fragment);
        fragmentTransaction.commit();
    }

    public void addFragment(int res, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.add(res, fragment, tag);
        fragmentTransaction.commit();
    }

    // 移除一个fragment
    public void removeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    // 显示一个fragment
    public void showFragment(Fragment fragment) {
        if (fragment.isHidden()) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        }
    }

    // 隐藏一个fragment
    public void hideFragment(Fragment fragment) {
        if (!fragment.isHidden()) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.hide(fragment);
            fragmentTransaction.commit();
        }
    }


    /** PART_C 支持方法 */
    // 设置屏幕背景透明效果
    public void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        getWindow().setAttributes(lp);
    }
}

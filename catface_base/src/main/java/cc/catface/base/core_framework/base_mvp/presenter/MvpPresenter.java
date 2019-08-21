package cc.catface.base.core_framework.base_mvp.presenter;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import cc.catface.base.core_framework.base_mvp.view.MvpView;


/**
 * 规定View泛型
 *
 * 定义绑定和解绑的抽象方法让子类实现
 *
 * 对外提供获取View的方法让子类通过该方法获取View
 *
 * 注意[所有子类必须使用public修饰]
 *
 * @desc: exposed property: 1. mView[or getMvpView()] 2. mActivity
 */
public class MvpPresenter<V extends MvpView> {

    /** V层View(可通过get()方法向外提供) */
    protected V mView;
    protected Activity mActivity;


    /** Presenter被创建后调用 */
    public void onCreatePresenter(@Nullable Bundle savedState) { }


    /** 绑定View[持有当前附属的View及对应的上下文] */
    public void onAttachMvpView(V mvpView, Activity activity) {
        mView = mvpView;
        mActivity = activity;
    }


    /** 解绑View */
    public void onDetachMvpView() {
        if (null != mView) mView = null;
    }


    /** Presenter被销毁时调用 */
    public void onDestroyPresenter() { }


    /** 在Presenter意外销毁的时候被调用[时机和Activity、Fragment、View中的onSaveInstanceState()的调用相同] */
    public void onSaveInstanceState(Bundle outState) { }
}

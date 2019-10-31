package cc.catface.base.core_framework.base_normal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxFragment;

import cc.catface.base.core_framework.BaseFunFm;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Deprecated public abstract class NormalFragment<B extends ViewDataBinding> extends BaseFunFm {
    private RxFragment mFm;

    public AppCompatActivity mActivity;
    protected View mRootView;
    protected B mBinding;

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*--->沉浸式*/
        ImmersionBar.with(this).init();
        /*<---*/
        mFm = this;
        mBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false);

        initAction();
        initData();
        createView();
        mRootView = mBinding.getRoot();
        return mRootView;
    }

    public abstract int layoutId();

    protected void initAction() { }

    protected void initData() { }

    protected void createView() { }
}

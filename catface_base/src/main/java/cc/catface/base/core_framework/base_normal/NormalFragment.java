package cc.catface.base.core_framework.base_normal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.trello.rxlifecycle2.components.support.RxFragment;

import cc.catface.base.core_framework.BaseFunctionFm;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class NormalFragment<B extends ViewDataBinding> extends BaseFunctionFm {
    private RxFragment mRxFragment;

    public Activity mActivity;
    protected View mRootView;
    protected B mBinding;

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRxFragment = this;
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

    public abstract void createView();
}

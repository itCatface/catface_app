package cc.catface.base.core_framework.light_mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import java.util.Objects;

import cc.catface.base.core_framework.BaseFunFm;
import cc.catface.ctool.system.TWeakHandler;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class LightFm<P extends LightPresenter, B extends ViewDataBinding> extends BaseFunFm {


    protected AppCompatActivity mActivity;
    protected P mPresenter;
    protected View mRootView;
    protected B mBinding;
    protected TWeakHandler<LightFm> mHandler;


    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Nullable @Override public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(Objects.requireNonNull(inflater), layoutId(), container, false);
        super.init();
        mRootView = mBinding.getRoot();
        return mRootView;
    }

    @Override public void onDestroy() {
        if (null != mHandler) {
            mHandler.clear();
        }
        if (null != mPresenter) {
            mPresenter.onDetachView();
            mPresenter = null;
        }
        if (null != mBinding) {
            mBinding.unbind();
            mBinding = null;
        }
        super.onDestroy();
    }

    public abstract int layoutId();
}

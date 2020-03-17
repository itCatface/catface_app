package cc.catface.base.core_framework.light_mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import cc.catface.base.core_framework.BaseFunAct;
import cc.catface.ctool.system.TWeakHandler;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class LightAct<P extends LightPresenter, B extends ViewDataBinding> extends BaseFunAct implements LightView {

    protected B mBinding;
    protected P mPresenter;
    protected TWeakHandler<LightAct> mHandler;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, layoutId());

        super.init();
    }

    @Override protected void onDestroy() {
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
}

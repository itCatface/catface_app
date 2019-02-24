package cc.catface.base.core_framework.base_normal;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import cc.catface.base.core_framework.BaseFunctionActivity;
import cc.catface.base.utils.android.view.TFontType;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class NormalActivity<B extends ViewDataBinding> extends BaseFunctionActivity {
    protected B mBinding;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TFontType.replaceFont(this, TFontType.Font.CURRENT_SYSTEM_TYPE);
        mBinding = DataBindingUtil.setContentView(this, layoutId());
        super.initOnCreate();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (null != mBinding) mBinding.unbind();
    }
}

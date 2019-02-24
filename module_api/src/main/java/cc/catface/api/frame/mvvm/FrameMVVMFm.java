package cc.catface.api.frame.mvvm;

import android.text.TextUtils;
import android.view.View;

import com.jakewharton.rxbinding.widget.RxTextView;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFragmentFrameBinding;
import cc.catface.base.core_framework.base_normal.NormalFragment;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class FrameMVVMFm extends NormalFragment<ApiFragmentFrameBinding> {
    @Override public int layoutId() {
        return R.layout.api_fragment_frame;
    }

    @Override public void createView() {
        mBinding.btLogin.setText("SIGN IN(by mvvm...)");
    }
}

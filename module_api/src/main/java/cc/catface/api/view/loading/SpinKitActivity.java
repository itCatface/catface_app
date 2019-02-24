package cc.catface.api.view.loading;

import android.view.View;

import com.jakewharton.rxbinding.widget.RxCompoundButton;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivitySpinKitBinding;
import cc.catface.api.view.loading.view_publish.RotatingView;
import cc.catface.base.core_framework.base_normal.NormalActivity;

public class SpinKitActivity extends NormalActivity<ApiActivitySpinKitBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_spin_kit;
    }

    @Override protected void initAction() {
        mBinding.loadingRv2.setShape(RotatingView.SHAPE_CIRCLE);
        RxCompoundButton.checkedChanges(mBinding.cbCtrl).subscribe(isChecked -> {
            mBinding.loadingRv1.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            mBinding.loadingRv2.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            mBinding.loadingWcv.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            mBinding.loadingWv.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            mBinding.loadingCgv.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            mBinding.loadingCv.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
        });
    }

    @Override public void create() {

    }
}

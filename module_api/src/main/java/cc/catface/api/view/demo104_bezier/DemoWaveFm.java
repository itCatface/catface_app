package cc.catface.api.view.demo104_bezier;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityWaveBinding;
import cc.catface.base.core_framework.base_normal.NormalFragment;


public class DemoWaveFm extends NormalFragment<ApiActivityWaveBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_wave;
    }

    @Override public void createView() {
        mBinding.btStartX.setOnClickListener(view ->mBinding. w1v.startAnimX());
       mBinding.btStartY.setOnClickListener(view -> mBinding.w1v.startAnimY());
    }


}

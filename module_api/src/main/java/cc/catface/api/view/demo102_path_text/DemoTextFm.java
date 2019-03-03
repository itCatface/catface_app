package cc.catface.api.view.demo102_path_text;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityTextBinding;
import cc.catface.base.core_framework.base_normal.NormalFragment;

public class DemoTextFm extends NormalFragment<ApiActivityTextBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_text;
    }

    @Override public void createView() {
        mBinding.fl.addView(new CustomView(mActivity, null));
    }
}

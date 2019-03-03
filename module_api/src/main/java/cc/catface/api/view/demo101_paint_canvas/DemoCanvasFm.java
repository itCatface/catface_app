package cc.catface.api.view.demo101_paint_canvas;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityCanvasBinding;
import cc.catface.base.core_framework.base_normal.NormalFragment;

public class DemoCanvasFm extends NormalFragment<ApiActivityCanvasBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_canvas;
    }

    @Override public void createView() {
        mBinding.fl.addView(new CustomView(mActivity, null));
    }

}

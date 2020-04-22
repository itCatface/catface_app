package cc.catface.api.smallfunc;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFmDemoSmallFuncBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.view.action.AntiShakeClickListener;

public class DemoSmallFuncFm extends LightFm<LightPresenter, ApiFmDemoSmallFuncBinding> {

    @Override public int layoutId() {
        return R.layout.api_fm_demo_small_func;
    }

    @SuppressLint("SetTextI18n") @Override protected void initAction() {
        /* 防抖按钮 */
        mBinding.btAntiShake.setOnClickListener(new AntiShakeClickListener() {
            @Override protected void onAntiShakeClick(View view) {
                ((Button) view).setText(System.currentTimeMillis() + "<--当前点击时间");
            }
        });
    }
}

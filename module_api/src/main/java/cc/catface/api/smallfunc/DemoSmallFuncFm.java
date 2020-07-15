package cc.catface.api.smallfunc;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFmDemoSmallFuncBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.java.TPing;
import cc.catface.ctool.java.TPingResultBean;
import cc.catface.ctool.view.action.AntiShakeClickListener;

public class DemoSmallFuncFm extends LightFm<LightPresenter, ApiFmDemoSmallFuncBinding> {

    @Override
    public int layoutId() {
        return R.layout.api_fm_demo_small_func;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initAction() {
        /* 防抖按钮 */
        mBinding.btAntiShake.setOnClickListener(new AntiShakeClickListener() {
            @Override
            protected void onAntiShakeClick(View view) {
                ((Button) view).setText(System.currentTimeMillis() + "<--当前点击时间");
            }
        });
        /* ping主机 */
        mBinding.btPingIp.setOnClickListener(v -> {
            //网络操作应在子线程中操作，避免阻塞UI线程，导致ANR
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TPingResultBean bean = TPing.ping(mBinding.etIp.getText().toString().trim(), 3, 5);
                    Log.i("catface", bean.toString());
                }
            }).start();
        });
    }
}

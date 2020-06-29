package cc.catface.module_apis.mmkv;

import android.view.View;

import com.tencent.mmkv.MMKV;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.context.TSP;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisFragmentMmkvBinding;

public class MMKVFm extends LightFm<LightPresenter, ApisFragmentMmkvBinding> {
    @Override
    public int layoutId() {
        return R.layout.apis_fragment_mmkv;
    }

    @Override
    protected void initAction() {
        mBinding.btClearAll10000.setOnClickListener(v -> {
            TSP.getInstance().clearAll();
            MMKV.defaultMMKV().clearAll();
            mBinding.tvAdd10000Time.setText("数据已清除");
        });
        mBinding.btAdd10000BySP.setOnClickListener(v -> {
            mBinding.pb.setVisibility(View.VISIBLE);
            long l = System.currentTimeMillis();
            new Thread(() -> {
                for (int i = 0; i < 10_00; i++) {
                    TSP.getInstance().setString("key" + i, "value" + i);    // 10_000=>OOM
                }
                mActivity.runOnUiThread(() -> {
                    mBinding.pb.setVisibility(View.GONE);
                    mBinding.tvAdd10000Time.setText("sp: " + (System.currentTimeMillis() - l) + "||" + TSP.getInstance().getString("key0"));
                });
            }).start();
        });
        mBinding.btAdd10000ByMMKV.setOnClickListener(v -> {
            mBinding.pb.setVisibility(View.VISIBLE);
            long l = System.currentTimeMillis();
            new Thread(() -> {
                for (int i = 0; i < 10_00; i++) {
                    MMKV.defaultMMKV().encode("key" + i, "value" + i);
                }
                mActivity.runOnUiThread(() -> {
                    mBinding.pb.setVisibility(View.GONE);
                    mBinding.tvAdd10000Time.setText("mmkv: " + (System.currentTimeMillis() - l) + "||" + MMKV.defaultMMKV().decodeString("key0"));
                });
            }).start();
        });
    }
}
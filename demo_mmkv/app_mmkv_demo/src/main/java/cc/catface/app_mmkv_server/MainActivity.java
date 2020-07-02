package cc.catface.app_mmkv_server;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.tencent.mmkv.MMKV;

import java.util.Random;

import cc.catface.app_mmkv_server.databinding.ActivityMainBinding;
import cc.catface.ctool.context.TSP;
import cc.catface.ctool.context.TToast;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        testSP_MMKV();
    }

    private int mCount = 3_000;

    @SuppressLint("SetTextI18n")
    protected void testSP_MMKV() {
        /* 清空所有sp和protobuf */
        mBinding.btClearAll.setOnClickListener(v -> {
            TSP.getInstance().clearAll();
            MMKV.defaultMMKV().clearAll();
            TToast.showNormal("数据已清空");
        });
        /* 加若干sp */
        mBinding.btAddBySP.setOnClickListener(v -> {
            mCount = Integer.parseInt(mBinding.etCount.getText().toString().trim());
            mBinding.pb.setVisibility(View.VISIBLE);
            long l = System.currentTimeMillis();
            new Thread(() -> {
                for (int i = 0; i < mCount; i++) {
                    TSP.getInstance().setString("key" + i, "value" + i);    // 10_000=>OOM
                }
                runOnUiThread(() -> {
                    mBinding.pb.setVisibility(View.GONE);
                    mBinding.tvAddBySp.setText("sp:" + (System.currentTimeMillis() - l) + "-" + TSP.getInstance().getString("key" + new Random().nextInt(mCount)));
                });
            }).start();
        });
        /* 加若干mmkv */
        mBinding.btAddByMMKV.setOnClickListener(v -> {
            mCount = Integer.parseInt(mBinding.etCount.getText().toString().trim());
            mBinding.pb.setVisibility(View.VISIBLE);
            long l = System.currentTimeMillis();
            new Thread(() -> {
                for (int i = 0; i < mCount; i++) {
                    MMKV.defaultMMKV().encode("key" + i, "value" + i);
                }
                runOnUiThread(() -> {
                    mBinding.pb.setVisibility(View.GONE);
                    mBinding.tvAddByMMKV.setText("mmkv:" + (System.currentTimeMillis() - l) + "-" + MMKV.defaultMMKV().decodeString("key" + new Random().nextInt(mCount)));
                });
            }).start();
        });
        /* 存一个mmkv */
        mBinding.btMMKVSave.setOnClickListener(v -> {
            String key = mBinding.etMMKVKeySave.getText().toString().trim();
            String value = mBinding.etMMKVValueSave.getText().toString().trim();
            MMKV.defaultMMKV().encode(key, value);
        });
        /* 取一个mmkv */
        mBinding.btMMKVGet.setOnClickListener(v -> {
            String key = mBinding.etMMKVKeyGet.getText().toString().trim();
            String value = MMKV.defaultMMKV().decodeString(key, "null");
            mBinding.tvMMKVValueGet.setText(value);
        });
    }
}

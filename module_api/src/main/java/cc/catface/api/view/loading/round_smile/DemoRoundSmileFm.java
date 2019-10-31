package cc.catface.api.view.loading.round_smile;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityRoundSmileBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * https://blog.csdn.net/qq_26331127/article/details/50587914
 */

public class DemoRoundSmileFm extends LightFm<LightPresenter, ApiActivityRoundSmileBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_round_smile;
    }

    @Override protected void initAction() {
        mBinding.btBegin.setOnClickListener(v -> new Thread() {
            @Override public void run() {
                Looper.prepare();
                try {
                    mBinding.view.setProgress(0);
                    while (mBinding.view.getProgress() <= 100) {
                        Thread.sleep(20);
                        mBinding.view.setProgress(mBinding.view.getProgress() + 1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start());
    }

    @Override protected void initView() {

        mBinding.startFail.setOnClickListener(v -> {

            mBinding.startSuccess.setText("do not touch me ");
            mBinding.startFail.setText("正在运行");
            mBinding.startSuccess.setClickable(false);
            mBinding.startFail.setClickable(false);

            new Thread() {
                @Override public void run() {
                    try {
                        mBinding.loadingview.setProgress(0);
                        while (mBinding.loadingview.getProgress() < 100) {
                            Thread.sleep(20);
                            mBinding.loadingview.setProgress(mBinding.loadingview.getProgress() + 1);
                        }
                        mBinding.loadingview.finishFail();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    mHandler.sendEmptyMessage(2);
                }
            }.start();

        });
        mBinding.startSuccess.setOnClickListener(v -> {

            mBinding.startSuccess.setClickable(false);
            mBinding.startFail.setClickable(false);

            mBinding.startFail.setText("do not touch me ");
            mBinding.startSuccess.setText("正在运行");

            new Thread() {
                @Override public void run() {

                    Looper.prepare();
                    try {
                        mBinding.loadingview.setProgress(0);
                        while (mBinding.loadingview.getProgress() < 100) {
                            Thread.sleep(20);
                            mBinding.loadingview.setProgress(mBinding.loadingview.getProgress() + 1);
                        }
                        mBinding.loadingview.finishSuccess();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    mHandler.sendEmptyMessage(1);
                }
            }.start();
        });


    }

    Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    mBinding.startFail.setText("开始失败动画 ");
                    mBinding.startSuccess.setText("开始成功动画");
                    mBinding.startSuccess.setClickable(true);
                    mBinding.startFail.setClickable(true);
                    break;
                case 2:
                    mBinding.startFail.setText("开始失败动画");
                    mBinding.startSuccess.setText("开始成功动画");
                    mBinding.startSuccess.setClickable(true);
                    mBinding.startFail.setClickable(true);
                    break;
                default:
            }
        }
    };
}



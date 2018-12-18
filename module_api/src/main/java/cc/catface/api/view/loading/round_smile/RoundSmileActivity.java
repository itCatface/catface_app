package cc.catface.api.view.loading.round_smile;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import cc.catface.api.R;

/**
 * https://blog.csdn.net/qq_26331127/article/details/50587914
 */

public class RoundSmileActivity extends Activity {


    private cc.catface.api.view.loading.round_smile.SuperLoadingProgress mSuperLoadingProgress;
    private cc.catface.api.view.loading.round_smile.LoadingView loadingView;
    private Button start_success;
    private Button start_fail;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_round_smile);
        loadingView = (cc.catface.api.view.loading.round_smile.LoadingView) findViewById(R.id.view);
        mSuperLoadingProgress = (cc.catface.api.view.loading.round_smile.SuperLoadingProgress) findViewById(R.id.loadingview);
        start_success = (Button) findViewById(R.id.start_success);
        start_fail = (Button) findViewById(R.id.start_fail);
        start_fail.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                start_success.setText("do not touch me ");
                start_fail.setText("正在运行");
                start_success.setClickable(false);
                start_fail.setClickable(false);

                new Thread() {
                    @Override public void run() {
                        try {
                            mSuperLoadingProgress.setProgress(0);
                            while (mSuperLoadingProgress.getProgress() < 100) {
                                Thread.sleep(20);
                                mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + 1);
                            }
                            mSuperLoadingProgress.finishFail();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        mHandler.sendEmptyMessage(2);
                    }
                }.start();

            }
        });
        start_success.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                start_success.setClickable(false);
                start_fail.setClickable(false);

                start_fail.setText("do not touch me ");
                start_success.setText("正在运行");

                new Thread() {
                    @Override public void run() {

                        Looper.prepare();
                        try {
                            mSuperLoadingProgress.setProgress(0);
                            while (mSuperLoadingProgress.getProgress() < 100) {
                                Thread.sleep(20);
                                mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + 1);
                            }
                            mSuperLoadingProgress.finishSuccess();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        mHandler.sendEmptyMessage(1);
                    }
                }.start();
            }
        });


    }

    Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    start_fail.setText("开始失败动画 ");
                    start_success.setText("开始成功动画");
                    start_success.setClickable(true);
                    start_fail.setClickable(true);
                    break;
                case 2:
                    start_fail.setText("开始失败动画");
                    start_success.setText("开始成功动画");
                    start_success.setClickable(true);
                    start_fail.setClickable(true);
                    break;
                default:
            }
        }
    };

    public void click(View v) {
        new Thread() {
            @Override public void run() {
                Looper.prepare();
                try {
                    loadingView.setProgress(0);
                    while (loadingView.getProgress() <= 100) {
                        Thread.sleep(20);
                        loadingView.setProgress(loadingView.getProgress() + 1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}



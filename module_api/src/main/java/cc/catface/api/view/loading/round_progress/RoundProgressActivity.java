package cc.catface.api.view.loading.round_progress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityRoundProgressBinding;
import cc.catface.base.core_framework.base_normal.NormalActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class RoundProgressActivity extends NormalActivity<ApiActivityRoundProgressBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_round_progress;
    }

    private Bitmap bmpDownload;
    private Bitmap bmpPause;

    @Override public void create() {
        initAction();

        mHandler = new Handler(Looper.getMainLooper());
        bmpDownload = BitmapFactory.decodeResource(getResources(), R.drawable.icon_lu_transmission_img);
        bmpPause = BitmapFactory.decodeResource(getResources(), R.drawable.icon_lu_transmission_img);

        postRoundProgressView(0);
        postRoundProgressImageView(0);
    }


    private void postRoundProgressImageView(final float progress) {
        if (progress > 100) {
            return;
        }
        mBinding.rpiv.setCurrentProgress(progress);
        mHandler.postDelayed(new Runnable() {
            @Override public void run() {
                if (isDownloading) postRoundProgressImageView(progress + 100.0f / 100);
            }
        }, mTime / 50);
    }


    private boolean isDownloading = true;

    @Override protected void initAction() {
        mBinding.rpiv.setOnClickListener(v -> {
            if (isDownloading) {
                mBinding.rpiv.setBackgroundResource(R.drawable.icon_lu_transmission_pause);
            } else {
                mBinding.rpiv.setBackgroundResource(R.drawable.icon_lu_transmission_img);
            }
            isDownloading = !isDownloading;
        });

        mBinding.btPause.setOnClickListener(v -> {
            mBinding.rpv.setIcon(bmpDownload);
            flag = true;
        });

        mBinding.btResume.setOnClickListener(v -> {
            mBinding.rpv.setIcon(bmpPause);
            flag = false;
        });
    }

    private Handler mHandler;
    private int mTime = 5 * 1000;
    private boolean flag = false;


    private void postRoundProgressView(float progress) {
        if (progress > 100) {
            return;
        }
        mBinding.rpv.updateProgress(progress);
        mHandler.postDelayed(() -> {
            if (flag) {
                postRoundProgressView(progress / 100);
            } else {
                postRoundProgressView(progress + 100.0f / 100);
            }
        }, mTime / 50);
    }
}

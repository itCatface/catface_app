package cc.catface.api.view.loading.round_progress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityRoundProgressBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoIflyTingjianViewFm extends LightFm<LightPresenter, ApiActivityRoundProgressBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_round_progress;
    }

    private Bitmap bmpDownload;
    private Bitmap bmpPause;

    @Override protected void initView() {
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
        /**  */
        mBinding.ivMove.setOnTouchListener(shopCarSettleTouch);
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


    /**
     *
     */
    private View.OnTouchListener shopCarSettleTouch = new View.OnTouchListener() {
        int lastX, lastY;

        @Override public boolean onTouch(View v, MotionEvent event) {
            int ea = event.getAction();
            DisplayMetrics dm = getResources().getDisplayMetrics();
            int screenWidth = dm.widthPixels;
            //            int screenHeight = dm.heightPixels - 100;//需要减掉图片的高度
            int screenHeight = dm.heightPixels;//需要减掉图片的高度
            switch (ea) {
                case MotionEvent.ACTION_DOWN:
                    lastX = (int) event.getRawX();//获取触摸事件触摸位置的原始X坐标
                    lastY = (int) event.getRawY();
                case MotionEvent.ACTION_MOVE:
                    //event.getRawX();获得移动的位置
                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;
                    int l = v.getLeft() + dx;
                    int b = v.getBottom() + dy;
                    int r = v.getRight() + dx;
                    int t = v.getTop() + dy;

                    //下面判断移动是否超出屏幕
                    if (l < 0) {
                        l = 0;
                        r = l + v.getWidth();
                    }
                    if (t < 0) {
                        t = 0;
                        b = t + v.getHeight();
                    }
                    if (r > screenWidth) {
                        r = screenWidth;
                        l = r - v.getWidth();
                    }
                    if (b > screenHeight) {
                        b = screenHeight;
                        t = b - v.getHeight();
                    }
                    v.layout(l, t, r, b);
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    v.postInvalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    };
}

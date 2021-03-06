package cc.catface.api.view.loading.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import cc.catface.ctool.system.IInterface.ISystemInterface;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class RotatingCircleView extends View {
    private int mColor = Color.argb(255, 221,221,221);
    private final int mDuration = 1_000;

    public RotatingCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private Paint mPaint;
    private Canvas mCanvas;

    private void init() {
        if (null == mPaint) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(mColor);
        }
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;

        drawArea();
    }

    private void drawArea() {
        mCanvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() > getHeight() ? getHeight() / 2 : getWidth() / 2, mPaint);
    }


    ObjectAnimator animRotationX, animRotationY;
    AnimatorSet animatorSet;

    public void startAnim() {
        if (null != animatorSet && animatorSet.isRunning()) return;

        animRotationX = ObjectAnimator.ofFloat(this, "rotationX", 0f, -180f);
        animRotationY = ObjectAnimator.ofFloat(this, "rotationY", 0f, -180f);
        animRotationX.setDuration(mDuration / 2);
        animRotationY.setDuration(mDuration / 2);

        animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animRotationX, animRotationY);
        AnimatorSet.Builder builder = animatorSet.play(animRotationX);
        builder.before(animRotationY);
        animatorSet.start();
        animatorSet.addListener(new ISystemInterface.AnimatorEndListener() {
            @Override public void onAnimationEnd(Animator animator) {
                mHandler.obtainMessage().sendToTarget();
            }
        });
    }


    @SuppressLint("HandlerLeak") private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            startAnim();
        }
    };


    /** 资源回收 */
    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (null != mHandler) mHandler.removeCallbacksAndMessages(null);
        if (null != animatorSet) {
            animatorSet.removeAllListeners();
            animatorSet.pause();
            animatorSet.cancel();
        }
        if (null != animRotationX) animRotationX.cancel();
        if (null != animRotationY) animRotationY.cancel();
    }
}

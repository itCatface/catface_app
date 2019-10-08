package cc.catface.api.view.loading.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import cc.catface.ctool.system.IInterface.ISystemInterface;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DoubleBounceView extends View {
    private final int mDuration = 1_000;

    public DoubleBounceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private View innerCircleView;
    private View outerCircleView;
    private Paint mPaint;
    private Canvas mCanvas;
    private RectF mRectF;


    private void init() {
        if (null == mPaint) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.RED);
        }

        Rect rect = new Rect(getLeft(), getTop(), getRight(), getBottom());
        mRectF = new RectF(rect);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;

        drawArea();
    }


    View clone;
    private void drawArea() {
        mCanvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() > getHeight() ? getHeight() / 2 : getWidth() / 2, mPaint);
        try {
            clone = (View) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        mPaint.setColor(Color.argb(100, 255, 0, 0));
        mCanvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() > getHeight() ? getHeight() / 2 : getWidth() / 2, mPaint);
    }


    ObjectAnimator animInnerScaleX, animInnerScaleY, animOuterScaleX, animOuterScaleY;
    AnimatorSet animatorSet;

    public void startAnim() {
        if (null != animatorSet && animatorSet.isRunning()) return;

        animInnerScaleX = ObjectAnimator.ofFloat(clone, "scaleX", 0f, 0.5f);
        animInnerScaleY = ObjectAnimator.ofFloat(clone, "scaleY", 0f, 0.5f);

        animOuterScaleX = ObjectAnimator.ofFloat(this, "scaleX", 1f, 0.5f);
        animOuterScaleY = ObjectAnimator.ofFloat(this, "scaleY", 1f, 0.5f);

        animInnerScaleX.setDuration(mDuration / 2);
        animInnerScaleY.setDuration(mDuration / 2);
        animOuterScaleX.setDuration(mDuration / 2);
        animOuterScaleY.setDuration(mDuration / 2);

        animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(animInnerScaleX);
        builder.with(animInnerScaleY).before(animOuterScaleX).before(animOuterScaleY);
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
        if (null != animInnerScaleX) animInnerScaleX.cancel();
        if (null != animOuterScaleX) animOuterScaleX.cancel();
    }
}

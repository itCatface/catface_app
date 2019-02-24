package cc.catface.api.view.loading.view_publish;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import cc.catface.api.R;
import cc.catface.base.utils.android.listener.AnimatorEndListener;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 *
 * @desc 简单的绕Z轴旋转
 */
public class RotatingView extends View {
    private final int mDuration = 3_000;

    public RotatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private Paint mPaint;
    private RectF mRectF = new RectF();

    private void init() {
        if (null == mPaint) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(getResources().getColor(R.color.colorAccent));
        }

        post(() -> {
            mRectF = new RectF(new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight()));
            reset();
        });
    }


    private int mCurrentShape = 0;
    public static final int SHAPE_RECTANGLE = 0;
    public static final int SHAPE_CIRCLE = 1;

    public void setShape(int type) {
        mCurrentShape = type;
        reset();
    }

    private void reset() {
        this.clearAnimation();
        this.invalidate();
        this.startAnim();
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mCurrentShape) {
            case SHAPE_RECTANGLE:
                canvas.drawRect(mRectF, mPaint);
                break;
            case SHAPE_CIRCLE:
                canvas.drawOval(mRectF, mPaint);
                break;
        }
    }


    ObjectAnimator animRotationX, animRotationY;
    AnimatorSet animatorSet = new AnimatorSet();

    public void startAnim() {
        if (animatorSet.isRunning()) return;

        animRotationX = ObjectAnimator.ofFloat(this, "rotationX", 0f, -180f);
        animRotationY = ObjectAnimator.ofFloat(this, "rotationY", 0f, -180f);
        animRotationX.setDuration(mDuration / 2);
        animRotationY.setDuration(mDuration / 2);


        animatorSet.playSequentially(animRotationX, animRotationY);
        animatorSet.start();
        animatorSet.addListener(new AnimatorEndListener() {
            @Override public void onAnimationEnd(Animator animation) {
                animation.start();
            }
        });
    }


    /** source recycle */
    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (null != animatorSet) {
            animatorSet.removeAllListeners();
            animatorSet.pause();
            animatorSet.cancel();
        }
        if (null != animRotationX) animRotationX.cancel();
        if (null != animRotationY) animRotationY.cancel();
    }
}

package cc.catface.api.view.demo104_bezier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class Wave1View extends View {

    private Context mCtx;
    private Paint mPaint;
    private Canvas mCanvas;
    private Path mPath;

    public Wave1View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.GREEN);
        mPath = new Path();

        //
        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint2.setStrokeWidth(7);
        mPaint2.setColor(Color.BLUE);
        mPath2 = new Path();
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        drawBezier1();

        drawBezier2();
    }


    /* rQuadTo
            控制点x = 上终点x+控制点x位移
            控制点y = 上终点y+控制点y位移
            终点x = 上终点x+终点x位移
            终点y = 上终点y+终点y位移
     * */
    private void drawBezier1() {
        mPath.moveTo(100, 400);
        mPath.rQuadTo(100, -100, 200, 0);
        mPath.rQuadTo(100, 100, 200, 0);
        mCanvas.drawPath(mPath, mPaint);
    }


    /* 全屏水波纹 */
    private Paint mPaint2;
    private Path mPath2;
    private int mItemWaveLength = 400;
    private int mOriginY = 1000;

    private void drawBezier2() {
        mPath2.reset();
        int halWaveLength = mItemWaveLength / 2;
        mPath2.moveTo(-mItemWaveLength + dx, mOriginY);
        for (int i = -mItemWaveLength; i <= getWidth() + mItemWaveLength; i += mItemWaveLength) {
            mPath2.rQuadTo(halWaveLength / 2, -100, halWaveLength, 0);
            mPath2.rQuadTo(halWaveLength / 2, 100, halWaveLength, 0);
        }
        /* 闭合 */
        mPath2.lineTo(getWidth(), getHeight());
        mPath2.lineTo(0, getHeight());
        mPath2.close();

        mCanvas.drawPath(mPath2, mPaint2);
    }


    private int dx;

    public void startAnimX() {   // 动画长度为一个波长
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(2_000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            dx = (int) animation.getAnimatedValue();
            postInvalidate();
        });
        animator.start();
    }

    public void startAnimY() {
        ValueAnimator animator = ValueAnimator.ofInt(1_000, 2_000);
        animator.setDuration(20_000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            mOriginY = (int) animation.getAnimatedValue();
            postInvalidate();
        });
        animator.start();

    }
}

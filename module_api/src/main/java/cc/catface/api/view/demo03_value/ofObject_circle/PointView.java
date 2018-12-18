package cc.catface.api.view.demo03_value.ofObject_circle;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class PointView extends View {
    private Point mPoint;

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null != mPoint) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, mPoint.getRadius(), paint);
        }
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), new Point(50), new Point((this.getHeight() - 60) / 2));
        animator.addUpdateListener(animation -> {
            mPoint = (Point) animation.getAnimatedValue();
            invalidate();
        });
        animator.setDuration(3_000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

    private class PointEvaluator implements TypeEvaluator<Point> {

        @Override public Point evaluate(float fraction, Point startValue, Point endValue) {
            int radiusStart = startValue.getRadius();
            int radiusEnd = endValue.getRadius();
            int currentValue = (int) (radiusStart + fraction * (radiusEnd - radiusStart));
            return new Point(currentValue);
        }
    }
}

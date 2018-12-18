package cc.catface.api.view.demo04_object;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class PointView extends View {
    private Point mPoint = new Point(100);

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
            canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, mPoint.getRadius(), paint);
        }
    }

    /** 当且仅当动画的只有一个过渡值时，系统才会调用对应属性的get函数来得到动画的初始值 */
    public int getPointRadius() {
        return 50;
    }

    public void setPointRadius(int radius) {
        mPoint.setRadius(radius);
        invalidate();
    }
}

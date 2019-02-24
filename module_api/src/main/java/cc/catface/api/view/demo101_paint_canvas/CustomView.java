package cc.catface.api.view.demo101_paint_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class CustomView extends View {
    private Context mCtx;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        if (null == mPaint) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.BLUE);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(7);
            mPaint.setShadowLayer(10, 12, 12, Color.BLACK); // 无作用--
        }
    }

    private Paint mPaint;

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRGB(200, 200, 200);


        /** -画个圆 */
        canvas.drawCircle(180, 180, 120, mPaint);

        /* 画个一条直线 */
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        canvas.drawLine(350, 140, 500, 160, mPaint);

        /* 画个多条直线 */
        float[] points = {350, 180, 500, 210, 330, 200, 520, 230};
        canvas.drawLines(points, mPaint);

        /* 画几个点 */
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(25);
        canvas.drawPoint(560, 200, mPaint);

        /* 画几个点 */
        float[] points2 = {550, 150, 600, 150};
        canvas.drawPoints(points2, mPaint);

        /* 矩形工具类Rect&RectF */
        Rect rect1 = new Rect(650, 80, 800, 250);
        RectF rectF1 = new RectF(rect1);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF1, mPaint);

        /* 圆角矩形 */
        RectF rectF2 = new RectF(850, 80, 1020, 300);
        mPaint.setColor(Color.GREEN);
        canvas.drawRoundRect(rectF2, 30, 15, mPaint);

        /** -圆形 */
        mPaint.setColor(Color.RED);
        canvas.drawCircle(180, 500, 120, mPaint);

        /* 椭圆 */
        Rect rect2 = new Rect(400, 380, 1020, 620);
        RectF rectF3 = new RectF(rect2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF3, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawOval(rectF3, mPaint);


        /** -弧 */
        RectF rectF4 = new RectF(60, 800, 500, 1100);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(15);
        canvas.drawOval(rectF4, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawArc(rectF4, 35, 80, false, mPaint);  // useCenter为添加两射线边

        RectF rectF5 = new RectF(550, 800, 1020, 1100);
        mPaint.setColor(Color.BLUE);
        canvas.drawOval(rectF5, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawArc(rectF5, 35, 80, true, mPaint);  // useCenter为添加两射线边

        RectF rectF6 = new RectF(60, 1200, 500, 1500);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        canvas.drawOval(rectF6, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawArc(rectF6, 35, 80, false, mPaint);  // useCenter为添加两射线边

        RectF rectF7 = new RectF(580, 1200, 1020, 1500);
        mPaint.setColor(Color.BLUE);
        canvas.drawOval(rectF7, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawArc(rectF7, 35, 80, true, mPaint);  // useCenter为添加两射线边
    }
}

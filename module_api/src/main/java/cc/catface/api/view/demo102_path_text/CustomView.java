package cc.catface.api.view.demo102_path_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
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
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(7);
            //            mPaint.setShadowLayer(10, 12, 12, Color.BLACK);
        }
    }

    private Paint mPaint;

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setPadding(10, 10, 10, 10);

        /* 直线路径 */
        Path path = new Path();
        path.moveTo(10, 10);
        path.lineTo(10, 100);
        path.lineTo(400, 100);
        path.close();
        canvas.drawPath(path, mPaint);

        /* 矩形路径 */
        Path path1 = new Path();
        RectF rectF1 = new RectF(10, 180, 400, 280);
        path1.addRect(rectF1, Path.Direction.CCW);
        Path path2 = new Path();
        RectF rectF2 = new RectF(610, 180, 1000, 280);
        path2.addRect(rectF2, Path.Direction.CW);
        canvas.drawPath(path1, mPaint);
        canvas.drawPath(path2, mPaint);

        String text = "自古多情空余恨，此恨绵绵无绝期...";
        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(20);
        canvas.drawTextOnPath(text, path1, 0, 18, mPaint);
        canvas.drawTextOnPath(text, path2, 0, 28, mPaint);

        /* 圆角矩形路径 */
        Path path3 = new Path();
        RectF rectF3 = new RectF(10, 360, 400, 550);
        float radii[] = {10, 15, 20, 25, 30, 35, 40, 45};
        path3.addRoundRect(rectF3, radii, Path.Direction.CCW);
        canvas.drawPath(path3, mPaint);

        /* 圆形路径 */
        Path path4 = new Path();
        path4.addCircle(805, 455, 95, Path.Direction.CCW);
        canvas.drawPath(path4, mPaint);

        /* 椭圆路径 */
        Path path5 = new Path();
        RectF rectF4 = new RectF(10, 690, 400, 820);
        path5.addOval(rectF4, Path.Direction.CCW);
        canvas.drawPath(path5, mPaint);

        /* 弧形路径 */
        Path path6 = new Path();
        RectF rectF5 = new RectF(805, 690, 920, 820);
        path6.addArc(rectF5, 45, 90);
        canvas.drawPath(path6, mPaint);

        /*-- 文字字体 --*/
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(60);
        Typeface typeface = Typeface.createFromAsset(mCtx.getAssets(), "fonts/hill_house.ttf");
        mPaint.setTypeface(typeface);
        canvas.drawText("Be honest rather clever.", 100, 1200, mPaint);
        canvas.drawText("Being on sea, sail; being on land, settle.", 100, 1300, mPaint);
        canvas.drawText("Be just to all, but trust not all.", 100, 1400, mPaint);
    }
}

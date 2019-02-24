package cc.catface.api.view.demo104_bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class Wave0View extends View {
    private Context mCtx;
    private Paint mPaint;
    private Canvas mCanvas;

    public Wave0View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(7);
        mPaint.setColor(Color.RED);


    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;

        drawBezier1();
    }


    /* quadTo */
    private void drawBezier1() {
        Path path = new Path();
        path.moveTo(0, 200);
        path.quadTo(100, 100, 200, 200);
        path.quadTo(300, 300, 400, 200);
        mCanvas.drawPath(path, mPaint);
    }


}

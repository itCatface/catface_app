package cc.catface.api.view.demo104_bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class PaintView extends View {
    private Context mCtx;
    private Paint mPaint;
    private Canvas mCanvas;
    private Path mPath;

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
    }


    @Override public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                return true;

            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                postInvalidate();
                return true;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;

        drawPath();
    }


    private void drawPath() {
        mCanvas.drawPath(mPath, mPaint);
    }


    public void reset() {
        mPath.reset();
        invalidate();
    }
}

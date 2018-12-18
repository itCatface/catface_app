package cc.catface.api.view.demo104_bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class PaintBezierView extends View {
    private Context mCtx;
    private Paint mPaint, mPaintEraser;
    private Canvas mCanvas;
    private Path mPath;


    private Paint.Style mStyle = Paint.Style.STROKE;
    private float mStrokeWidth = 5;
    private int mColor = Color.BLUE;

    public PaintBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        initEraser();

        setProperties();
    }

    private void initEraser() {
        mPaintEraser = new Paint();
        mPaintEraser.setAlpha(0);
        mPaintEraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaintEraser.setAntiAlias(true);
        mPaintEraser.setDither(true);
        mPaintEraser.setStyle(Paint.Style.STROKE);
        mPaintEraser.setStrokeJoin(Paint.Join.ROUND);
        mPaintEraser.setStrokeWidth(10);
    }


    private float mPreX, mPreY, mEndX, mEndY;

    @Override public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;

            case MotionEvent.ACTION_MOVE:
                mEndX = (mPreX + event.getX()) / 2;
                mEndY = (mPreY + event.getY()) / 2;
                mPath.quadTo(mPreX, mPreY, mEndX, mEndY);
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                break;

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
        postInvalidate();
    }


    /** 公共方法 */
    public void setPenColor(int color) {
        this.mColor = color;
        setProperties();
    }

    public void setStyle(Paint.Style style) {
        this.mStyle = style;
        setProperties();
    }

    public void setPenWidth(float width) {
        this.mStrokeWidth = width;
        setProperties();
    }

    private void setProperties() {
        mPaint.setStyle(mStyle);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(mColor);
    }


    public void reset() {
        mPath.reset();
        postInvalidate();
    }
}

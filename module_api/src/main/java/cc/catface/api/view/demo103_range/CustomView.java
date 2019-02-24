package cc.catface.api.view.demo103_range;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class CustomView extends View {
    private Context mCtx;
    private Canvas mCanvas;
    private Paint mPaint;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;

        drawRegion1();

        /** 区域的合并、交叉等操作 */
        drawRegion2();
    }


    private void drawRegion1() {
        /* region */
        Region region = new Region(10, 10, 120, 120);
        RegionIterator regionIterator0 = new RegionIterator(region);
        Rect r0 = new Rect();

        while (regionIterator0.next(r0)) {
            mCanvas.drawRect(r0, mPaint);
        }


        /* api_set */
        region.set(170, 10, 300, 160);  // set即替换为新区域
        RegionIterator regionIterator1 = new RegionIterator(region);
        Rect r1 = new Rect();

        while (regionIterator1.next(r1)) {
            mCanvas.drawRect(r1, mPaint);
        }


        /* setPath */
        mPaint.setColor(Color.GREEN);
        Path path0 = new Path();
        RectF rectF0 = new RectF(350, 10, 650, 200);
        path0.addOval(rectF0, Path.Direction.CCW);
        region.setPath(path0, new Region(350, 10, 650, 160));
        RegionIterator regionIterator2 = new RegionIterator(region);

        Rect r2 = new Rect();
        while (regionIterator2.next(r2)) {
            mCanvas.drawRect(r2, mPaint);
        }


        /* RegionIterator[矩形集枚举区域] */
        mPaint.setStyle(Paint.Style.STROKE);
        Path path1 = new Path();
        RectF rectF1 = new RectF(700, 10, 1070, 500);
        path1.addOval(rectF1, Path.Direction.CCW);
        region.setPath(path1, new Region(700, 10, 1070, 490));
        RegionIterator regionIterator3 = new RegionIterator(region);
        Rect r3 = new Rect();
        while (regionIterator3.next(r3)) {
            mCanvas.drawRect(r3, mPaint);
        }
    }


    private void drawRegion2() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);

        Rect rect0 = new Rect(350, 700, 650, 1500);
        Rect rect1 = new Rect(150, 950, 950, 1250);
        Region region0 = new Region(rect0);
        Region region1 = new Region(rect1);

        region0.op(region1, Region.Op.XOR);

        RegionIterator regionIterator = new RegionIterator(region0);
        Rect r = new Rect();
        while (regionIterator.next(r)) {
            mCanvas.drawRect(r, mPaint);
        }
    }
}

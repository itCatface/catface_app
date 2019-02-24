package cc.catface.api.view.loading.round_progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import cc.catface.api.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class RoundProgressImageView extends ImageView {
    private final int DEFAULT_EX_RING_WIDTH = 5;
    private int mExRingWidth = DEFAULT_EX_RING_WIDTH;


    public RoundProgressImageView(Context context) {
        this(context, null);
    }

    public RoundProgressImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RoundProgressImageView);
        mExRingWidth = typedArray.getInt(R.styleable.RoundProgressImageView_ex_ring_width, DEFAULT_EX_RING_WIDTH);
        typedArray.recycle();

        init();
    }


    private Paint mPaint;

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }


    private float mCurrentProgress;

    public void setCurrentProgress(float progress) {
        this.mCurrentProgress = progress;
        postInvalidate();
    }

    private float getCurrentAngle() {
        return mCurrentProgress * 360 / 100;
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float left = mExRingWidth / 2.0f;
        float top = mExRingWidth / 2.0f;
        float right = getWidth() - mExRingWidth / 2.0f;
        float bottom = getHeight() - mExRingWidth / 2.0f;
        RectF oval = new RectF(left, top, right, bottom);
        mPaint.setColor(Color.parseColor("#4285F4"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mExRingWidth);
        canvas.drawArc(oval, -90, getCurrentAngle(), false, mPaint);
    }
}

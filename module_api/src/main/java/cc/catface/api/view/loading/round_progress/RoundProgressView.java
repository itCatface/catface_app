package cc.catface.api.view.loading.round_progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import cc.catface.api.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class RoundProgressView extends View {

    /** 各个属性 */
    private final int DEFAULT_BACKGROUND_COLOR = Color.GRAY;
    private final int DEFAULT_BORDER_COLOR = Color.BLUE;
    private final int DEFAULT_BORDER_WIDTH = 1;
    private final float DEFAULT_ICON_SCALE = 2.0f / 8;
    private final int DEFAULT_MILLISECOND = 100;
    private int mBackgroundColor = DEFAULT_BACKGROUND_COLOR;
    private int mBorderColor = DEFAULT_BORDER_COLOR;
    private int mBorderWidth = DEFAULT_BORDER_WIDTH;
    private Bitmap mIcon;
    private float mIconScale = DEFAULT_ICON_SCALE;
    private int mMillisecond = DEFAULT_MILLISECOND;
    private int mCurrentProgress = 0;


    /** 老东西 */
    private Matrix mMatrix;
    private Paint mPaint;

    public RoundProgressView(Context context) {
        this(context, null);
    }

    public RoundProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        if (null == attrs) return;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RoundProgressView);
        mBackgroundColor = typedArray.getColor(R.styleable.RoundProgressView_background_color, DEFAULT_BACKGROUND_COLOR);
        mBorderColor = typedArray.getColor(R.styleable.RoundProgressView_border_color, DEFAULT_BORDER_COLOR);
        mBorderWidth = typedArray.getInt(R.styleable.RoundProgressView_border_width, DEFAULT_BORDER_WIDTH);
        mMillisecond = typedArray.getInt(R.styleable.RoundProgressView_millisecond, DEFAULT_MILLISECOND);
        int iconID = typedArray.getResourceId(R.styleable.RoundProgressView_icon_resource, 0);
        if (0 != iconID) mIcon = BitmapFactory.decodeResource(getResources(), iconID);
        float scale = typedArray.getFloat(R.styleable.RoundProgressView_icon_scale, DEFAULT_ICON_SCALE);
        setIconScale(scale);
        typedArray.recycle();

        init();
    }


    private void init() {

    }


    public void setIcon(Bitmap bitmap) {
        mIcon = bitmap;
    }

    public void setIconScale(float scale) {
        mIconScale = scale;
        mMatrix = null;
    }


    public void updateProgress(float value) {
        if (value < 0 || value > 100) throw new IllegalStateException(value + " is invalid progress!");
        mCurrentAngle = value * (mEndAngle - mStartAngle) / 100;
        if (value >= 100) setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.api_icon_lu_transmission_img));
        postInvalidateDelayed(0);
    }


    /**  */
    private float mStartAngle = 0.0f;
    private float mEndAngle = 360.0f;
    private float mCurrentAngle = mStartAngle;

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float paddingLeft = getPaddingLeft();
        float paddingRight = getPaddingRight();
        float paddingTop = getPaddingTop();
        float paddingBottom = getPaddingBottom();

        //draw the background
        float left = paddingLeft + mBorderWidth - 0.1f;
        float top = paddingTop + mBorderWidth - 0.1f;
        float right = getWidth() - paddingRight - mBorderWidth + 0.1f;
        float bottom = getHeight() - paddingBottom - mBorderWidth + 0.1f;
        RectF oval = new RectF(left, top, right, bottom);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(mBackgroundColor);
        mPaint.setStrokeWidth(0);
        canvas.drawArc(oval, 0, 360, true, mPaint);

        //        if (mDrawRoundProgress) {

        //draw the progress
        left = paddingLeft + mBorderWidth / 2.0f;
        top = paddingTop + mBorderWidth / 2.0f;
        right = getWidth() - paddingRight - mBorderWidth / 2.0f;
        bottom = getHeight() - paddingBottom - mBorderWidth / 2.0f;
        oval = new RectF(left, top, right, bottom);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setColor(mBorderColor);

        canvas.drawArc(oval, -90, mCurrentAngle, false, mPaint);

        if (null == mIcon || mIcon.isRecycled()) {
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float fontHeight = fontMetrics.bottom - fontMetrics.top;
            float textBaseY = getHeight() / 2.0f + fontHeight / 2 - fontMetrics.bottom;
            canvas.drawText("无图", getWidth() / 2.0f, textBaseY, mPaint);
            return;
        }
        if (null == mMatrix) {
            mMatrix = new Matrix();
            mMatrix.postScale(mIconScale, mIconScale);

            /*TODO*/
            //            float dx = (getWidth() - mIcon.getWidth()) / 2.0f;
            //            float dy = (getHeight() - mIcon.getHeight()) / 2.0f;

            float dx = (getWidth() * (1 - mIconScale)) / 2.5f;
            float dy = (getHeight() * (1 - mIconScale)) / 2.5f;
            Log.d("root", "进度圈图标位置：" + mIconScale + " || " + getWidth() + " || " + getHeight() + " == " + mIcon.getWidth() + " || " + mIcon.getHeight());
            mMatrix.postTranslate(dx, dy);
        }
        canvas.drawBitmap(mIcon, mMatrix, mPaint);
    }
}

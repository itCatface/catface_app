package cc.catface.api.multi_finger.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class MultiTouchEditText extends EditText {
    public MultiTouchEditText(Context context) {
        super(context);
    }

    public MultiTouchEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiTouchEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private float x1, y1, x2, y2;
    private double mInitDistance = 0;
    private double mCtrlDistance = 1;    // 手势响应范围


    @SuppressLint("ClickableViewAccessibility") @Override public boolean onTouchEvent(MotionEvent event) {
        int nCnt = event.getPointerCount();
        if((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN && 2 == nCnt) {
            x1 = event.getX(0);
            y1 = event.getY(0);
            x2 = event.getX(1);
            y2 = event.getY(1);

            int xLen = Math.abs((int) (x2 - x1));
            int yLen = Math.abs((int) (y2 - y1));
            mInitDistance = Math.sqrt((double) xLen * xLen + (double) yLen * yLen);
            //        } else if((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP /*|| action == MotionEvent.ACTION_MOVE || action == MotionEvent.ACTION_CANCEL*/ && 2 == nCnt) {
        } else if((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP /*|| action == MotionEvent.ACTION_MOVE*/ || (event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_CANCEL && 2 == nCnt) {
            float _x1 = event.getX(0);
            float _y1 = event.getY(0);
            float _x2 = event.getX(1);
            float _y2 = event.getY(1);

            int xLen = Math.abs((int) (_x2 - _x1));
            int yLen = Math.abs((int) (_y2 - _y1));
            double mFinalDistance = Math.sqrt((double) xLen * xLen + (double) yLen * yLen);
            if(mFinalDistance - mInitDistance > mCtrlDistance) {
                if(null != mCallback) mCallback.event(true);
            } else if(mFinalDistance - mInitDistance < - mCtrlDistance) {
                if(null != mCallback) mCallback.event(false);
            }
        }

        return true;
    }


    /** 放缩监听 */
    public interface Callback {
        void event(boolean isZoomLarge);
    }

    private Callback mCallback;

    public void setCallback(Callback callback) {
        if(null != mCallback) mCallback = null;
        mCallback = callback;
    }
}

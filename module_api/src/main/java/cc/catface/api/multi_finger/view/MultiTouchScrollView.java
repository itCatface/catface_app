package cc.catface.api.multi_finger.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class MultiTouchScrollView extends ScrollView {
    public MultiTouchScrollView(Context context) {
        super(context);
    }

    public MultiTouchScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiTouchScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override public boolean onInterceptTouchEvent(MotionEvent event) {
        int nCnt = event.getPointerCount();
        if(nCnt == 2) {
            return false;
        } else {
            return super.onInterceptTouchEvent(event);
        }
    }
}

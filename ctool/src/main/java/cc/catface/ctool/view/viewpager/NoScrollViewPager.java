package cc.catface.ctool.view.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class NoScrollViewPager extends ViewPager {

    /** true-->不支持滑动切页 */
    private boolean noScroll = true;

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override public boolean onTouchEvent(MotionEvent arg0) {
        if (noScroll) return false;
        else return super.onTouchEvent(arg0);
    }

    @Override public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll) return false;
        else return super.onInterceptTouchEvent(arg0);
    }

    @Override public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    /** false-->切页无滑动效果 */
    @Override public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

}
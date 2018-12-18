package cc.catface.base.utils.android.view.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/*TODO [delete]*/
public class DragBounceListView extends ListView {

    private static final int MAX_OVER_SCROLL_DISTANCE = 200;

    private Context mCtx;
    private int mMaxYOverScrollDistance;

    public DragBounceListView(Context context) {
        super(context);
        mCtx = context;
        initBounceListView();
    }

    public DragBounceListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        initBounceListView();
    }

    public DragBounceListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mCtx = context;
        initBounceListView();
    }

    private void initBounceListView() {
        // get the density of the screen and do some maths with it on the max overscroll distance
        // variable so that you get similar behaviors no matter what the screen size

        final DisplayMetrics metrics = mCtx.getResources().getDisplayMetrics();
        final float density = metrics.density;

        mMaxYOverScrollDistance = (int) (density * MAX_OVER_SCROLL_DISTANCE);

        /**
         * @desc 上面的mMaxYOverscrollDistance有点问题，直接改成MAX_OVERSCROLLY_DISTANCE效果才出来。。
         */
        mMaxYOverScrollDistance = MAX_OVER_SCROLL_DISTANCE;
    }


    @Override protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        /**
         * @desc 摩擦摩擦
         */
        if (!isTouchEvent) { // 禁止惯性滑动
            if ((scrollY < 0 && deltaX < 0) || (scrollY > getHeight() && deltaX > 0)) {
                deltaY = 0;
            }
        }
        // This is where the magic happens, we have replaced the incoming maxOverScrollY with our own custom variable mMaxYOverScrollDistance;
        return super.overScrollBy(deltaX, (deltaY + 1) / 2, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxYOverScrollDistance, isTouchEvent);
    }
}
package cc.catface.work_demo.swipe_change_page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cc.catface.ctool.system.TLog;

/**
 * Created by Administrator on 2016/8/29 0029.
 */
public class HorizontalVerticalPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
    private final String TAG = "qwer1234";

    private static final int FLING_MIN_DISTANCE = 20;
    private Context mCtx;
    private List<View> mViews;
    private List<Fragment> list;
    private OnTouchListener mOnTouchListener;
    private int mPosition = 0;

    HorizontalVerticalPagerAdapter(@NonNull FragmentManager fm, Context ctx, List<View> view, List<Fragment> list) {
        super(fm);
        mCtx = ctx;
        mViews = view;
        this.list = list;
    }


    void setOnTouchListener(OnTouchListener onTouchListener) {
        this.mOnTouchListener = onTouchListener;
    }

    int getPosition() {
        return mPosition;
    }

    private void setPosition(int position) {
        this.mPosition = position;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((Fragment) object).getView();
    }


    @SuppressLint("ClickableViewAccessibility") @NonNull @Override public Fragment getItem(int position) {
        final GestureDetector detector = new GestureDetector(mCtx, new GestureListener());

        Fragment fragment = list.get(position);


        for (View view : mViews) {
            TLog.d("rrrrr", "各个fragment的rootview： " + view);
//            view.setOnTouchListener((v, event) -> {
//                detector.onTouchEvent(event);
//                return true;
//            });
        }

        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) { }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

    @Override
    public void onPageSelected(int position) {
        setPosition(position);
        TLog.d(TAG, "position-----" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class GestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            TLog.d(TAG, "onDown-----" + getActionName(e.getAction()));
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            TLog.d(TAG, "onShowPress-----" + getActionName(e.getAction()));
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            TLog.d(TAG, "onSingleTapUp-----" + getActionName(e.getAction()));
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            TLog.d(TAG, "onScroll-----"
                    + getActionName(e2.getAction()) + ",(" + e1.getX() + "," + e1.getY() + ") ,("
                    + e2.getX() + "," + e2.getY() + ")");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            TLog.d(TAG, "onLongPress-----" + getActionName(e.getAction()));
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            TLog.d(TAG, "onFling-----" + getActionName(e2.getAction()) + ",(" + e1.getX() + "," + e1.getY() + ") ,("
                    + e2.getX() + "," + e2.getY() + ")");

            float detly_x = e1.getX() - e2.getX();
            float detly_y = e1.getY() - e2.getY();

            if (Math.abs(detly_x) > Math.abs(detly_y)) {// 水平方向滑动
                int offsetPosition = 0;
                if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE) {
                    offsetPosition = 1;
                    TLog.d(TAG, "onFling----- 向左滑动");
                } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE) {
                    offsetPosition = -1;
                    TLog.d(TAG, "onFling----- 向右滑动");
                }
                mOnTouchListener.onHorizontalFling(offsetPosition);
            } else {// 垂直方向滑动
                int offsetPosition = 0;
                if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE) {
                    offsetPosition = 1;
                    TLog.d(TAG, "onFling----- 向上滑动");
                } else if (e2.getY() - e1.getY() > FLING_MIN_DISTANCE) {
                    offsetPosition = -1;
                    TLog.d(TAG, "onFling----- 向下滑动");
                }
                mOnTouchListener.onVerticalFling(offsetPosition);
            }

            return false;
        }

        private String getActionName(int action) {
            String name = "";
            switch (action) {
                case MotionEvent.ACTION_DOWN: {
                    name = "ACTION_DOWN";
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    name = "ACTION_MOVE";
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    name = "ACTION_UP";
                    break;
                }
                default:
                    break;
            }
            return name;
        }
    }

    /**
     * 注意，这个是自定义的OnTouchListener，不是view的
     */
    public interface OnTouchListener {
        void onVerticalFling(int offsetPosition);

        void onHorizontalFling(int offsetPosition);
    }
}

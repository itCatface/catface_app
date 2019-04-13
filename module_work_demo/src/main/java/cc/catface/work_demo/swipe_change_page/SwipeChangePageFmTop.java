package cc.catface.work_demo.swipe_change_page;

import android.graphics.Color;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.work_demo.R;
import cc.catface.work_demo.databinding.WorkDemoFmSwipeChangePageTempBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SwipeChangePageFmTop extends NormalFragment<WorkDemoFmSwipeChangePageTempBinding> {
    private View mView;

    @Override public int layoutId() {
        return R.layout.work_demo_fm_swipe_change_page_temp;
    }

    @Override public void createView() {
        mView = mBinding.getRoot();
        mBinding.tvTemp.setBackgroundColor(Color.YELLOW);
        mBinding.tvTemp.setText("page-top");
    }

    @Override public void onResume() {
        super.onResume();
        GestureDetector gestureDetector = new GestureDetector(getActivity(), new SwipeChangePageFmTop.MyOnGestureListener());
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    public class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if ((e2.getY() - e1.getY() > 50) && Math.abs(velocityY) > 200) {
                TLog.d("rrrrr", "下滑...");
                ((SwipeChangePageActivity) getActivity()).setViewPager(true, 2);
                return true;
            }
            return false;
        }
    }


    public View view() {
        return mView;
    }
}

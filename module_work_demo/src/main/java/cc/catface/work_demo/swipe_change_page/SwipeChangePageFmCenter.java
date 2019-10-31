package cc.catface.work_demo.swipe_change_page;

import android.graphics.Color;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.work_demo.R;
import cc.catface.work_demo.databinding.WorkDemoFmSwipeChangePageTempBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SwipeChangePageFmCenter extends LightFm<LightPresenter, WorkDemoFmSwipeChangePageTempBinding> {

    @Override public int layoutId() {
        return R.layout.work_demo_fm_swipe_change_page_temp;
    }

    @Override protected void initView() {
        mBinding.tvTemp.setBackgroundColor(Color.DKGRAY);
        mBinding.tvTemp.setText("page-center");


    }

    @Override public void onResume() {
        super.onResume();
        GestureDetector gestureDetector = new GestureDetector(getActivity(), new MyOnGestureListener());
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    public View view() {
        TLog.d("rrrrr", "center--view(): " + mRootView);
        return mRootView;
    }


    public class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            int offsetPosition = 0;

            if ((e1.getX() - e2.getX() > 50) && Math.abs(velocityX) > 200) {
                TLog.d("rrrrr", "左滑...");
                offsetPosition = 2;
                ((SwipeChangePageActivity) getActivity()).setViewPager(false, offsetPosition);
                return true;
            } else if ((e2.getX() - e1.getX() > 50) && Math.abs(velocityX) > 200) {
                TLog.d("rrrrr", "右滑...");
                offsetPosition = -1;
                ((SwipeChangePageActivity) getActivity()).setViewPager(false, offsetPosition);
                return true;
            } else if ((e1.getY() - e2.getY() > 50) && Math.abs(velocityY) > 200) {
                TLog.d("rrrrr", "上滑...");
                offsetPosition = 1;
                ((SwipeChangePageActivity) getActivity()).setViewPager(true, offsetPosition);
                return true;
            } else if ((e2.getY() - e1.getY() > 50) && Math.abs(velocityY) > 200) {
                TLog.d("rrrrr", "下滑...");
                offsetPosition = -2;
                ((SwipeChangePageActivity) getActivity()).setViewPager(true, offsetPosition);
                return true;
            }
            return false;
        }
    }

}

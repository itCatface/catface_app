package cc.catface.work_demo.swipe_change_page;

import android.graphics.Color;
import android.view.View;

import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.work_demo.R;
import cc.catface.work_demo.databinding.WorkDemoFmSwipeChangePageTempBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SwipeChangePageFmLeft extends NormalFragment<WorkDemoFmSwipeChangePageTempBinding> {
    @Override public int layoutId() {
        return R.layout.work_demo_fm_swipe_change_page_temp;
    }

    @Override public void createView() {
        mBinding.tvTemp.setBackgroundColor(Color.RED);
        mBinding.tvTemp.setText("page-left");
    }

    public View view() {
        TLog.d("rrrrr", "view(): " + mRootView);
        return mRootView;
    }


}

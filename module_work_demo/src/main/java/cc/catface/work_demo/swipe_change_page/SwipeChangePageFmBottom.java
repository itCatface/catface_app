package cc.catface.work_demo.swipe_change_page;

import android.graphics.Color;
import android.view.View;

import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.work_demo.R;
import cc.catface.work_demo.databinding.WorkDemoFmSwipeChangePageTempBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SwipeChangePageFmBottom extends NormalFragment<WorkDemoFmSwipeChangePageTempBinding> {
    private View mView;
    @Override public int layoutId() {
        return R.layout.work_demo_fm_swipe_change_page_temp;
    }

    @Override public void createView() {
        mView = mBinding.getRoot();
        mBinding.tvTemp.setBackgroundColor(Color.GREEN);
        mBinding.tvTemp.setText("page-bottom");
    }


    public View view() {
        return mView;
    }
}

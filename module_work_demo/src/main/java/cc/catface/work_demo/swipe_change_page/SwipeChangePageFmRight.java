package cc.catface.work_demo.swipe_change_page;

import android.graphics.Color;
import android.view.View;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.work_demo.R;
import cc.catface.work_demo.databinding.WorkDemoFmSwipeChangePageTempBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SwipeChangePageFmRight extends LightFm<LightPresenter, WorkDemoFmSwipeChangePageTempBinding> {

    private View mView;

    @Override public int layoutId() {
        return R.layout.work_demo_fm_swipe_change_page_temp;
    }

    @Override protected void initView() {
        mView = mBinding.getRoot();
        mBinding.tvTemp.setBackgroundColor(Color.BLUE);
        mBinding.tvTemp.setText("page-right");
    }


    public View view() {
        return mView;
    }
}

package cc.catface.module_apis.half_scroll;

import android.view.View;

import com.yinglan.scrolllayout.ScrollLayout;

import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisActivityHalfScrollBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class HalfScrollFm extends NormalFragment<ApisActivityHalfScrollBinding> {
    @Override public int layoutId() {
        return R.layout.apis_activity_half_scroll;
    }

    @Override protected void createView() {
        /* 控制转写文本显示 */
        mBinding.sl.setVisibility(View.VISIBLE);
        mBinding.sl.setOnScrollChangedListener(mOnScrollChangedListener);
        mBinding.sl.getBackground().setAlpha(0);
    }

    /* 转写文本拖拽 */
    private ScrollLayout.OnScrollChangedListener mOnScrollChangedListener = new ScrollLayout.OnScrollChangedListener() {
        @Override public void onScrollProgressChanged(float currentProgress) {
            if (currentProgress >= 0) {
                float percent = 255 * currentProgress;
                if (percent > 255) {
                    percent = 255;
                } else if (percent <= 0) {
                    percent = 255;
                }
                mBinding.sl.getBackground().setAlpha(255 - (int) percent);
            }
        }

        @Override public void onScrollFinished(ScrollLayout.Status currentStatus) {
            if (currentStatus.equals(ScrollLayout.Status.EXIT)) {
                getActivity().finish();
            }
        }

        @Override public void onChildScroll(int top) { }
    };
}

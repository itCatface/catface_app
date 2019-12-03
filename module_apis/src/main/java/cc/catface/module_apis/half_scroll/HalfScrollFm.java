package cc.catface.module_apis.half_scroll;

import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisActivityHalfScrollBinding;
import cc.catface.scroll.ScrollLayout;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class HalfScrollFm extends LightFm<LightPresenter, ApisActivityHalfScrollBinding> {

    @Override public int layoutId() {
        return R.layout.apis_activity_half_scroll;
    }

    @Override protected void initView() {
        initToolBar();
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

    /** tool bar */
    private void initToolBar() {
        Toolbar toolbar = mBinding.iTbHalfScroll.tbTitle;
        mActivity.setSupportActionBar(toolbar);
        ActionBar bar = mActivity.getSupportActionBar();
        if (null != bar) {
            bar.setDisplayShowHomeEnabled(true);
            bar.setTitle("half scroll示例");
        }
        toolbar.setNavigationIcon(R.mipmap.flaticon_back);
        toolbar.setNavigationOnClickListener(v -> mActivity.finish());
    }
}

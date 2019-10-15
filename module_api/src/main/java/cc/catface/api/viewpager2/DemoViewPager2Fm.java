package cc.catface.api.viewpager2;

import android.os.Message;
import android.os.SystemClock;

import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFragmentViewPager2Binding;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.ctool.system.TWeakHandler;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoViewPager2Fm extends NormalFragment<ApiFragmentViewPager2Binding> implements TWeakHandler.MessageListener {

    private TWeakHandler<DemoViewPager2Fm> mHandler;

    @Override public void handleMessage(Message msg) {
        if (msg.what == WHAT_PLAY) {
            mBinding.vp21.setCurrentItem(mBinding.vp21.getCurrentItem() + 1);
        }
    }

    @Override public int layoutId() {
        return R.layout.api_fragment_view_pager_2;
    }

    private List<Integer> mDatas = new ArrayList<>();

    {
        mDatas.add(android.R.color.holo_red_dark);
        mDatas.add(android.R.color.holo_purple);
        mDatas.add(android.R.color.holo_blue_dark);
        mDatas.add(android.R.color.holo_green_light);
    }

    @Override protected void initData() {
        mHandler = new TWeakHandler<>(this);
    }

    private final int WHAT_PLAY = 0x00;
    private boolean isAutoPlay = true;
    private int mDuration = 3_000;

    @Override protected void createView() {
        /** 横向ViewPager */
        /* 控制切页 */
        mBinding.vp21.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                mBinding.tvVp21Title.setText("当前页：" + position % mDatas.size());
            }

            @Override public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager2.SCROLL_STATE_IDLE:
                    case ViewPager2.SCROLL_STATE_SETTLING:
                        isAutoPlay = true;
                        break;

                    case ViewPager2.SCROLL_STATE_DRAGGING:
                        isAutoPlay = false;
                        break;

                }
            }
        });
        /* 翻页效果 */
        float MIN_SCALE = 0.75f;
        mBinding.vp21.setPageTransformer((view, position) -> {
            int pageWidth = view.getWidth();
            if (position < -1) {
                view.setAlpha(0);
            } else if (position <= 0) {
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);
            } else if (position <= 1) {
                view.setAlpha(1 - position);
                view.setTranslationX(pageWidth * -position);
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else {
                view.setAlpha(0);
            }
        });
        mBinding.vp21.setAdapter(new ViewPager2Adapter(mDatas));
        mBinding.vp21.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % mDatas.size());
        new Thread(() -> {
            while (true) {
                SystemClock.sleep(mDuration);
                if (isAutoPlay) mHandler.sendEmptyMessage(WHAT_PLAY);
            }
        }).start();


        /** 纵向ViewPager */
        mBinding.vp22.setAdapter(new ViewPager2Adapter(mDatas));
    }


}

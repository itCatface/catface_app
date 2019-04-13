package cc.catface.work_demo.swipe_change_page;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.work_demo.R;
import cc.catface.work_demo.databinding.ActivityVercitalPagerBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SwipeChangePageActivity extends NormalActivity<ActivityVercitalPagerBinding> {
    @Override public int layoutId() {
        return R.layout.activity_vercital_pager;
    }

    private HorizontalVerticalPagerAdapter mAdapter;

    @Override public void create() {
        initFragments();
        mBinding.viewpager.setOffscreenPageLimit(5);

        mAdapter = new HorizontalVerticalPagerAdapter(getSupportFragmentManager(), this, fmViews, fms);
        mBinding.viewpager.setAdapter(mAdapter);
        mBinding.viewpager.addOnPageChangeListener(mAdapter);
        mBinding.viewpager.setCurrentItem(2);

//        mAdapter.setOnTouchListener(new HorizontalVerticalPagerAdapter.OnTouchListener() {
//            @Override
//            public void onVerticalFling(int offsetPosition) {
//                mBinding.viewpager.setVertical(true);
//                mBinding.viewpager.setCurrentItem(mAdapter.getPosition() + offsetPosition);
//            }
//
//            @Override
//            public void onHorizontalFling(int offsetPosition) {
//                mBinding.viewpager.setVertical(false);
//                mBinding.viewpager.setCurrentItem(mAdapter.getPosition() + offsetPosition);
//            }
//        });

        mBinding.buttonPanel.setOnClickListener(v -> {
            mBinding.viewpager.setVertical(!mBinding.viewpager.isVertical());
            mAdapter.notifyDataSetChanged();
        });
    }


    public void setViewPager(boolean isVertical, int offsetPosition) {
        mBinding.viewpager.setVertical(isVertical);
        mBinding.viewpager.setCurrentItem(mAdapter.getPosition() + offsetPosition);
        mAdapter.notifyDataSetChanged();
    }


    private List<Fragment> fms = new ArrayList<>();
    private Fragment fmLeft, fmTop, fmCenter, fmRight, fmBottom;
    private List<View> fmViews = new ArrayList<>();

    private void initFragments() {
        fmLeft = new SwipeChangePageFmLeft();
        fmTop = new SwipeChangePageFmTop();
        fmCenter = new SwipeChangePageFmCenter();
        fmRight = new SwipeChangePageFmRight();
        fmBottom = new SwipeChangePageFmBottom();
        fms.add(fmLeft);
        fms.add(fmTop);
        fms.add(fmCenter);
        fms.add(fmRight);
        fms.add(fmBottom);

        fmViews.add(((SwipeChangePageFmLeft) fmLeft).view());
        fmViews.add(((SwipeChangePageFmTop) fmTop).view());
        fmViews.add(((SwipeChangePageFmCenter) fmCenter).view());
        fmViews.add(((SwipeChangePageFmRight) fmRight).view());
        fmViews.add(((SwipeChangePageFmBottom) fmBottom).view());
    }
}

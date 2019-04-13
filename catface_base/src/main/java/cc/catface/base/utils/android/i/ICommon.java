package cc.catface.base.utils.android.i;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface ICommon {

    interface PageChangeListener extends ViewPager.OnPageChangeListener {
        @Override default void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        @Override default void onPageScrollStateChanged(int state) { }
    }

}

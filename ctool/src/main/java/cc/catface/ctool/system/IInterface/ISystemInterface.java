package cc.catface.ctool.system.IInterface;

import android.animation.Animator;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface ISystemInterface {

    interface TextChangedWatcher extends TextWatcher {
        @Override default void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override default void afterTextChanged(Editable editable) { }
    }

    interface AnimatorEndListener extends Animator.AnimatorListener {
        @Override default void onAnimationStart(Animator animator) { }

        @Override default void onAnimationCancel(Animator animator) { }

        @Override default void onAnimationRepeat(Animator animator) { }
    }

    interface PageChangeListener extends ViewPager.OnPageChangeListener {
        @Override default void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        @Override default void onPageScrollStateChanged(int state) { }
    }

}

package cc.catface.ctool.view.viewpager;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class PagerAdapterView<T extends View> extends PagerAdapter {

    private List<T> mViews;

    protected PagerAdapterView(List<T> views) {
        this.mViews = views;
    }

    @Override public int getCount() {
        return null == mViews ? 0 : mViews.size();
    }

    @Override public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull @Override public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViews.get(position));
    }
}

package cc.catface.api.eleme.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ElemeVPAdapter extends PagerAdapter {

    private List<View> mViews;

    public ElemeVPAdapter(List<View> views) {
        mViews = views;
    }

    @Override public int getCount() {
        return null == mViews ? 0 : mViews.size();
    }

    @Override public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull @Override public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViews.get(position));
    }
}

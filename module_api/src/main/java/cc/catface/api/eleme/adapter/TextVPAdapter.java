package cc.catface.api.eleme.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TextVPAdapter extends PagerAdapter {

    private List<GridView> mViews;

    public TextVPAdapter(List<GridView> views) {
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

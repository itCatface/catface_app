package cc.catface.wanandroid.engine.adapter;

import android.os.Parcelable;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class NavigationAdapter extends FragmentPagerAdapter {

    private List<String>  mTabTitles;
    private List<Fragment> mFms;

    public NavigationAdapter(FragmentManager fm, List<String> tabTitles, List<Fragment> fms) {
        super(fm);
        this.mTabTitles = tabTitles;
        this.mFms = fms;
    }

    @Override public Fragment getItem(int i) {
        return mFms.get(i);
    }

    @Override public int getCount() {
        return mFms.size();
    }

    @Nullable @Override public CharSequence getPageTitle(int position) {
        return mTabTitles.get(position);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}

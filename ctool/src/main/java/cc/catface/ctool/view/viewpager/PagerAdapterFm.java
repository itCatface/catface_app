package cc.catface.ctool.view.viewpager;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class PagerAdapterFm extends FragmentStatePagerAdapter {

    private List<Fragment> mFms;

    public PagerAdapterFm(@NonNull FragmentManager fm, List<Fragment> fms) {
        super(fm);
        this.mFms = fms;
    }

    private List<String> mTabTitles;

    public PagerAdapterFm(@NonNull FragmentManager fm, List<String> tabTitles, List<Fragment> fms) {
        super(fm);
        this.mTabTitles = tabTitles;
        this.mFms = fms;
    }

    @NonNull @Override public Fragment getItem(int position) {
        return mFms.get(position);
    }

    @Override public int getCount() {
        return null == mFms ? 0 : mFms.size();
    }

    @Nullable @Override public CharSequence getPageTitle(int position) {
        return mTabTitles.get(position);
    }

    @Override public Parcelable saveState() {
        return null;
    }

    @Override public void restoreState(Parcelable state, ClassLoader loader) { }
}

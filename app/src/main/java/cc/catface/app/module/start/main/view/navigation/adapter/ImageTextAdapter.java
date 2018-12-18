package cc.catface.app.module.start.main.view.navigation.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ImageTextAdapter extends FragmentStatePagerAdapter {

    private String[] mTabTitles;
    private List<Fragment> mFms;

    public ImageTextAdapter(FragmentManager fm, String[] tabTitles, List<Fragment> fms) {
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
        return mTabTitles[position];
    }
}

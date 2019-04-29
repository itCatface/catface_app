package cc.catface.wanandroid.engine.adapter;

import android.os.Parcelable;
import android.view.ViewGroup;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class WanandroidMainAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public WanandroidMainAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragments = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) { }
}
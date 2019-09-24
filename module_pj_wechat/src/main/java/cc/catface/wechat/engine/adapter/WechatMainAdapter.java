package cc.catface.wechat.engine.adapter;

import android.os.Parcelable;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class WechatMainAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public WechatMainAdapter(FragmentManager fm, List<Fragment> fragmentList) {
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
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
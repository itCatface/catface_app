package cc.catface.wanandroid.module.navigation.view;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.ctool.view.viewpager.PagerAdapterFm;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentNavigationBinding;
import cc.catface.wanandroid.engine.domain.NavigationData;
import cc.catface.wanandroid.module.navigation.vp.NavigationPresenterImpl;
import cc.catface.wanandroid.module.navigation.vp.NavigationVP;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class NavigationFm extends LightFm<NavigationPresenterImpl, WanandroidFragmentNavigationBinding> implements NavigationVP.NavigationView {

    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_navigation;
    }

    @Override protected void initPresenter() {
        mPresenter = new NavigationPresenterImpl(this, mActivity);
    }

    @Override protected void initData() {
        request();
    }


    /** private's support */
    private void request() {
        mPresenter.request();
    }

    /** View's */
    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    @Override public void requestSuccess(NavigationData data) {
        for (int i = 0; i < data.getData().size(); i++) {
            mTitles.add(data.getData().get(i).getName());

            NavigationListFm fm = NavigationListFm.getInstance(data.getData().get(i), i);
            mFragments.add(fm);
        }

        mBinding.vpNavigation.setOffscreenPageLimit(mTitles.size());
        mBinding.vpNavigation.setAdapter(new PagerAdapterFm(getChildFragmentManager(), mTitles, mFragments));
        mBinding.tlNavigation.setupWithViewPager(mBinding.vpNavigation);
    }

    @Override public void requestFailure(String error) {

    }
}

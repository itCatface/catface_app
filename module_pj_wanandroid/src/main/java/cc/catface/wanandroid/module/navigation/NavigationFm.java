package cc.catface.wanandroid.module.navigation;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentNavigationBinding;
import cc.catface.wanandroid.engine.adapter.NavigationAdapter;
import cc.catface.wanandroid.engine.adapter.ProjectsAdapter;
import cc.catface.wanandroid.engine.domain.NavigationData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(NavigationPresenterImpl.class)
public class NavigationFm extends MvpFragment<NavigationVP.NavigationView, NavigationPresenterImpl, WanandroidFragmentNavigationBinding> implements NavigationVP.NavigationView {
    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_navigation;
    }

    @Override protected void initData() {
        request();
    }

    @Override public void viewCreated() {

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
        mBinding.vpNavigation.setAdapter(new NavigationAdapter(getChildFragmentManager(), mTitles, mFragments));
        mBinding.tlNavigation.setupWithViewPager(mBinding.vpNavigation);
    }

    @Override public void requestFailure(String error) {

    }
}

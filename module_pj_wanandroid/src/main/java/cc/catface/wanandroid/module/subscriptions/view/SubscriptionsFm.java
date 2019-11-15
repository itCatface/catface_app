package cc.catface.wanandroid.module.subscriptions.view;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.ctool.view.viewpager.PagerAdapterFm;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentSubscriptiosBinding;
import cc.catface.wanandroid.engine.domain.SubscriptionsData;
import cc.catface.wanandroid.module.subscriptions.vp.SubscriptionsPresenterImpl;
import cc.catface.wanandroid.module.subscriptions.vp.SubscriptionsVP;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SubscriptionsFm extends LightFm<SubscriptionsPresenterImpl, WanandroidFragmentSubscriptiosBinding> implements SubscriptionsVP.SubscriptionsView {

    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_subscriptios;
    }

    @Override protected void initPresenter() {
        mPresenter = new SubscriptionsPresenterImpl(this, mActivity);
    }

    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFms = new ArrayList<>();

    @Override protected void initData() {
        request();
    }


    /** private's support */
    private void request() {
        mPresenter.request();
    }

    /** View's */
    @Override public void requestSuccess(SubscriptionsData data) {
        for (SubscriptionsData.Data projectsData : data.getData()) {
            mTitles.add(projectsData.getName());
            mFms.add(SubscriptionsListFm.getInstance(projectsData.getId()));
        }


        mBinding.vpSubscriptions.setOffscreenPageLimit(mTitles.size());
        mBinding.vpSubscriptions.setAdapter(new PagerAdapterFm(getChildFragmentManager(), mTitles, mFms));
        mBinding.tlSubscriptions.setupWithViewPager(mBinding.vpSubscriptions);
    }

    @Override public void requestFailure(String error) {

    }
}

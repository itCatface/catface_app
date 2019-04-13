package cc.catface.wanandroid.module.subscriptions.mvp;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentSubscriptiosBinding;
import cc.catface.wanandroid.engine.adapter.ProjectsAdapter;
import cc.catface.wanandroid.engine.domain.SubscriptionsData;
import cc.catface.wanandroid.module.subscriptions.SubscriptionsListFm;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(SubscriptionsPresenterImpl.class)
public class SubscriptionsFm extends MvpFragment<SubscriptionsVP.SubscriptionsView, SubscriptionsPresenterImpl, WanandroidFragmentSubscriptiosBinding> implements SubscriptionsVP.SubscriptionsView {
    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_subscriptios;
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
        mBinding.vpSubscriptions.setAdapter(new ProjectsAdapter(getChildFragmentManager(), mTitles, mFms));
        mBinding.tlSubscriptions.setupWithViewPager(mBinding.vpSubscriptions);
    }

    @Override public void requestFailure(String error) {

    }
}

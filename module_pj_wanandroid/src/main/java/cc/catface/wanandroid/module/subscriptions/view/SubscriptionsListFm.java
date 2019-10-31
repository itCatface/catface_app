package cc.catface.wanandroid.module.subscriptions.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.utils.android.view.recyclerview.ItemClickSupport;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentSubscriptionsListBinding;
import cc.catface.wanandroid.engine.adapter.SubscriptionsListAdapter;
import cc.catface.wanandroid.engine.domain.SubscriptionsListData;
import cc.catface.wanandroid.module.subscriptions.vp.SubscriptionsListPresenterImpl;
import cc.catface.wanandroid.module.subscriptions.vp.SubscriptionsListVP;
import cc.catface.wanandroid.module.web.WebActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SubscriptionsListFm extends LightFm<SubscriptionsListPresenterImpl, WanandroidFragmentSubscriptionsListBinding> implements SubscriptionsListVP.SubscriptionsListView {

    private int mCid;

    public static SubscriptionsListFm getInstance(int cid) {
        SubscriptionsListFm fm = new SubscriptionsListFm();
        Bundle bundle = new Bundle();
        bundle.putInt("cid", cid);
        fm.setArguments(bundle);
        return fm;
    }

    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_subscriptions_list;
    }

    @Override protected void initPresenter() {
        mPresenter = new SubscriptionsListPresenterImpl(this, mActivity);
    }

    @Override protected void initData() {
        mCid = getArguments().getInt("cid");
        request(mCid);
    }

    @Override protected void initAction() {
        mBinding.srlSubscriptionsList.setOnRefreshListener(() -> request(mCid));
        ItemClickSupport.addTo(mBinding.rvSubscriptionsList).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                String url = mData.getData().getDatas().get(position).getLink();
                WebActivity.jump(mActivity, url);
            }
        });
    }

    private SubscriptionsListAdapter mAdapter;

    @Override protected void created() {
        mBinding.srlSubscriptionsList.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY);

        mBinding.rvSubscriptionsList.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvSubscriptionsList.setHasFixedSize(true);
        mAdapter = new SubscriptionsListAdapter(mActivity);
        mBinding.rvSubscriptionsList.setAdapter(mAdapter);
    }


    /** private's support */
    private void request(int cid) {
        mPresenter.request(0, cid);
    }

    /** View's */
    private SubscriptionsListData mData;

    @Override public void requestSuccess(SubscriptionsListData data) {
        mBinding.srlSubscriptionsList.setRefreshing(false);
        mData = data;
        mAdapter.setData(mData);
        mAdapter.notifyDataSetChanged();
    }

    @Override public void requestFailure(String error) {
        mBinding.srlSubscriptionsList.setRefreshing(false);
    }
}

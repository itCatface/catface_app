package cc.catface.wanandroid.module.navigation.view;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.view.recyclerview.ItemClickSupport;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentNavigationListBinding;
import cc.catface.wanandroid.engine.adapter.NavigationListAdapter;
import cc.catface.wanandroid.engine.domain.NavigationData;
import cc.catface.wanandroid.module.web.WebActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class NavigationListFm extends LightFm<LightPresenter, WanandroidFragmentNavigationListBinding> {

    public static NavigationListFm getInstance(NavigationData.Data data, int id) {
        NavigationListFm fm = new NavigationListFm();
        Bundle bundle = new Bundle();
        bundle.putSerializable("NavigationData", data);
        bundle.putInt("id", id);
        fm.setArguments(bundle);
        return fm;
    }

    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_navigation_list;
    }

    private NavigationData.Data mData;
    private int mId;

    @Override protected void initData() {
        mData = (NavigationData.Data) getArguments().getSerializable("NavigationData");
        mId = getArguments().getInt("id");
    }

    @Override protected void created() {
        mBinding.rvNavigationList.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvNavigationList.setHasFixedSize(true);
        mBinding.rvNavigationList.setAdapter(new NavigationListAdapter(mData.getArticles()));
    }

    @Override protected void initAction() {
        ItemClickSupport.addTo(mBinding.rvNavigationList).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                String url = mData.getArticles().get(position).getLink();
                WebActivity.jump(mActivity, url);
            }
        });
    }


}

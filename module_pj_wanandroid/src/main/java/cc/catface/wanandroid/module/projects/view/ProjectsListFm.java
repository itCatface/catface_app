package cc.catface.wanandroid.module.projects.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.utils.android.view.recyclerview.ItemClickSupport;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentProjectsListBinding;
import cc.catface.wanandroid.engine.adapter.ProjectsListAdapter;
import cc.catface.wanandroid.engine.domain.ProjectsListData;
import cc.catface.wanandroid.module.projects.vp.ProjectsListPresenterImpl;
import cc.catface.wanandroid.module.projects.vp.ProjectsListVP;
import cc.catface.wanandroid.module.web.WebActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ProjectsListFm extends LightFm<ProjectsListPresenterImpl, WanandroidFragmentProjectsListBinding> implements ProjectsListVP.ProjectsListView {

    private int mCid;

    public static ProjectsListFm getInstance(int cid) {
        ProjectsListFm fm = new ProjectsListFm();
        Bundle bundle = new Bundle();
        bundle.putInt("cid", cid);
        fm.setArguments(bundle);
        return fm;
    }

    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_projects_list;
    }

    @Override protected void initPresenter() {
        mPresenter = new ProjectsListPresenterImpl(this, mActivity);
    }

    @Override protected void initData() {
        mCid = getArguments().getInt("cid");
        request(mCid);
    }

    @Override protected void initAction() {
        mBinding.srlProjectsList.setOnRefreshListener(() -> request(mCid));
        ItemClickSupport.addTo(mBinding.rvProjectsList).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                String url = mData.getData().getDatas().get(position).getLink();
                WebActivity.jump(mActivity, url);
            }
        });
    }

    @Override protected void created() {
        mBinding.srlProjectsList.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY);

        mBinding.rvProjectsList.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvProjectsList.setHasFixedSize(true);
    }


    /** private's support */
    private void request(int cid) {
        mPresenter.request(0, cid);
    }

    /** View's */
    private ProjectsListData mData;

    @Override public void requestSuccess(ProjectsListData data) {
        mData = data;
        mBinding.srlProjectsList.setRefreshing(false);
        mBinding.rvProjectsList.setAdapter(new ProjectsListAdapter(mData.getData().getDatas()));
    }

    @Override public void requestFailure(String error) {
        mBinding.srlProjectsList.setRefreshing(false);
    }
}

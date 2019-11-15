package cc.catface.wanandroid.module.projects.view;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.ctool.view.viewpager.PagerAdapterFm;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentProjectsBinding;
import cc.catface.wanandroid.engine.domain.ProjectsData;
import cc.catface.wanandroid.module.projects.vp.ProjectsPresenterImpl;
import cc.catface.wanandroid.module.projects.vp.ProjectsVP;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ProjectsFm extends LightFm<ProjectsPresenterImpl, WanandroidFragmentProjectsBinding> implements ProjectsVP.ProjectsView {

    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_projects;
    }

    @Override protected void initPresenter() {
        mPresenter = new ProjectsPresenterImpl(this, mActivity);
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
    @Override public void requestSuccess(ProjectsData data) {
        for (ProjectsData.Data projectsData : data.getData()) {
            mTitles.add(projectsData.getName());

            mFms.add(ProjectsListFm.getInstance(projectsData.getId()));
        }


        mBinding.vpProjects.setOffscreenPageLimit(mTitles.size());
        mBinding.vpProjects.setAdapter(new PagerAdapterFm(getChildFragmentManager(), mTitles, mFms));
        mBinding.tlProjects.setupWithViewPager(mBinding.vpProjects);
    }

    @Override public void requestFailure(String error) {

    }
}

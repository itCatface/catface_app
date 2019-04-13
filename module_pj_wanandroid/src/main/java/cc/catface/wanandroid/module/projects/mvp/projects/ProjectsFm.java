package cc.catface.wanandroid.module.projects.mvp.projects;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentProjectsBinding;
import cc.catface.wanandroid.engine.adapter.ProjectsAdapter;
import cc.catface.wanandroid.engine.domain.ProjectsData;
import cc.catface.wanandroid.module.projects.mvp.projects_list.ProjectsListFm;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(ProjectsPresenterImpl.class)
public class ProjectsFm extends MvpFragment<ProjectsVP.ProjectsView, ProjectsPresenterImpl, WanandroidFragmentProjectsBinding> implements ProjectsVP.ProjectsView {
    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_projects;
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
        mBinding.vpProjects.setAdapter(new ProjectsAdapter(getChildFragmentManager(), mTitles, mFms));
        mBinding.tlProjects.setupWithViewPager(mBinding.vpProjects);
    }

    @Override public void requestFailure(String error) {

    }
}

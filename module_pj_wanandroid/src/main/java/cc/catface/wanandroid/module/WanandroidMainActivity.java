package cc.catface.wanandroid.module;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.utils.android.i.ICommon;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidActivityMainBinding;
import cc.catface.wanandroid.engine.adapter.WanandroidMainAdapter;
import cc.catface.wanandroid.module.home.HomeFm;
import cc.catface.wanandroid.module.knowledge.mvp.knowledge.KnowledgeFm;
import cc.catface.wanandroid.module.navigation.NavigationFm;
import cc.catface.wanandroid.module.projects.mvp.projects.ProjectsFm;
import cc.catface.wanandroid.module.subscriptions.mvp.SubscriptionsFm;

@Route(path = Const.ARouter.pj_wanandroid_main) public class WanandroidMainActivity extends NormalActivity<WanandroidActivityMainBinding> {

    @Override public int layoutId() {
        return R.layout.wanandroid_activity_main;
    }


    @Override protected void initData() {
        initFragments();
    }

    private List<Fragment> mFragments = new ArrayList<>();

    private void initFragments() {
        mFragments.add(new HomeFm());
        mFragments.add(new KnowledgeFm());
        mFragments.add(new SubscriptionsFm());
        mFragments.add(new NavigationFm());
        mFragments.add(new ProjectsFm());
    }

    @Override protected void initAction() {
        mBinding.vpWanandroidMain.setOnPageChangeListener((ICommon.PageChangeListener) position -> {
            switch (position) {
                case 0:
                    mBinding.bottomNav.setSelectedItemId(R.id.bottom_nav_home);
                    updateToolBarTitle("首页");
                    break;
                case 1:
                    mBinding.bottomNav.setSelectedItemId(R.id.bottom_nav_knowledge);
                    updateToolBarTitle("知识体系");
                    break;
                case 2:
                    mBinding.bottomNav.setSelectedItemId(R.id.bottom_nav_subscriptions);
                    updateToolBarTitle("公众号");
                    break;
                case 3:
                    mBinding.bottomNav.setSelectedItemId(R.id.bottom_nav_navigation);
                    updateToolBarTitle("导航");
                    break;
                case 4:
                    mBinding.bottomNav.setSelectedItemId(R.id.bottom_nav_projects);
                    updateToolBarTitle("项目");
                    break;
            }
        });
        mBinding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            if (R.id.bottom_nav_home == item.getItemId())
                mBinding.vpWanandroidMain.setCurrentItem(0, false);
            if (R.id.bottom_nav_knowledge == item.getItemId())
                mBinding.vpWanandroidMain.setCurrentItem(1, false);
            if (R.id.bottom_nav_subscriptions == item.getItemId())
                mBinding.vpWanandroidMain.setCurrentItem(2, false);
            if (R.id.bottom_nav_navigation == item.getItemId())
                mBinding.vpWanandroidMain.setCurrentItem(3, false);
            if (R.id.bottom_nav_projects == item.getItemId())
                mBinding.vpWanandroidMain.setCurrentItem(4, false);
            return true;
        });
    }

    @Override public void create() {
        initToolBar();

        mBinding.vpWanandroidMain.setAdapter(new WanandroidMainAdapter(getSupportFragmentManager(), mFragments));
    }


    /** tool bar */
    private ActionBar mBar;

    private void initToolBar() {
        Toolbar toolbar = mBinding.iTbWanandroid.tbTitle;
        setSupportActionBar(toolbar);
        mBar = getSupportActionBar();
        if (null != mBar) {
            mBar.setDisplayShowHomeEnabled(true);
            mBar.setTitle("wanandroid/首页");
        }
        toolbar.setNavigationIcon(R.mipmap.flaticon_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void updateToolBarTitle(String title) {
        if (null != mBar) {
            mBar.setTitle("wanandroid/" + title);
        }
    }


    /** private's support */
}

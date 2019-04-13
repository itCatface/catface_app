package cc.catface.wanandroid.module.home;

import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentHomeBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(HomePresenterImpl.class)
public class HomeFm extends MvpFragment<HomeVP.HomeView, HomePresenterImpl, WanandroidFragmentHomeBinding> implements HomeVP.HomeView {
    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_home;
    }

    @Override public void viewCreated() {

    }
}

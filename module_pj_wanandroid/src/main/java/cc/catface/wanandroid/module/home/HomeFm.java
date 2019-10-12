package cc.catface.wanandroid.module.home;

import android.os.Message;
import android.os.SystemClock;

import androidx.viewpager2.widget.ViewPager2;

import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.ctool.system.TWeakHandler;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentHomeBinding;
import cc.catface.wanandroid.engine.adapter.HomeBannerAdapter;
import cc.catface.wanandroid.engine.domain.Banner;
import cc.catface.wanandroid.engine.domain.TopArticle;
import cc.catface.wanandroid.engine.domain.WanandroidConst;
import cc.catface.wanandroid.module.web.WebActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 * desc 支持滑动隐藏顶部ToolBar
 */
@CreatePresenter(HomePresenterImpl.class) public class HomeFm extends MvpFragment<HomeVP.HomeView, HomePresenterImpl, WanandroidFragmentHomeBinding> implements HomeVP.HomeView, TWeakHandler.MessageListener {

    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_home;
    }

    private TWeakHandler<HomeFm> mHandler;
    private final int WHAT_PLAY_NEXT = 0x00;
    private boolean autoPlay = true;
    private int duration = 3_000;

    @Override public void handleMessage(Message msg) {
        int what = msg.what;
        if (what == WHAT_PLAY_NEXT) {
            mBinding.vpHomeBanner.setCurrentItem(mBinding.vpHomeBanner.getCurrentItem() + 1);
        }
    }

    @Override protected void initAction() {
        mBinding.btJump2Blog.setOnClickListener(v -> WebActivity.jump(mActivity, WanandroidConst.url_blog));
    }

    @Override protected void initData() {
        mHandler = new TWeakHandler<>(this);
        mPresenter.requestBanner();
        mPresenter.requestTopArticle();
    }

    @Override public void viewCreated() {
        mBinding.vpHomeBanner.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager2.SCROLL_STATE_IDLE:
                    case ViewPager2.SCROLL_STATE_SETTLING:
                        autoPlay = true;
                        break;

                    case ViewPager2.SCROLL_STATE_DRAGGING:
                        autoPlay = false;
                        break;

                }
            }
        });
    }


    /** View's */
    @Override public void requestBannerSuccess(Banner banner) {
        mBinding.vpHomeBanner.setAdapter(new HomeBannerAdapter(banner.getData()));
        mBinding.vpHomeBanner.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % banner.getData().size());
        new Thread(() -> {
            while (true) {
                SystemClock.sleep(duration);
                if (autoPlay) mHandler.sendEmptyMessage(WHAT_PLAY_NEXT);
            }
        }).start();
    }

    @Override public void requestBannerFailure() {

    }

    @Override public void requestTopArticleSuccess(TopArticle topArticle) {

    }

    @Override public void requestTopArticleFailure() {

    }
}

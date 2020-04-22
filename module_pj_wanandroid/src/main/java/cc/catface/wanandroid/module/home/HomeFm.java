package cc.catface.wanandroid.module.home;

import android.os.Message;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.ctool.system.TWeakHandler;
import cc.catface.ctool.view.recyclerview.ItemClickSupport;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentHomeBinding;
import cc.catface.wanandroid.engine.adapter.HomeBannerAdapter;
import cc.catface.wanandroid.engine.adapter.HomeTopArticleAdapter;
import cc.catface.wanandroid.engine.domain.Banner;
import cc.catface.wanandroid.engine.domain.TopArticle;
import cc.catface.wanandroid.module.web.WebActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 * desc 支持滑动隐藏顶部ToolBar
 */
public class HomeFm extends LightFm<HomePresenterImpl, WanandroidFragmentHomeBinding> implements HomeVP.HomeView {

    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_home;
    }

    @Override protected void initPresenter() {
        mPresenter = new HomePresenterImpl(this, mActivity);
    }

    private TWeakHandler<HomeFm> mHandler;
    private final int WHAT_PLAY_NEXT = 0x00;

    @Override public void handleMessage(Message msg) {
        int what = msg.what;
        if (what == WHAT_PLAY_NEXT && isPlaying) {
            mBinding.vpHomeBanner.setCurrentItem(mBinding.vpHomeBanner.getCurrentItem() + 1);
            ctrlPlay(true);
        }
    }

    @Override protected void initAction() {
        ItemClickSupport.addTo(mBinding.rvTopArticle).setOnItemClickListener((recyclerView, position, view) -> WebActivity.jump(mActivity, mTopArticleDatas.get(position).getLink()));
    }

    @Override protected void initData() {
        mHandler = new TWeakHandler<>(this);
        mPresenter.requestBanner();
        mPresenter.requestTopArticle();
    }

    /**
     * View's
     */
    private List<Banner.Data> mBannerDatas = new ArrayList<>();

    @Override public void requestBannerSuccess(Banner banner) {
        mBannerDatas = banner.getData();
        mBinding.vpHomeBanner.setAdapter(new HomeBannerAdapter(mBannerDatas));
        mBinding.vpHomeBanner.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % mBannerDatas.size());
        mBinding.vpHomeBanner.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager2.SCROLL_STATE_IDLE:
                    case ViewPager2.SCROLL_STATE_SETTLING:
                        if (!isPlaying) ctrlPlay(true);
                        break;

                    case ViewPager2.SCROLL_STATE_DRAGGING:
                        ctrlPlay(false);
                        break;
                }
            }

            @Override public void onPageSelected(int position) {
                mBinding.tvBannerTitle.setText(mBannerDatas.get(position % mBannerDatas.size()).getTitle());
            }
        });

        ctrlPlay(true);
    }

    @Override public void requestBannerFailure() {

    }

    private List<TopArticle.Data> mTopArticleDatas;

    @Override public void requestTopArticleSuccess(TopArticle topArticle) {
        mTopArticleDatas = topArticle.getData();
        mBinding.rvTopArticle.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvTopArticle.setHasFixedSize(true);
        mBinding.rvTopArticle.setAdapter(new HomeTopArticleAdapter(mTopArticleDatas));
    }

    @Override public void requestTopArticleFailure() {

    }

    @Override public void onPause() {
        ctrlPlay(false);
        super.onPause();
    }

    /**
     * 轮播控制
     */
    private boolean isPlaying = true;
    private int mPlayingDuration = 3_000;

    private void ctrlPlay(boolean startPlaying) {
        if (null == mHandler) return;
        isPlaying = startPlaying;
        if (isPlaying) mHandler.sendEmptyMessageDelayed(WHAT_PLAY_NEXT, mPlayingDuration);
        else mHandler.removeCallbacksAndMessages(null);
    }

    @Override public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        ctrlPlay(isVisibleToUser);
    }

}

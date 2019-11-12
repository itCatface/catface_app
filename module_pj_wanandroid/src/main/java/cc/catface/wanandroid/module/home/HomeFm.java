package cc.catface.wanandroid.module.home;

import android.os.Message;
import android.os.SystemClock;

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
    private boolean autoPlay = true;
    private int duration = 3_000;

    @Override public void handleMessage(Message msg) {
        int what = msg.what;
        if (what == WHAT_PLAY_NEXT) {
            mBinding.vpHomeBanner.setCurrentItem(mBinding.vpHomeBanner.getCurrentItem() + 1);
        }
    }

    @Override protected void initAction() {
        ItemClickSupport.addTo(mBinding.rvTopArticle).setOnItemClickListener((recyclerView, position, view) -> WebActivity.jump(mActivity, mTopArticleDatas.get(position).getLink()));
    }


    private HomeTopArticleAdapter mTopArticleAdapter;

    @Override protected void initData() {
        mHandler = new TWeakHandler<>(this);
        mPresenter.requestBanner();
        mPresenter.requestTopArticle();
    }


    /** View's */
    private List<Banner.Data> mBannerDatas = new ArrayList<>();

    @Override public void requestBannerSuccess(Banner banner) {
        mBannerDatas = banner.getData();
        mBinding.vpHomeBanner.setAdapter(new HomeBannerAdapter(mBannerDatas));
        mBinding.vpHomeBanner.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % mBannerDatas.size());
        new Thread(() -> {
            while (true) {
                SystemClock.sleep(duration);
                if (autoPlay) mHandler.sendEmptyMessage(WHAT_PLAY_NEXT);
            }
        }).start();
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

            @Override public void onPageSelected(int position) {
                mBinding.tvBannerTitle.setText(mBannerDatas.get(position % mBannerDatas.size()).getTitle());
            }
        });
    }

    @Override public void requestBannerFailure() {

    }

    private List<TopArticle.Data> mTopArticleDatas;

    @Override public void requestTopArticleSuccess(TopArticle topArticle) {
        mTopArticleDatas = topArticle.getData();
        mBinding.rvTopArticle.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvTopArticle.setHasFixedSize(true);
        mTopArticleAdapter = new HomeTopArticleAdapter(topArticle.getData());
        mBinding.rvTopArticle.setAdapter(mTopArticleAdapter);
    }

    @Override public void requestTopArticleFailure() {

    }
}

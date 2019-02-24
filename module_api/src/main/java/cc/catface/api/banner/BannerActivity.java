package cc.catface.api.banner;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cc.catface.api.R;
import cc.catface.api.banner.domain.BannerBean;
import cc.catface.api.databinding.ApiActivityBannerBinding;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.utils.android.Timer.CountDownTimer;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.base.utils.android.common_print.toast.TToast;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.AROUTER.api_banner)
public class BannerActivity extends NormalActivity<ApiActivityBannerBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_banner;
    }

    @Override public void create() {
        initTitle();

        itcast();

        recycler();
    }

    private void initTitle() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.AROUTER.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
    }


    /** 传智轮播方式 */
    private CountDownTimer mCountDownTimer;
    private boolean mFlagInterceptPlay = false;
    private int mFingerUpTime;

    private List<BannerBean> mDatas = new ArrayList<>();
    private ArrayList<ImageView> mIcons = new ArrayList<>();
    private int previousSelectedPosition = 0;

    @SuppressLint("ClickableViewAccessibility") private void itcast() {
        /** 初始化数据 */
        mDatas.add(new BannerBean(R.drawable.start_indicator_1, "我的大刀早已饥渴难耐了"));
        mDatas.add(new BannerBean(R.drawable.start_indicator_2, "敌军还有30秒到达战场"));
        mDatas.add(new BannerBean(R.drawable.start_indicator_3, "你也想来一发嘛"));
        mDatas.add(new BannerBean(R.drawable.start_indicator_4, "人在塔在"));


        /** ViewPager事件-->触摸时禁止滚动 */
        mBinding.vpAd.setOnTouchListener((view, motionEvent) -> {
            switch(motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mFlagInterceptPlay = true;
                    break;

                case MotionEvent.ACTION_UP:
                    mFlagInterceptPlay = false;
                    mFingerUpTime = (int) System.currentTimeMillis();
                    TLog.d("rrrrr", "ds" + mFingerUpTime);
                    break;
            }
            return false;
        });
        /* 滚动时更新轮播图、title、指示器状态 */
        mBinding.vpAd.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override public void onPageSelected(int position) {
                int newPosition = position % mIcons.size();
                mBinding.tvAdTitle.setText(mDatas.get(newPosition).getTitle());
                mBinding.llPoints.getChildAt(previousSelectedPosition).setBackgroundResource(R.drawable.shape_package_indicator_normal);
                mBinding.llPoints.getChildAt(newPosition).setBackgroundResource(R.drawable.shape_package_indicator_fouse);
                previousSelectedPosition = newPosition;
            }

            @Override public void onPageScrollStateChanged(int state) { }
        });


        /** 初始化所有banner图及对应指示器 */
        ImageView iv_icon;
        View v_point;
        LinearLayout.LayoutParams layoutParams;
        for(int i = 0; i < mDatas.size(); i++) {
            iv_icon = new ImageView(this);
            iv_icon.setBackgroundResource(mDatas.get(i).getIcon());
            mIcons.add(iv_icon);

            v_point = new View(this);
            layoutParams = new LinearLayout.LayoutParams(15, 15);
            if(0 != i) layoutParams.leftMargin = 10;
            v_point.setBackgroundResource(R.drawable.shape_package_indicator_normal);
            mBinding.llPoints.addView(v_point, layoutParams);
        }


        /** adapter */
        mBinding.llPoints.getChildAt(0).setBackgroundResource(R.drawable.shape_package_indicator_fouse);
        mBinding.tvAdTitle.setText(mDatas.get(0).getTitle());
        mBinding.vpAd.setAdapter(new PagerAdapter() {
            @Override public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override public Object instantiateItem(ViewGroup container, int position) {
                int newPosition = position % mIcons.size();
                ImageView iv = mIcons.get(newPosition);
                container.addView(iv);
                return iv;
            }

            @Override public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        mBinding.vpAd.setCurrentItem(Integer.MAX_VALUE / 2);


        /** 开启轮播 */
        mCountDownTimer = new CountDownTimer(Integer.MAX_VALUE, 3_000) {
            @Override public void onTick(long millisUntilFinished) {
                if(! mFlagInterceptPlay && (int) System.currentTimeMillis() - mFingerUpTime > 3_000)
                    mBinding.vpAd.setCurrentItem(mBinding.vpAd.getCurrentItem() + 1);
            }

            @Override public void onFinish() { }
        };
        mCountDownTimer.start();
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        if(null != mCountDownTimer) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    /**  */
    private String[] imgUrls = {"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1398415679,1254740245&fm=27&gp=0.jpg", "https://ss1.bdstatic" + "" + "" + "" + "" +
            ".com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=73725640,1948341253&fm=27&gp=0.jpg", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=947313244,2662783106&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1144942459,690886074&fm=27&gp=0.jpg", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2994369606," +
            "3652748422&fm=27&gp=0.jpg"};
    private String[] titles = {"a1", "b2", "c3", "d4", "e5"};

    private void recycler() {
        mBinding.ad1.initBannerImageView(Arrays.asList(imgUrls), position -> TToast.get(BannerActivity.this).showShortNormal(titles[position]), position -> mBinding.tv1.setText(titles[position %
                titles.length]));

        mBinding.ad2.initBannerImageView(Arrays.asList(imgUrls), position -> TToast.get(BannerActivity.this).showShortNormal(titles[position]), position -> mBinding.tv2.setText(titles[position %
                titles.length]));

        mBinding.ad3.initBannerImageView(Arrays.asList(imgUrls), position -> TToast.get(BannerActivity.this).showShortNormal(titles[position]), position -> mBinding.tv3.setText(titles[position %
                titles.length]));

        mBinding.ad4.initBannerImageView(Arrays.asList(imgUrls), position -> TToast.get(BannerActivity.this).showShortNormal(titles[position]), position -> mBinding.tv4.setText(titles[position %
                titles.length]));
    }
}

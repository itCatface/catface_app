package cc.catface.api.banner;

import android.annotation.SuppressLint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cc.catface.api.R;
import cc.catface.api.banner.domain.BannerBean;
import cc.catface.base.core_framework.base_normal.NormalBaseActivityID;
import cc.catface.base.utils.android.Timer.CountDownTimer;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.android.view.recyclerview.banner.RecyclerViewBannerBase;
import cc.catface.base.utils.android.view.recyclerview.banner.RecyclerViewBannerNew;
import cc.catface.base.utils.android.view.recyclerview.banner.RecyclerViewBannerNormal;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = "/api/banner")
public class BannerActivity extends NormalBaseActivityID {
    @Override public int layoutId() {
        return R.layout.api_activity_banner;
    }


    @Override public void ids() {
        idsOfRVBanner();
    }

    @Override public void create() {
        itcast();

        recycler();
    }

    /** 传智轮播方式 */
    private ViewPager vp_ad;
    private TextView tv_title;
    private LinearLayout ll_points;

    private CountDownTimer mCountDownTimer;
    private boolean mFlagInterceptPlay = false;

    private List<BannerBean> mDatas = new ArrayList<>();
    private ArrayList<ImageView> mIcons = new ArrayList<>();
    private int previousSelectedPosition = 0;

    @SuppressLint("ClickableViewAccessibility") private void itcast() {
        vp_ad = (ViewPager) findViewById(R.id.vp_ad);
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_points = (LinearLayout) findViewById(R.id.ll_points);

        /** 初始化数据 */
        mDatas.add(new BannerBean(R.drawable.start_indicator_1, "title1"));
        mDatas.add(new BannerBean(R.drawable.start_indicator_2, "title2"));
        mDatas.add(new BannerBean(R.drawable.start_indicator_3, "title3"));
        mDatas.add(new BannerBean(R.drawable.start_indicator_4, "title4"));


        /** ViewPager事件-->触摸时禁止滚动 */
        vp_ad.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mFlagInterceptPlay = true;
                    break;

                case MotionEvent.ACTION_UP:
                    mFlagInterceptPlay = false;
                    break;
            }
            return false;
        });
        /* 滚动时更新轮播图、title、指示器状态 */
        vp_ad.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override public void onPageSelected(int position) {
                int newPosition = position % mIcons.size();
                tv_title.setText(mDatas.get(newPosition).getTitle());
                ll_points.getChildAt(previousSelectedPosition).setBackgroundResource(R.drawable.shape_package_indicator_normal);
                ll_points.getChildAt(newPosition).setBackgroundResource(R.drawable.shape_package_indicator_fouse);
                previousSelectedPosition = newPosition;
            }

            @Override public void onPageScrollStateChanged(int state) { }
        });


        /** 初始化所有banner图及对应指示器 */
        ImageView iv_icon;
        View v_point;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < mDatas.size(); i++) {
            iv_icon = new ImageView(this);
            iv_icon.setBackgroundResource(mDatas.get(i).getIcon());
            mIcons.add(iv_icon);

            v_point = new View(this);
            layoutParams = new LinearLayout.LayoutParams(5, 5);
            if (0 != i) layoutParams.leftMargin = 10;
            v_point.setBackgroundResource(R.drawable.shape_package_indicator_normal);
            ll_points.addView(v_point, layoutParams);
        }


        /** adapter */
        ll_points.getChildAt(0).setBackgroundResource(R.drawable.shape_package_indicator_fouse);
        tv_title.setText(mDatas.get(0).getTitle());
        vp_ad.setAdapter(new PagerAdapter() {
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
        vp_ad.setCurrentItem(Integer.MAX_VALUE / 2);


        /** 开启轮播 */
        mCountDownTimer = new CountDownTimer(Integer.MAX_VALUE, 2_000) {
            @Override public void onTick(long millisUntilFinished) {
                if (!mFlagInterceptPlay) vp_ad.setCurrentItem(vp_ad.getCurrentItem() + 1);
            }

            @Override public void onFinish() { }
        };
        mCountDownTimer.start();
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        if (null != mCountDownTimer) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    /**  */
    private RecyclerViewBannerNormal ad_1;
    private RecyclerViewBannerNew ad_2;
    private RecyclerViewBannerNormal ad_3;
    private RecyclerViewBannerNew ad_4;
    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;
    private TextView tv_4;

    private void idsOfRVBanner() {
        ad_1 = (RecyclerViewBannerNormal) findViewById(R.id.ad_1);
        ad_2 = (RecyclerViewBannerNew) findViewById(R.id.ad_2);
        ad_3 = (RecyclerViewBannerNormal) findViewById(R.id.ad_3);
        ad_4 = (RecyclerViewBannerNew) findViewById(R.id.ad_4);
        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_3 = (TextView) findViewById(R.id.tv_3);
        tv_4 = (TextView) findViewById(R.id.tv_4);
    }

    private String[] imgUrls = {"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1398415679,1254740245&fm=27&gp=0.jpg", "https://ss1.bdstatic" + "" +
            ".com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=73725640,1948341253&fm=27&gp=0.jpg", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=947313244,2662783106&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1144942459,690886074&fm=27&gp=0.jpg", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2994369606," +
            "3652748422&fm=27&gp=0.jpg"};
    private String[] titles = {"a1", "b2", "c3", "d4", "e5"};

    private void recycler() {
        ad_1.initBannerImageView(Arrays.asList(imgUrls), new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override public void onItemClick(int position) {
                TToast.get(BannerActivity.this).showShortNormal(titles[position]);
            }
        }, new RecyclerViewBannerBase.OnBannerScrolledListener() {
            @Override public void onBannerScrolled(int position) {
                tv_1.setText(titles[position % titles.length]);
            }
        });

        ad_2.initBannerImageView(Arrays.asList(imgUrls), new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override public void onItemClick(int position) {
                TToast.get(BannerActivity.this).showShortNormal(titles[position]);
            }
        }, new RecyclerViewBannerBase.OnBannerScrolledListener() {
            @Override public void onBannerScrolled(int position) {
                tv_2.setText(titles[position % titles.length]);
            }
        });

        ad_3.initBannerImageView(Arrays.asList(imgUrls), new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override public void onItemClick(int position) {
                TToast.get(BannerActivity.this).showShortNormal(titles[position]);
            }
        }, new RecyclerViewBannerBase.OnBannerScrolledListener() {
            @Override public void onBannerScrolled(int position) {
                tv_3.setText(titles[position % titles.length]);
            }
        });

        ad_4.initBannerImageView(Arrays.asList(imgUrls), new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override public void onItemClick(int position) {
                TToast.get(BannerActivity.this).showShortNormal(titles[position]);
            }
        }, new RecyclerViewBannerBase.OnBannerScrolledListener() {
            @Override public void onBannerScrolled(int position) {
                tv_4.setText(titles[position % titles.length]);
            }
        });
    }
}

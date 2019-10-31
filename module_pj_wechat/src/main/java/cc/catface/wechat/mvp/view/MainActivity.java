package cc.catface.wechat.mvp.view;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.wechat.R;
import cc.catface.wechat.databinding.WechatActivityMainBinding;
import cc.catface.wechat.engine.adapter.WechatMainAdapter;
import cc.catface.wechat.mvp.vp.MainPresenterImpl;
import cc.catface.wechat.mvp.vp.MainVP;
import cc.catface.wechat.utils.ShadeView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.pj_wechat_main) public class MainActivity extends LightAct<MainPresenterImpl, WechatActivityMainBinding> implements MainVP.MainView {

    @Override public int layoutId() {
        return R.layout.wechat_activity_main;
    }

    @Override protected void initData() {
        initFragments();
        initTabIndicator();
    }

    private List<Fragment> mFragments = new ArrayList<>();
    private List<ShadeView> tabIndicators = new ArrayList<>();

    private void initFragments() {
        mFragments.add(new ChatsFm());
        mFragments.add(new ContactsFm());
        mFragments.add(new DiscoverFm());
        mFragments.add(new MeFm());
    }

    private void initTabIndicator() {
        ShadeView one = (ShadeView) findViewById(R.id.id_indicator_one);
        ShadeView two = (ShadeView) findViewById(R.id.id_indicator_two);
        ShadeView three = (ShadeView) findViewById(R.id.id_indicator_three);
        ShadeView four = (ShadeView) findViewById(R.id.id_indicator_four);
        tabIndicators.add(one);
        tabIndicators.add(two);
        tabIndicators.add(three);
        tabIndicators.add(four);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        one.setIconAlpha(1.0f);
    }

    @Override public void created() {
        mBinding.vpMain.setOffscreenPageLimit(4);
        mBinding.vpMain.setAdapter(new WechatMainAdapter(getSupportFragmentManager(), mFragments));
        mBinding.vpMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0) {
                    ShadeView leftTab = tabIndicators.get(position);
                    ShadeView rightTab = tabIndicators.get(position + 1);
                    leftTab.setIconAlpha(1 - positionOffset);
                    rightTab.setIconAlpha(positionOffset);
                }
            }

            @Override public void onPageSelected(int position) {
                if (position == 2) {
                    tabIndicators.get(position).setIconBitmap(MainActivity.this, R.drawable.wechat_icon_main_bottom_tab_discover_green);
                } else {
                    tabIndicators.get(2).setIconBitmap(MainActivity.this, R.drawable.wechat_icon_main_bottom_tab_discover);
                }
            }

            @Override public void onPageScrollStateChanged(int state) { }
        });
    }

    @Override public void onClick(View view) {
        resetTabsStatus();
        int id = view.getId();
        if (id == R.id.id_indicator_one) {
            tabIndicators.get(0).setIconAlpha(1.0f);
            mBinding.vpMain.setCurrentItem(0, false);
        } else if (id == R.id.id_indicator_two) {
            tabIndicators.get(1).setIconAlpha(1.0f);
            mBinding.vpMain.setCurrentItem(1, false);
        } else if (id == R.id.id_indicator_three) {
            tabIndicators.get(2).setIconAlpha(1.0f);
            mBinding.vpMain.setCurrentItem(2, false);
        } else if (id == R.id.id_indicator_four) {
            tabIndicators.get(3).setIconAlpha(1.0f);
            mBinding.vpMain.setCurrentItem(3, false);
        }
    }

    private void resetTabsStatus() {
        for (int i = 0; i < tabIndicators.size(); i++) {
            tabIndicators.get(i).setIconAlpha(0);
        }
    }
}

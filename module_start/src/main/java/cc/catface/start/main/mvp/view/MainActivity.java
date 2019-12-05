package cc.catface.start.main.mvp.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTabHost;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.ctool.system.TScreen;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.start.R;
import cc.catface.start.databinding.StartActivityMainBinding;
import cc.catface.start.main.domain.Tab;
import cc.catface.start.main.media.mvp.view.AVFm;
import cc.catface.start.main.mvp.vp.MainPresenterImp;
import cc.catface.start.main.mvp.vp.MainVP;
import cc.catface.start.main.personal.view.PersonalFm;
import cc.catface.start.main.query.view.QueryFm;

@Route(path = Const.ARouter.start_main) public class MainActivity extends LightAct<MainPresenterImp, StartActivityMainBinding> implements MainVP.MainView, MessFm.FragmentListener, NavigationView.OnNavigationItemSelectedListener {

    @Override public int layoutId() {
        return R.layout.start_activity_main;
    }

    @Override protected void initPresenter() {
        mPresenter = new MainPresenterImp(this, this);
    }

    private FragmentTabHost tab_host;
    private List<Tab> mFmTabs;

    @SuppressLint("CheckResult") @Override protected void created() {
        initToolBar();

        /*  */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mBinding.dl, mBinding.iTbStart.tbTitle, R.string.app_name, R.string.app_name);
        mBinding.dl.addDrawerListener(toggle);
        toggle.syncState();
        mBinding.nv.setNavigationItemSelectedListener(this);


        /* 调整侧边导航布局高度 */
        int statusBarHeight = TScreen.getStatusBarHeight();
        int actionBarHeight = TScreen.getActionBarHeight();
        DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) mBinding.nv.getLayoutParams();
        layoutParams.topMargin = statusBarHeight + actionBarHeight;
        mBinding.nv.setLayoutParams(layoutParams);


        mPresenter.requestPermission(this);
    }


    @Override public void onBackPressed() {
        DrawerLayout drawer = mBinding.dl;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    /* View's */
    @Override public void requestPermissionSuccess() {
        tab_host = (FragmentTabHost) findViewById(android.R.id.tabhost);
        initTab();
    }

    @Override public void requestPermissionFailure() {
        Toast.makeText(this, "请重启应用允许请求的权限", Toast.LENGTH_SHORT).show();
    }


    private void initTab() {
        mFmTabs = new ArrayList<>();
        mFmTabs.add(new Tab(FunctionFm.class, "功能", Color.argb(255, 26, 125, 235), R.drawable.icon_start_main_function_default, R.drawable.icon_start_main_function_focus));
        mFmTabs.add(new Tab(MessFm.class, "普通", Color.argb(255, 26, 125, 235), R.drawable.icon_start_main_text_image_default, R.drawable.icon_start_main_text_image_focus));
        mFmTabs.add(new Tab(AVFm.class, "影音", Color.argb(255, 26, 125, 235), R.drawable.icon_start_main_video_default, R.drawable.icon_start_main_video_focus));
        mFmTabs.add(new Tab(QueryFm.class, "查询", Color.argb(255, 26, 125, 235), R.drawable.icon_start_main_query_default, R.drawable.icon_start_main_query_focus));
        mFmTabs.add(new Tab(PersonalFm.class, "我", Color.argb(255, 26, 125, 235), R.drawable.icon_start_main_myself_default, R.drawable.icon_start_main_myself_focus));

        /* 绑定fragment的manager */
        tab_host.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tab_host.getTabWidget().setDividerDrawable(null);   // 删除tab分割线

        for (int i = 0; i < mFmTabs.size(); i++) {
            Tab tab = mFmTabs.get(i);
            TabHost.TabSpec tabSpec = tab_host.newTabSpec(tab.getTabText()).setIndicator(tab.getTabView(MainActivity.this));
            tab_host.addTab(tabSpec, tab.getFmClz(), null);
            //            tab_host.getTabWidget().setBackgroundResource(R.drawable.bottom_bar);
            tab_host.getTabWidget().setBackgroundColor(Color.TRANSPARENT);

            tab.setChecked(0 == i);     // 默认选中第一个tab
        }

        /* 切换tab回调 */
        tab_host.setOnTabChangedListener(tabId -> {
            for (int i = 0; i < mFmTabs.size(); i++) {
                Tab tab = mFmTabs.get(i);
                tab.setChecked(tabId.equals(tab.getTabText()));
                mTitle = tabId;
                updateToolBar();
            }
        });
    }


    /** 接收fragment参数 */
    @Override public void process(String process) {
        TToast.get(this).showShortNormal("接受到fragment信息：" + process);
    }


    /** tool bar */
    private ActionBar mBar;

    private void initToolBar() {
        Toolbar toolbar = mBinding.iTbStart.tbTitle;
        setSupportActionBar(toolbar);
        mBar = getSupportActionBar();
        if (null != mBar) {
            mBar.setDisplayShowHomeEnabled(true);
            mBar.setTitle(mTitle);
        }
        toolbar.setNavigationIcon(R.mipmap.flaticon_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private String mTitle = "功能";

    private void updateToolBar() {
        if (null != mBar) {
            mBar.setTitle(mTitle);
        }
    }


    /** 侧边导航 */
    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {
            Toast.makeText(this, "回到主页", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_gallery) {
            Toast.makeText(this, "gallery", Toast.LENGTH_SHORT).show();
        }

        mBinding.dl.closeDrawer(GravityCompat.START);
        return true;
    }


    /** 连按两次退出应用 */
    private long mPeriod = 0;

    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mPeriod) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mPeriod = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}

package cc.catface.module_start.main.mvp.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTabHost;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpActivity;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.module_start.R;
import cc.catface.module_start.databinding.StartActivityMainBinding;
import cc.catface.module_start.main.domain.Tab;
import cc.catface.module_start.main.media.view.VideoFm;
import cc.catface.module_start.main.mvp.presenter.MainPresenterImp;
import cc.catface.module_start.main.mvp.view.navigation.view.FunctionFm;
import cc.catface.module_start.main.mvp.view.navigation.view.MessFm;
import cc.catface.module_start.main.personal.mvp.view.PersonalFm;
import cc.catface.module_start.main.query.view.QueryFm;

@Route(path = Const.ARouter.start_main)
@CreatePresenter(MainPresenterImp.class)
public class MainActivity extends MvpActivity<MainView, MainPresenterImp, StartActivityMainBinding> implements MainView, MessFm.FragmentListener {
    @Override public int layoutId() {
        return R.layout.start_activity_main;
    }

    private FragmentTabHost tab_host;
    private List<Tab> mFmTabs;

    @SuppressLint("CheckResult") @Override public void create() {
        initToolBar();

        new RxPermissions(this)
                .request(Manifest.permission.READ_PHONE_STATE/*,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION*/)
                .subscribe(granted -> {
                    if (!granted) {
                        Toast.makeText(this, "请重启应用允许请求的权限", Toast.LENGTH_SHORT).show();
                    }
                    //所有权限通过，初始化界面
                    onPermissionChecked();
                });
    }


    @CallSuper
    protected void onPermissionChecked() {
        tab_host = (FragmentTabHost) findViewById(android.R.id.tabhost);
        initTab();
    }


    private void initTab() {
        mFmTabs = new ArrayList<>();
        mFmTabs.add(new Tab(FunctionFm.class, "功能", Color.argb(255, 26, 125, 235), R.drawable.icon_start_main_function_default, R.drawable.icon_start_main_function_focus));
        mFmTabs.add(new Tab(MessFm.class, "普通", Color.argb(255, 26, 125, 235), R.drawable.icon_start_main_text_image_default, R.drawable.icon_start_main_text_image_focus));
        mFmTabs.add(new Tab(VideoFm.class, "影音", Color.argb(255, 26, 125, 235), R.drawable.icon_start_main_video_default, R.drawable.icon_start_main_video_focus));
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

    private String mTitle = "功能", mNormalTitle = "";

    private void updateToolBar() {
        if (null != mBar) {
            mBar.setTitle(mTitle);
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_menu, menu);
        menu.findItem(R.id.menu_search).setVisible(false);
        menu.findItem(R.id.menu_normal).setVisible(!TextUtils.isEmpty(mNormalTitle.trim()));
        menu.findItem(R.id.menu_plus_1).setVisible(false);
        menu.findItem(R.id.menu_plus_2).setVisible(false);

        menu.findItem(R.id.menu_normal).setTitle(mNormalTitle);
        menu.findItem(R.id.menu_normal).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}

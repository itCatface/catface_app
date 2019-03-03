package cc.catface.module_start.main.mvp.view;

import android.graphics.Color;
import android.widget.TabHost;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentTabHost;
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

    @Override public void create() {
        tab_host = (FragmentTabHost) findViewById(android.R.id.tabhost);
        initTab();
    }

    private void initTab() {
        mFmTabs = new ArrayList<>();
        mFmTabs.add(new Tab(FunctionFm.class, "功能", Color.argb(255, 26, 125, 235), R.drawable.icon_start_main_function_default, R.drawable.icon_start_main_function_focus));
        mFmTabs.add(new Tab(MessFm.class, "mess", Color.argb(255, 26, 125, 235), R.drawable.icon_start_main_text_image_default, R.drawable.icon_start_main_text_image_focus));
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
        tab_host.setOnTabChangedListener(s -> {
            for (int i = 0; i < mFmTabs.size(); i++) {
                Tab tab = mFmTabs.get(i);

                tab.setChecked(s.equals(tab.getTabText()));
            }
        });
    }


    /** 接收fragment参数 */
    @Override public void process(String process) {
        TToast.get(this).showShortNormal("接受到fragment信息：" + process);
    }
}

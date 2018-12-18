package cc.catface.module_apis.showapi.mvp.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.core_framework.base_normal.NormalBaseActivityID;
import cc.catface.module_apis.R;
import cc.catface.module_apis.showapi.adapter.ImageTextAdapter;

@Route(path = "/apis/showapi")
public class ShowAPIActivity extends NormalBaseActivityID {

    @Override public int layoutId() {
        return R.layout.showapi_activity_show_api;
    }

    private TabLayout tl_mess;
    private ViewPager vp_mess;

    private String[] mTabTitles = {"笑话(文)", "笑话(图)", "笑话(gif)", "GIF", "视频"};
    private List<Fragment> mFms;

    @Override public void ids() {
        tl_mess = (TabLayout) findViewById(R.id.tl_mess);
        vp_mess = (ViewPager) findViewById(R.id.vp_mess);
    }


    @Override public void create() {
        LinearLayout ll = (LinearLayout) tl_mess.getChildAt(0);
        ll.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        ll.setDividerPadding(40);
        ll.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.showapi_shape_top_bar_line));

        mFms = new ArrayList<>();
//        mFms.add(new YYJoke341_1Fm());
        mFms.add(new YYJoke341_2Fm());
//        mFms.add(new YYJoke341_3Fm());

        vp_mess.setOffscreenPageLimit(mTabTitles.length);
        vp_mess.setAdapter(new ImageTextAdapter(getSupportFragmentManager(), mTabTitles, mFms));
        tl_mess.setupWithViewPager(vp_mess);
    }
}

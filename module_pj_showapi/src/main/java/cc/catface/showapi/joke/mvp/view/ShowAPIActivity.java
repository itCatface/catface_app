package cc.catface.showapi.joke.mvp.view;

import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.showapi.R;
import cc.catface.showapi.databinding.ShowapiActivityShowApiBinding;
import cc.catface.showapi.joke.adapter.ImageTextAdapter;

public class ShowAPIActivity extends LightAct<LightPresenter, ShowapiActivityShowApiBinding> {

    @Override public int layoutId() {
        return R.layout.showapi_activity_show_api;
    }

    private String[] mTabTitles = {"笑话(文)", "笑话(图)", "笑话(gif)", "GIF", "视频"};
    private List<Fragment> mFms;


    @Override protected void initView() {
        LinearLayout ll = (LinearLayout) mBinding.tlMess.getChildAt(0);
        ll.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        ll.setDividerPadding(40);
        ll.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.showapi_shape_top_bar_line));

        mFms = new ArrayList<>();
        mFms.add(new YYJoke341_1Fm());
        mFms.add(new YYJoke341_2Fm());
        mFms.add(new YYJoke341_3Fm());

        mBinding.vpMess.setOffscreenPageLimit(mTabTitles.length);
        mBinding.vpMess.setAdapter(new ImageTextAdapter(getSupportFragmentManager(), mTabTitles, mFms));
        mBinding.tlMess.setupWithViewPager(mBinding.vpMess);
    }
}

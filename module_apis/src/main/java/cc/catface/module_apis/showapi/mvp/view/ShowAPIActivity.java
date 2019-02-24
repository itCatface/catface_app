package cc.catface.module_apis.showapi.mvp.view;

import androidx.core.content.ContextCompat;

import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ShowapiActivityShowApiBinding;
import cc.catface.module_apis.showapi.adapter.ImageTextAdapter;

@Route(path = Const.AROUTER.apis_showapi)
public class ShowAPIActivity extends NormalActivity<ShowapiActivityShowApiBinding> {
    @Override public int layoutId() {
        return R.layout.showapi_activity_show_api;
    }

    private String[] mTabTitles = {"笑话(文)", "笑话(图)", "笑话(gif)", "GIF", "视频"};
    private List<Fragment> mFms;


    @Override public void create() {
        title();

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

    private void title() {
        mBinding.tfaShowapi.setTitle(getIntent().getStringExtra(Const.AROUTER.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
    }
}

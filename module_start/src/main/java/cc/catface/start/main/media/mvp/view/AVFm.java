package cc.catface.start.main.media.mvp.view;

import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.ctool.view.viewpager.PagerAdapterFm;
import cc.catface.start.R;
import cc.catface.start.databinding.FmAvBinding;
import cc.catface.start.main.media.mvp.vp.VideoPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class AVFm extends LightFm<VideoPresenterImp, FmAvBinding> implements LightView {

    @Override public int layoutId() {
        return R.layout.fm_av;
    }

    private String[] mTabTitles = {"音乐", "视频"};
    private List<Fragment> mFms;

    @Override protected void initView() {
        LinearLayout ll = (LinearLayout) mBinding.tlMedia.getChildAt(0);
        ll.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        ll.setDividerPadding(40);
        ll.setDividerDrawable(ContextCompat.getDrawable(mActivity, R.drawable.shape_vertical_line));

        mFms = new ArrayList<>();
        mFms.add(new MusicMainFm());
        mFms.add(new MusicMainFm());

        mBinding.vpMedia.setOffscreenPageLimit(mTabTitles.length);
        mBinding.vpMedia.setAdapter(new PagerAdapterFm(getChildFragmentManager(), Arrays.asList(mTabTitles), mFms));
        mBinding.tlMedia.setupWithViewPager(mBinding.vpMedia);
    }
}

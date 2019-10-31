package cc.catface.start.main.media.mvp.view;

import androidx.core.content.ContextCompat;

import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.start.R;
import cc.catface.start.databinding.FmSecondVideoBinding;
import cc.catface.start.main.media.mvp.vp.VideoPresenterImp;
import cc.catface.start.main.mvp.adapter.ImageTextAdapter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class VideoFm extends LightFm<VideoPresenterImp, FmSecondVideoBinding> implements LightView {

    @Override public int layoutId() {
        return R.layout.fm_second_video;
    }

    private String[] mTabTitles = {"音乐", "视频"};
    private List<Fragment> mFms;

    @Override protected void initView() {
        LinearLayout ll = (LinearLayout) mBinding.tlMedia.getChildAt(0);
        ll.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        ll.setDividerPadding(40);
        ll.setDividerDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.showapi_shape_top_bar_line));

        mFms = new ArrayList<>();
        mFms.add(new MusicMainFm());
        mFms.add(new MusicMainFm());

        mBinding.vpMedia.setOffscreenPageLimit(mTabTitles.length);
        mBinding.vpMedia.setAdapter(new ImageTextAdapter(getChildFragmentManager(), mTabTitles, mFms));
        mBinding.tlMedia.setupWithViewPager(mBinding.vpMedia);
    }
}

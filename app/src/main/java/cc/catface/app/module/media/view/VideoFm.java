package cc.catface.app.module.media.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cc.catface.app.R;
import cc.catface.app.module.media.presenter.VideoPresenterImp;
import cc.catface.app.module.start.main.view.navigation.adapter.ImageTextAdapter;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsFragment;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(VideoPresenterImp.class)
public class VideoFm extends AbsFragment<VideoView, VideoPresenterImp> implements VideoView {

    @Override public int layoutId() {
        return R.layout.fm_second_video;
    }

    @BindView(R.id.tl_media) TabLayout tl_media;
    @BindView(R.id.vp_media) ViewPager vp_media;

    private String[] mTabTitles = {"音乐", "视频"};
    private List<Fragment> mFms;

    @Override public void viewCreated() {
        LinearLayout ll = (LinearLayout) tl_media.getChildAt(0);
        ll.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        ll.setDividerPadding(40);
        ll.setDividerDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.showapi_shape_top_bar_line));

        mFms = new ArrayList<>();
        mFms.add(new MusicMainFm());
        mFms.add(new MusicMainFm());

        vp_media.setOffscreenPageLimit(mTabTitles.length);
        vp_media.setAdapter(new ImageTextAdapter(getChildFragmentManager(), mTabTitles, mFms));
        tl_media.setupWithViewPager(vp_media);
    }
}

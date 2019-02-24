package cc.catface.module_start.main.media.view;

import androidx.core.content.ContextCompat;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.module_start.R;
import cc.catface.module_start.databinding.FmSecondVideoBinding;
import cc.catface.module_start.main.media.presenter.VideoPresenterImp;
import cc.catface.module_start.main.mvp.view.navigation.adapter.ImageTextAdapter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(VideoPresenterImp.class)
public class VideoFm extends MvpFragment<VideoView, VideoPresenterImp, FmSecondVideoBinding> implements VideoView {
    @Override public int layoutId() {
        return R.layout.fm_second_video;
    }

    private String[] mTabTitles = {"音乐", "视频"};
    private List<Fragment> mFms;

    @Override public void viewCreated() {
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

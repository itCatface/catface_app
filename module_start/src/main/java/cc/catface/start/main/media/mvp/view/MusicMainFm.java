package cc.catface.start.main.media.mvp.view;

import android.os.Environment;

import java.util.Arrays;
import java.util.List;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.start.R;
import cc.catface.start.databinding.FmMusicMainBinding;
import cc.catface.start.main.media.mvp.vp.MusicMainPresenterImp;
import cc.catface.start.main.media.utils.MusicUtils;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class MusicMainFm extends LightFm<MusicMainPresenterImp, FmMusicMainBinding> implements LightView {


    @Override public int layoutId() {
        return R.layout.fm_music_main;
    }

    @Override protected void initView() {
    }

    private List<String> mSongs;

    @Override protected void initAction() {
        mBinding.btScan.setOnClickListener(v -> {
            mSongs = MusicUtils.getMatchSongs(Environment.getExternalStorageDirectory(), new String[]{".mp3"});

            TListView.str(mActivity, mBinding.lvSongs, mSongs.toArray(new String[mSongs.size()]), new TListView.Callback() {
                @Override public void onClick(int pos) {
                    TToast.get(mActivity).showShortNormal(mSongs.get(pos));
                }
            });
        });
    }

    @Override protected void initData() {

    }
}

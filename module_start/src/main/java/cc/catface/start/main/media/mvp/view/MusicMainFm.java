package cc.catface.start.main.media.mvp.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.start.R;
import cc.catface.start.databinding.FmMusicMainBinding;
import cc.catface.start.main.media.mvp.vp.MusicMainPresenterImp;
import cc.catface.start.main.media.service.ScanMusicService;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class MusicMainFm extends LightFm<MusicMainPresenterImp, FmMusicMainBinding> implements ServiceConnection, View.OnClickListener, LightView {


    @Override public int layoutId() {
        return R.layout.fm_music_main;
    }

    @Override protected void initAction() {
        mBinding.btScan.setOnClickListener(this);
        mBinding.btPlay.setOnClickListener(this);
    }

    private Intent mServiceIntent;

    @Override protected void created() {
        mServiceIntent = new Intent(mActivity, ScanMusicService.class);
    }

    @Override public void onClick(View view) {

        if (R.id.bt_scan == view.getId()) {
            if (mBinding.btScan.getText().toString().trim().equals("加载本地歌曲")) {
                mBinding.btScan.setText("停止加载本地歌曲");
                if (null != mBinder) {
                    mBinder.setData("开始任务-->搜索本机所有歌曲");
                    mActivity.startService(mServiceIntent);
                }
            } else {
                mBinding.btScan.setText("加载本地歌曲");
                mActivity.stopService(mServiceIntent);
            }
        } else if (R.id.bt_play == view.getId()) {
            // TODO ARouter
            String value = "" + System.currentTimeMillis();
            Toast.makeText(mActivity, "生成数据：" + value, Toast.LENGTH_SHORT).show();
        }
    }


    /** 使用ServiceConnection与activity通信 */
    private ScanMusicService.Binder mBinder = null;

    @Override public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        mBinder = (ScanMusicService.Binder) iBinder;
    }

    @Override public void onServiceDisconnected(ComponentName componentName) {

    }
}

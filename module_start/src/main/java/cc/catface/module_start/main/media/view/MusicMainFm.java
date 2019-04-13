package cc.catface.module_start.main.media.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.module_start.R;
import cc.catface.module_start.databinding.FmMusicMainBinding;
import cc.catface.module_start.main.media.presenter.MusicMainPresenterImp;
import cc.catface.module_start.main.media.service.ScanMusicService;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(MusicMainPresenterImp.class)
public class MusicMainFm extends MvpFragment<MusicMainView, MusicMainPresenterImp, FmMusicMainBinding> implements MusicMainView, ServiceConnection, View.OnClickListener {
    private Intent mServiceIntent;

    @Override public int layoutId() {
        return R.layout.fm_music_main;
    }

    @Override protected void initAction() {
        mBinding.btScan.setOnClickListener(this);
        mBinding.btPlay.setOnClickListener(this);
    }


    @Override public void viewCreated() {
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

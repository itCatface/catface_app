package cc.catface.module_start.main.media.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsFragmentID;
import cc.catface.module_start.R;
import cc.catface.module_start.main.media.presenter.MusicMainPresenterImp;
import cc.catface.module_start.main.media.service.ScanMusicService;
import cc.catface.module_start.main.mvp.view.MainActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(MusicMainPresenterImp.class)
public class MusicMainFm extends AbsFragmentID<MusicMainView, MusicMainPresenterImp> implements MusicMainView, ServiceConnection, View.OnClickListener {
    private MainActivity mActivity;
    private Intent mServiceIntent;

    @Override public int layoutId() {
        return R.layout.fm_music_main;
    }

    private ProgressBar pb;
    private Button bt_scan;
    private Button bt_play;

    @Override public void ids(View v) {
        pb = (ProgressBar) v.findViewById(R.id.pb);
        bt_scan = (Button) v.findViewById(R.id.bt_scan);
        bt_play = (Button) v.findViewById(R.id.bt_play);
        bt_scan.setOnClickListener(this);
        bt_play.setOnClickListener(this);
    }

    @Override public void onClick(View view) {

        if (R.id.bt_scan == view.getId()) {
            if (bt_scan.getText().toString().trim().equals("加载本地歌曲")) {
                bt_scan.setText("停止加载本地歌曲");
                if (null != mBinder) {
                    mBinder.setData("开始任务-->搜索本机所有歌曲");
                    mActivity.startService(mServiceIntent);
                }
            } else {
                bt_scan.setText("加载本地歌曲");
                mActivity.stopService(mServiceIntent);
            }
        } else if (R.id.bt_play == view.getId()) {
            // TODO ARouter
            String value = "" + SystemClock.currentThreadTimeMillis();
            Toast.makeText(mActivity, "生成数据：" + value, Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(Const.ROUTE.test_arouter).withString("value", value).navigation();
        }
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
    }

    @Override public void viewCreated() {
        mServiceIntent = new Intent(mActivity, ScanMusicService.class);
    }


    /** 使用ServiceConnection与activity通信 */
    private ScanMusicService.Binder mBinder = null;

    @Override public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        mBinder = (ScanMusicService.Binder) iBinder;
    }

    @Override public void onServiceDisconnected(ComponentName componentName) {

    }
}

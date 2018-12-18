package cc.catface.app.module.media.view;

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

import butterknife.BindView;
import butterknife.OnClick;
import cc.catface.app.R;
import cc.catface.app.module.media.presenter.MusicMainPresenterImp;
import cc.catface.app.module.media.service.ScanMusicService;
import cc.catface.app.module.start.main.view.MainActivity;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsFragment;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(MusicMainPresenterImp.class)
public class MusicMainFm extends AbsFragment<MusicMainView, MusicMainPresenterImp> implements MusicMainView, ServiceConnection {
    private MainActivity mActivity;
    private Intent mServiceIntent;

    @Override public int layoutId() {
        return R.layout.fm_music_main;
    }

    @BindView(R.id.pb) ProgressBar pb;
    @BindView(R.id.bt_scan) Button bt_scan;
    @BindView(R.id.bt_play) Button bt_play;

    @OnClick({R.id.bt_scan, R.id.bt_play}) void event(View view) {
        switch (view.getId()) {
            case R.id.bt_scan:
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
                break;

            case R.id.bt_play:
                // TODO ARouter
                String value = "" + SystemClock.currentThreadTimeMillis();
                Toast.makeText(mActivity, "生成数据：" + value, Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build(Const.ROUTE.test_arouter).withString("value", value).navigation();
                break;
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

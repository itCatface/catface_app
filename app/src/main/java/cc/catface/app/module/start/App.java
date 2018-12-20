package cc.catface.app.module.start;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.mob.MobSDK;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import cc.catface.app.R;
import cc.catface.app.module.start.splash.view.SplashActivity;
import cc.catface.app_base.ARouterApp;
import cc.catface.app_base.Const;
import cc.catface.base.utils.android.CrashHandler;
import cc.catface.module_start.main.mvp.view.MainActivity;
import cc.catface.module_start.main.personal.mvp.view.SettingActivity;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        /* 各模块依赖的Application初始化操作 */
        ARouterApp.setContext(getApplicationContext());
        ARouterApp.initDB();
        initARouter();

        initIflytek();

        initJPush();

        initMob();

        initLeakCanary();

        CrashHandler.getInstance().init(this);

        Fresco.initialize(this);

        initBugly();

    }


    /** 64k */
    @Override protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    private void initARouter() {
        if (Const.IS_DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }


    private void initIflytek() {
        String param = ("appid=" + getString(R.string.app_id)) + "," + SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_MSC;
        SpeechUtility.createUtility(App.this, param);
    }


    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }


    private void initMob() {
        MobSDK.init(this);
    }


    private void initLeakCanary() {
        if (Const.IS_DEBUG) LeakCanary.install(this);
    }

    private void initBugly() {
        Beta.canShowUpgradeActs.add(SplashActivity.class);
        Beta.canShowUpgradeActs.add(SettingActivity.class);
        Beta.upgradeDialogLayoutId =R.layout.bugly_dialog_upgrade;
        Bugly.init(getApplicationContext(), getResources().getString(R.string.app_id_bugly), Const.IS_DEBUG);
    }
}

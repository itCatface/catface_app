package cc.catface.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
//import com.mob.MobSDK;
import com.squareup.leakcanary.LeakCanary;

import androidx.multidex.MultiDex;

import java.lang.ref.WeakReference;

import cc.catface.app_base.ARouterApp;
import cc.catface.app_base.Const;
import cc.catface.base.AppBase;
import cc.catface.base.utils.android.crash.CrashHandler;
import cc.catface.ctool.context.TContext;
import cc.catface.module_start.CrashHandlerActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        /* 各模块依赖的Application初始化操作 */
        AppBase.setContext(this);
        ARouterApp.setContext(this);
        ARouterApp.initDB();

        TContext.setContext(new WeakReference<>(this));

        /* 初始化ARouter */
        initARouter();

        /* 初始化讯飞能力 */
        initIflytek();

        /* 初始化MOB能力[分享] */
        initMob();

        /* 初始化内存泄漏检查工具 */
        initLeakCanary();

        /* 初始化全局崩溃拦截器 */
        initCrashHandler();

        /* 初始化Fresco[图片加载] */
        Fresco.initialize(this);
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


    private void initMob() {
        //        MobSDK.init(this);
    }


    private void initLeakCanary() {
        if (Const.IS_DEBUG) LeakCanary.install(this);
    }

    private void initCrashHandler() {
        CrashHandler.getInstance().setCrashListener(info -> {
            Intent intent = new Intent(this, CrashHandlerActivity.class);
            intent.putExtra("info", info);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
        }).init(this);
    }
}

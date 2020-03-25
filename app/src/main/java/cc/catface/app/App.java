package cc.catface.app;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;

import java.lang.ref.WeakReference;

import cc.catface.app_base.ARouterApp;
import cc.catface.app_base.Const;
import cc.catface.ctool.context.TContext;
import cc.catface.ctool.system.TLog;
import cc.catface.ctool.system.sensor.camera.TPhoto;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        TPhoto.handle7Camera();

        /* 初始化日志打印 */
        TLog.init("catface", true, false, "");

        /* 各模块依赖的Application初始化操作 */
        ARouterApp.setContext(this);
        ARouterApp.initDB();

        TContext.setContext(new WeakReference<>(this));

        /* 初始化ARouter */
        initARouter();

        /* 初始化内存泄漏检查工具 */
        initLeakCanary();

        /* 初始化Fresco[图片加载] */
        Fresco.initialize(this);
    }


    /**
     * 64k
     */
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


    private void initLeakCanary() {
        if (Const.IS_DEBUG) LeakCanary.install(this);
    }

}

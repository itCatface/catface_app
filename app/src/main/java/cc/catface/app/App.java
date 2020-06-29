package cc.catface.app;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVLogLevel;

import cc.catface.app_base.ARouterApp;
import cc.catface.app_base.Const;
import cc.catface.ctool.context.TApp;
import cc.catface.ctool.java.TThreadPool;
import cc.catface.ctool.system.TLog;
import cc.catface.ctool.system.sensor.camera.TPhoto;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class App extends TApp {

    @Override
    public void onCreate() {
        super.onCreate();
        long l = System.currentTimeMillis();

        TThreadPool.EXECUTOR.submit(() -> {
            TPhoto.handle7Camera();

            String mmkvDir = MMKV.initialize("sdcard/catface/mmkv");
            MMKV.setLogLevel(MMKVLogLevel.LevelDebug);
            TLog.d("App-->mmkvDir: " + mmkvDir);

            /* 初始化日志打印 */
            TLog.init("tag_catface", true, false, "");

            /* 各模块依赖的Application初始化操作 */
            ARouterApp.setContext(this);
            ARouterApp.initDB();


            /* 初始化ARouter */
            initARouter();

            /* 初始化内存泄漏检查工具 */
            initLeakCanary();

            /* 初始化Fresco[图片加载] */
            Fresco.initialize(this);

            TLog.d("application oncreate time: " + (System.currentTimeMillis() - l));
        });


    }


    /**
     * 64k
     */
    @Override
    protected void attachBaseContext(Context base) {
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

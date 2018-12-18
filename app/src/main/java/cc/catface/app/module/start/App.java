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

import cc.catface.app.R;
import cc.catface.app_base.ARouterApp;
import cc.catface.base.utils.android.CrashHandler;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class App extends Application {
    public static final boolean IS_DEBUG = true;
    private static Context mCtx;

    @Override public void onCreate() {
        super.onCreate();
        mCtx = getApplicationContext();
        ARouterApp.setContext(mCtx);

        mInstance = this;
//        initDB();
        ARouterApp.initDB();

        initIflytek();

        initJPush();

        initMob();

        initLeakCanary();

        CrashHandler.getInstance().init(this);

        Fresco.initialize(this);

        initARouter();
    }

    /** 获取全局上下文 */
    public static Context getCtx() {
        return mCtx;
    }

    private static App mInstance;

    public static App getInstance() {
        return mInstance;
    }

//    private DaoMaster.DevOpenHelper mHelper;
//    private SQLiteDatabase db;
//    private DaoMaster mDaoMaster;
//    private DaoSession mDaoSession;
//
//    private void initDB() {
//        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
//        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
//        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
//        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
//        mHelper = new DaoMaster.DevOpenHelper(this, "sport-db", null);
//        db = mHelper.getWritableDatabase();
//        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
//        mDaoMaster = new DaoMaster(db);
//        mDaoSession = mDaoMaster.newSession();
//    }
//
//    public DaoSession getDaoSession() {
//        return mDaoSession;
//    }
//
//    public SQLiteDatabase getDb() {
//        return db;
//    }


    /** 64k */
    @Override protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initIflytek() {
        StringBuffer param = new StringBuffer();
        param.append("appid=" + getString(R.string.app_id));
        param.append(",");
        // 设置使用v5+
        param.append(SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_MSC);
        SpeechUtility.createUtility(App.this, param.toString());
    }

    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    private void initMob() {
        MobSDK.init(this);
    }

    private void initLeakCanary() {
        LeakCanary.install(this);
    }

    private void initARouter() {
        if (IS_DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

}

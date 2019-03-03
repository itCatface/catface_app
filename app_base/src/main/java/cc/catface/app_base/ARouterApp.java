package cc.catface.app_base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;

import cc.catface.app_base.greendao.domain.greendao_gen.DaoMaster;
import cc.catface.app_base.greendao.domain.greendao_gen.DaoSession;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ARouterApp extends Application {
    /** 主模块context */
    @SuppressLint("StaticFieldLeak") private static Context mCtx;

    public static void setContext(Context ctx) {
        mCtx = ctx;
    }

    public static Context getContext() {
        if (null == mCtx) throw new RuntimeException("基础模块中context为null!!!");
        return mCtx;
    }


    /** memo-db模块 */
    private static DaoMaster.DevOpenHelper mHelper;
    private static SQLiteDatabase db;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    public static void initDB() {
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(mCtx, "sport-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static SQLiteDatabase getDb() {
        return db;
    }


    @Override public void onCreate() {
        super.onCreate();
        initARouter();
    }


    private void initARouter() {
        if (Const.IS_DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}

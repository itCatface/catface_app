package cc.catface.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class AppBase extends Application {

    /** 主模块context */
    @SuppressLint("StaticFieldLeak") private static Context mCtx;

    public static void setContext(Context ctx) {
        mCtx = ctx;
    }

    public static Context getContext() {
        if (null == mCtx) throw new RuntimeException("基础模块中context为null!!!");
        return mCtx;
    }

    @Override public void onCreate() {
        super.onCreate();
    }
}

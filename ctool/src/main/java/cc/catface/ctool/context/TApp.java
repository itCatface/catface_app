package cc.catface.ctool.context;

import android.app.Application;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TApp extends Application {

    private static TApp instance;

    public static TApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}

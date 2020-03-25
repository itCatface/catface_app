package cc.catface.iflytek;

import android.app.Application;
import android.os.StrictMode;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class IflytekApp extends Application {

    @Override public void onCreate() {
        super.onCreate();

        /* android 7.0系统解决拍照的问题 */
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }
}

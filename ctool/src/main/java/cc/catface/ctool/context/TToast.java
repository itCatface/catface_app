package cc.catface.ctool.context;

import android.widget.Toast;

import cc.catface.ctool.system.TLog;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TToast {

    public static void showNormal(String msg) {
        Toast.makeText(TApp.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showNormalD(String msg) {
        showNormal(msg);
        TLog.d(msg);
    }

    public static void showNormal(String msg, boolean isLong) {
        Toast.makeText(TApp.getInstance(), msg, Toast.LENGTH_LONG).show();
    }

}

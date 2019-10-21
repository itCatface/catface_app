package cc.catface.ctool.context;

import android.widget.Toast;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TToast {

    public static void showNormal(String msg) {
        Toast.makeText(TContext.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showNormal(String msg, boolean isLong) {
        Toast.makeText(TContext.getContext(), msg, Toast.LENGTH_LONG).show();
    }

}

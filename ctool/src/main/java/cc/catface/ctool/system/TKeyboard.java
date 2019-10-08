package cc.catface.ctool.system;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TKeyboard {

    public static void open(EditText et, Context ctx) {
        InputMethodManager manager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null == manager) return;
        manager.showSoftInput(et, InputMethodManager.RESULT_SHOWN);
        manager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    public static void close(EditText et, Context ctx) {
        InputMethodManager manager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null == manager) return;
        manager.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }


    /*xxx*/
    public static boolean isOpen(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        InputMethodManager manager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (null == view || null == manager) return false;
        return manager.isActive() && activity.getWindow().getCurrentFocus() != null;
    }
}

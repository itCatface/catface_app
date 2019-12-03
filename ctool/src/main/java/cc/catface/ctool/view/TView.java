package cc.catface.ctool.view;

import android.view.View;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TView {

    public static void gone(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    public static void invisible(View... views) {
        for (View view : views) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    public static void visible(View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

}

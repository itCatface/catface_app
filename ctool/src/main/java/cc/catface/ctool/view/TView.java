package cc.catface.ctool.view;

import android.view.View;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TView {

    /**
     * 设置所有控件显示属性为View.GONE
     *
     * @param views 控件列表
     */
    public static void gone(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 设置所有控件显示属性为View.INVISIBLE
     *
     * @param views 控件列表
     */
    public static void invisible(View... views) {
        for (View view : views) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置所有控件显示属性为View.VISIBLE
     *
     * @param views 控件列表
     */
    public static void visible(View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

}

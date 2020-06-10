package cc.catface.ctool.context;

import android.graphics.drawable.Drawable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TResource {

    public static String getString(int id) {
        return TApp.getInstance().getResources().getString(id);
    }

    public static Drawable getDrawable(int id) {
        return TApp.getInstance().getResources().getDrawable(id);
    }

    public static int getColor(int id) {
        return TApp.getInstance().getResources().getColor(id);
    }

    public static int getDimen(int id) {
        return TApp.getInstance().getResources().getDimensionPixelSize(id);
    }
}

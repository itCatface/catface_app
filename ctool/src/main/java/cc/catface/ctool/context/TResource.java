package cc.catface.ctool.context;

import android.graphics.drawable.Drawable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TResource {

    public static String getString(int id) {
        return TContext.getContext().getResources().getString(id);
    }

    public static Drawable getDrawable(int id) {
        return TContext.getContext().getResources().getDrawable(id);
    }

    public static int getColor(int id) {
        return TContext.getContext().getResources().getColor(id);
    }

    public static int getDimen(int id) {
        return TContext.getContext().getResources().getDimensionPixelSize(id);
    }
}

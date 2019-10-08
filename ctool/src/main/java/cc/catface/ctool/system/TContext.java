package cc.catface.ctool.system;

import android.content.Context;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TContext {

    private static Context mContext;

    public static void setContext(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        if (null == mContext) throw new NullPointerException("CTool的context为空");
        return mContext;
    }

}

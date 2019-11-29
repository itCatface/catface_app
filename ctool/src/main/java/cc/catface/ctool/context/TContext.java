package cc.catface.ctool.context;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TContext {

    private static WeakReference<Context> mWeakContext;

    public static void setContext(WeakReference<Context> weakContext) {
        if (null == mWeakContext) mWeakContext = weakContext;
    }

    public static Context getContext() {
        if (null == mWeakContext) throw new RuntimeException("CTool的context为空");
        return mWeakContext.get();
    }
}

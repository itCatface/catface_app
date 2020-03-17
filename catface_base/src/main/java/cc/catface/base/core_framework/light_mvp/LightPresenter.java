package cc.catface.base.core_framework.light_mvp;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class LightPresenter<V extends LightView> {

    private WeakReference<V> mWeakReference;
    private Context mContext;

    protected LightPresenter() { }

    protected LightPresenter(V view, Context context) {
        mWeakReference = new WeakReference<>(view);
        this.mContext = context;
    }

    void onDetachView() {
        if (null != mWeakReference) {
            mWeakReference.clear();
            mWeakReference = null;
        }
    }

    protected V getView() {
        return mWeakReference.get();
    }

    protected Context getCtx() {
        return mContext;
    }

}

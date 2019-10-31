package cc.catface.base.core_framework.light_mvp;

import android.content.Context;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class LightPresenter<V extends LightView> {

    protected V mView;
    protected Context mContext;

    protected LightPresenter() {

    }

    protected LightPresenter(V view, Context context) {
        this.mView = view;
        this.mContext = context;
    }


    void onDetachView() {
        if (null != mView) mView = null;
    }

    protected boolean isViewEnabled() {
        return null != mView;
    }

}

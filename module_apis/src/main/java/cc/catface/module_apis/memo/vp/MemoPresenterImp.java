package cc.catface.module_apis.memo.vp;

import android.content.Context;

import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class MemoPresenterImp extends LightPresenter<MemoVP.MemoView> implements MemoVP.MemoPresenter {

    public MemoPresenterImp(MemoVP.MemoView view, Context context) {
        super(view, context);
    }

}

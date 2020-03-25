package cc.catface.start.splash.vp;


import android.content.Context;
import android.content.Intent;

import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.crash.CrashHandler;
import cc.catface.start.CrashHandlerActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SplashPresenterImp extends LightPresenter<SplashVP.SplashView> implements SplashVP.SplashPresenter {

    public SplashPresenterImp(SplashVP.SplashView view, Context context) {
        super(view, context);
    }

    public void initCrashHandler() {
        CrashHandler.getInstance().setCrashListener(info -> {
            Intent intent = new Intent(getCtx(), CrashHandlerActivity.class);
            intent.putExtra("info", info);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getCtx().startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
        }).init(getCtx());
    }

}

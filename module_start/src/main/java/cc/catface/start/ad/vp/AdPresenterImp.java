package cc.catface.start.ad.vp;


import android.content.Context;
import android.content.Intent;

import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.crash.CrashHandler;
import cc.catface.ctool.context.TApp;
import cc.catface.start.CrashHandlerActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc: application启动activity需要添加flag-Intent.FLAG_ACTIVITY_NEW_TASK
 */
public class AdPresenterImp extends LightPresenter<AdVP.AdView> implements AdVP.AdPresenter {

    public AdPresenterImp(AdVP.AdView view, Context context) {
        super(view, context);
    }

    public void initCrashHandler() {
        CrashHandler.getInstance().setCrashListener(info -> {
            Intent intent = new Intent(TApp.getInstance(), CrashHandlerActivity.class);
            intent.putExtra("info", info);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            TApp.getInstance().startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
        }).init();
    }
}

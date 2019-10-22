package cc.catface.ctool.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TActivity {

    public static void startActivity(Context ctx, Class clz) {
        ctx.startActivity(new Intent(ctx, clz));
    }

    public static void startActivityFinish(Context ctx, Class clz) {
        ctx.startActivity(new Intent(ctx, clz));
        ((Activity) ctx).finish();
    }

    public static void startActivity(Context ctx, Class clz, Bundle bundle) {
        Intent intent = new Intent(ctx, clz);
        intent.putExtras(bundle);
        ctx.startActivity(intent);
    }

    public static void startActivityFinish(Context ctx, Class clz, Bundle bundle) {
        Intent intent = new Intent(ctx, clz);
        intent.putExtras(bundle);
        ctx.startActivity(intent);
        ((Activity) ctx).finish();
    }


    /** Activity出栈管理 */
    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        if (null != activities) activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        if (null != activities) activities.remove(activity);
    }

    public static void finishAllActivities() {
        if (null == activities) return;

        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }
}

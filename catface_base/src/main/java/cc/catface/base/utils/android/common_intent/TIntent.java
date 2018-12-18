package cc.catface.base.utils.android.common_intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.R;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TIntent implements TIntentI {

    public static void startActivity(Context ctx, Class clz, boolean in) {
        ctx.startActivity(new Intent(ctx, clz));

        if (IS_ANIM_ON) {
            if (in) ((Activity) ctx).overridePendingTransition(R.anim.intent_zoom_start_in_default, R.anim.intent_zoom_start_out_default);
            else ((Activity) ctx).overridePendingTransition(R.anim.intent_zoom_exit_in_default, R.anim.intent_zoom_exit_out_default);
        }
    }

    public static void startActivityAndFinish(Context ctx, Class clz, boolean in) {
        ctx.startActivity(new Intent(ctx, clz));

        if (IS_ANIM_ON) {
            if (in) ((Activity) ctx).overridePendingTransition(R.anim.intent_zoom_start_in_default, R.anim.intent_zoom_start_out_default);
            else ((Activity) ctx).overridePendingTransition(R.anim.intent_zoom_exit_in_default, R.anim.intent_zoom_exit_out_default);
        }

        ((Activity) ctx).finish();
    }

    public static void startActivity(Context ctx, Class clz, boolean in, Bundle bundle) {
        Intent intent = new Intent(ctx, clz);
        intent.putExtras(bundle);
        ctx.startActivity(intent);

        if (IS_ANIM_ON) {
            if (in) ((Activity) ctx).overridePendingTransition(R.anim.intent_zoom_start_in_default, R.anim.intent_zoom_start_out_default);
            else ((Activity) ctx).overridePendingTransition(R.anim.intent_zoom_exit_in_default, R.anim.intent_zoom_exit_out_default);
        }
    }

    public static void startActivityAndFinish(Context ctx, Class clz, boolean in, Bundle bundle) {
        Intent intent = new Intent(ctx, clz);
        intent.putExtras(bundle);
        ctx.startActivity(intent);

        if (IS_ANIM_ON) {
            if (in) ((Activity) ctx).overridePendingTransition(R.anim.intent_zoom_start_in_default, R.anim.intent_zoom_start_out_default);
            else ((Activity) ctx).overridePendingTransition(R.anim.intent_zoom_exit_in_default, R.anim.intent_zoom_exit_out_default);
        }

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
        if (null != activities) for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        if (null != activities) activities.clear();
    }
}

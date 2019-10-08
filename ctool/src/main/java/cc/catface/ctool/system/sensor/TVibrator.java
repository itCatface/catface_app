package cc.catface.ctool.system.sensor;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TVibrator {

    private static Vibrator mVibrator;

    private static boolean isVibratorEnabled(Context context) {
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        return null != mVibrator;
    }


    /** exposed */
    public static void play(Context context, long milliseconds) {
        if (isVibratorEnabled(context)) mVibrator.vibrate(milliseconds);
    }


    public static void play(Context context, long[] pattern, int repeat) {
        if (isVibratorEnabled(context)) mVibrator.vibrate(pattern, repeat);
    }


    public static void cancel(Context context) {
        if (isVibratorEnabled(context)) mVibrator.cancel();
    }
}

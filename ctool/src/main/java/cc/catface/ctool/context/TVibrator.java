package cc.catface.ctool.context;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TVibrator {

    private static Vibrator mVibrator;

    private static boolean isVibratorEnabled() {
        mVibrator = (Vibrator) TApp.getInstance().getSystemService(Context.VIBRATOR_SERVICE);
        return null != mVibrator;
    }


    /** exposed */
    public static void play(long milliseconds) {
        if (isVibratorEnabled()) mVibrator.vibrate(milliseconds);
    }


    public static void play(long[] pattern, int repeat) {
        if (isVibratorEnabled()) mVibrator.vibrate(pattern, repeat);
    }


    public static void cancel() {
        if (isVibratorEnabled()) mVibrator.cancel();
    }
}

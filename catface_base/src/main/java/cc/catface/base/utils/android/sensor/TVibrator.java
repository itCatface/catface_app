package cc.catface.base.utils.android.sensor;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TVibrator {


    public static void play(Context context, long milliseconds) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(milliseconds);
    }


    public static void play(Context context, long[] pattern, int repeat) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, repeat);
    }


    public static void cancel(Context context) {
        ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).cancel();
    }
}

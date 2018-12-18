package cc.catface.base.utils.android.Timer;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TTimer {

    public interface TimeFinishedCallback {
        void timeFinish();
    }

    public static void timeFinished(int millisInFuture, final TimeFinishedCallback callback) {
        new CountDownTimer(millisInFuture, 1_000) {
            @Override public void onTick(long millisUntilFinished) {

            }

            @Override public void onFinish() {
                if (null != callback) callback.timeFinish();
            }
        }.start();
    }
}

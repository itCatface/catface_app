package cc.catface.ctool.system.Timer;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import androidx.annotation.NonNull;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc: CountDownTimer最后一秒不回调-fix
 */
public abstract class CountDownTimer {

    private final long mMillisInFuture;

    private final long mCountdownInterval;

    private long mStopTimeInFuture;

    private boolean mCancelled = false;

    public CountDownTimer(long millisInFuture, long countDownInterval) {
        mMillisInFuture = millisInFuture;
        mCountdownInterval = countDownInterval;
    }

    public synchronized final void cancel() {
        mCancelled = true;
        mHandler.removeMessages(MSG);
    }

    public synchronized final CountDownTimer start() {
        mCancelled = false;
        if (mMillisInFuture <= 0) {
            onFinish();
            return this;
        }

        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        return this;
    }

    public abstract void onTick(long millisUntilFinished);

    public abstract void onFinish();

    private static final int MSG = 1;

    @SuppressLint("HandlerLeak") private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {

            synchronized (CountDownTimer.this) {
                if (mCancelled) return;

                final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();
                if (millisLeft <= 0) onFinish();

                else {
                    long lastTickStart = SystemClock.elapsedRealtime();
                    onTick(millisLeft);
                    long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();
                    while (delay < 0) delay += mCountdownInterval;
                    sendMessageDelayed(obtainMessage(MSG), delay);
                }
            }
        }
    };
}
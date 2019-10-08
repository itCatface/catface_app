package cc.catface.ctool.view.click;

import android.view.View;

import java.util.Calendar;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class AntiShakeClickListener implements View.OnClickListener {

    private long mInterval;
    private long mLastClickTime = 0;

    public AntiShakeClickListener() {
        this.mInterval = 1_000;
    }

    public AntiShakeClickListener(int interval) {
        this.mInterval = interval;
    }

    @Override public void onClick(View view) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - mLastClickTime > mInterval) {
            mLastClickTime = currentTime;
            onAntiShakeClick(view);
        }
    }

    protected abstract void onAntiShakeClick(View view);
}
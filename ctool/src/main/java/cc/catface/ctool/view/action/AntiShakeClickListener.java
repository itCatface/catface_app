package cc.catface.ctool.view.action;

import android.view.View;

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
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastClickTime > mInterval) {
            mLastClickTime = currentTime;
            onAntiShakeClick(view);
        }
    }

    protected abstract void onAntiShakeClick(View view);
}
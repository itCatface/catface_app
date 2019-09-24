package cc.catface.api.view.loading.view_publish;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.RequiresApi;

import cc.catface.api.R;
import cc.catface.base.utils.android.listener.AnimatorEndListener;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 *
 * @desc 矩形波浪
 */
public class WaveView extends FrameLayout {
    private Context mCtx;
    private int mDuration = 700;


    @RequiresApi(api = Build.VERSION_CODES.M) public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        post(() -> {
            init();
            invalidate();
            startAnim();
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.M) private void init() {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = this.getWidth() / 10;
        layoutParams.height = this.getHeight() / 2;
        layoutParams.setMargins(0, getMeasuredHeight() / 4, 0, 0);

        view1 = new View(mCtx);
        view2 = new View(mCtx);
        view3 = new View(mCtx);
        view4 = new View(mCtx);
        view5 = new View(mCtx);

        view1.setLayoutParams(layoutParams);
        view2.setLayoutParams(layoutParams);
        view3.setLayoutParams(layoutParams);
        view4.setLayoutParams(layoutParams);
        view5.setLayoutParams(layoutParams);

        view2.setTranslationX(getMeasuredWidth() * 0.2f);
        view3.setTranslationX(getMeasuredWidth() * 0.4f);
        view4.setTranslationX(getMeasuredWidth() * 0.6f);
        view5.setTranslationX(getMeasuredWidth() * 0.8f);

        view1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view4.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view5.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        this.addView(view1);
        this.addView(view2);
        this.addView(view3);
        this.addView(view4);
        this.addView(view5);
    }


    private View view1, view2, view3, view4, view5;


    public void startAnim() {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view1, "scaleY", 1f, 2f, 1f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view2, "scaleY", 1f, 2f, 1f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view3, "scaleY", 1f, 2f, 1f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(view4, "scaleY", 1f, 2f, 1f);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(view5, "scaleY", 1f, 2f, 1f);

        anim1.setDuration(mDuration).setStartDelay(0);
        anim2.setDuration(mDuration).setStartDelay(200);
        anim3.setDuration(mDuration).setStartDelay(400);
        anim4.setDuration(mDuration).setStartDelay(600);
        anim5.setDuration(mDuration).setStartDelay(800);


        AnimatorSet as = new AnimatorSet();
        as.playTogether(anim1, anim2, anim3, anim4, anim5);
        as.start();
        as.addListener(new AnimatorEndListener() {
            @Override public void onAnimationEnd(Animator animation) {
                as.start();
            }
        });
    }
}


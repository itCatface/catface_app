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
 */
public class CircleView extends FrameLayout {
    private Context mCtx;
    private int mDuration = 900;


    @RequiresApi(api = Build.VERSION_CODES.M) public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        post(() -> {
            init();
            invalidate();
            startAnim();
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.M) private void init() {
        FrameLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = this.getWidth() / 5;
        layoutParams.height = this.getHeight() / 5;

        view11 = new View(mCtx);

        view21 = new View(mCtx);
        view22 = new View(mCtx);

        view31 = new View(mCtx);
        view32 = new View(mCtx);
        view33 = new View(mCtx);

        view41 = new View(mCtx);
        view42 = new View(mCtx);

        view51 = new View(mCtx);

        view11.setLayoutParams(layoutParams);

        view21.setLayoutParams(layoutParams);
        view22.setLayoutParams(layoutParams);

        view31.setLayoutParams(layoutParams);
        view32.setLayoutParams(layoutParams);
        view33.setLayoutParams(layoutParams);

        view41.setLayoutParams(layoutParams);
        view42.setLayoutParams(layoutParams);

        view51.setLayoutParams(layoutParams);



        view11.setTranslationY(getMeasuredHeight() * 2 / 3f);

        view21.setTranslationY(getMeasuredWidth() / 3f);

        view22.setTranslationX(getMeasuredWidth() / 3f);
        view22.setTranslationY(getMeasuredWidth() * 2 / 3f);

        view32.setTranslationX(getMeasuredHeight() / 3f);
        view32.setTranslationY(getMeasuredHeight() / 3f);

        view33.setTranslationX(getMeasuredHeight() * 2 / 3f);
        view33.setTranslationY(getMeasuredHeight() * 2 / 3f);

        view41.setTranslationX(getMeasuredHeight() / 3f);

        view42.setTranslationX(getMeasuredHeight() * 2 / 3f);
        view42.setTranslationY(getMeasuredHeight() / 3f);

        view51.setTranslationX(getMeasuredHeight() * 2 / 3f);

//        view11.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view11.setBackgroundResource(R.drawable.api_shape_circle);


        view21.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view22.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view31.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view32.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view33.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view41.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view42.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view51.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        this.addView(view11);
        this.addView(view21);
        this.addView(view22);
        this.addView(view31);
        this.addView(view32);
        this.addView(view33);
        this.addView(view41);
        this.addView(view42);
        this.addView(view51);
    }


    private View view11, view21, view22, view31, view32, view33, view41, view42, view51;


    public void startAnim() {
        ObjectAnimator anim111 = ObjectAnimator.ofFloat(view11, "scaleX", 1f, 0f, 1, 1f);
        ObjectAnimator anim112 = ObjectAnimator.ofFloat(view11, "scaleY", 1f, 0f, 1, 1f);
        ObjectAnimator anim211 = ObjectAnimator.ofFloat(view21, "scaleX", 1f, 0f, 1, 1f);
        ObjectAnimator anim212 = ObjectAnimator.ofFloat(view21, "scaleY", 1f, 0f, 1, 1f);
        ObjectAnimator anim221 = ObjectAnimator.ofFloat(view22, "scaleX", 1f, 0f, 1, 1f);
        ObjectAnimator anim222 = ObjectAnimator.ofFloat(view22, "scaleY", 1f, 0f, 1, 1f);
        ObjectAnimator anim311 = ObjectAnimator.ofFloat(view31, "scaleX", 1f, 0f, 1, 1f);
        ObjectAnimator anim312 = ObjectAnimator.ofFloat(view31, "scaleY", 1f, 0f, 1, 1f);
        ObjectAnimator anim321 = ObjectAnimator.ofFloat(view32, "scaleX", 1f, 0f, 1, 1f);
        ObjectAnimator anim322 = ObjectAnimator.ofFloat(view32, "scaleY", 1f, 0f, 1, 1f);
        ObjectAnimator anim331 = ObjectAnimator.ofFloat(view33, "scaleX", 1f, 0f, 1, 1f);
        ObjectAnimator anim332 = ObjectAnimator.ofFloat(view33, "scaleY", 1f, 0f, 1, 1f);
        ObjectAnimator anim411 = ObjectAnimator.ofFloat(view41, "scaleX", 1f, 0f, 1, 1f);
        ObjectAnimator anim412 = ObjectAnimator.ofFloat(view41, "scaleY", 1f, 0f, 1, 1f);
        ObjectAnimator anim421 = ObjectAnimator.ofFloat(view42, "scaleX", 1f, 0f, 1, 1f);
        ObjectAnimator anim422 = ObjectAnimator.ofFloat(view42, "scaleY", 1f, 0f, 1, 1f);
        ObjectAnimator anim511 = ObjectAnimator.ofFloat(view51, "scaleX", 1f, 0f, 1, 1f);
        ObjectAnimator anim512 = ObjectAnimator.ofFloat(view51, "scaleY", 1f, 0f, 1, 1f);


        int t = 150;

        anim111.setDuration(mDuration);
        anim112.setDuration(mDuration);
        anim211.setDuration(mDuration).setStartDelay(t);
        anim212.setDuration(mDuration).setStartDelay(t);
        anim221.setDuration(mDuration).setStartDelay(t);
        anim222.setDuration(mDuration).setStartDelay(t);
        anim311.setDuration(mDuration).setStartDelay(t * 2);
        anim312.setDuration(mDuration).setStartDelay(t * 2);
        anim321.setDuration(mDuration).setStartDelay(t * 2);
        anim322.setDuration(mDuration).setStartDelay(t * 2);
        anim331.setDuration(mDuration).setStartDelay(t * 2);
        anim332.setDuration(mDuration).setStartDelay(t * 2);
        anim411.setDuration(mDuration).setStartDelay(t * 3);
        anim412.setDuration(mDuration).setStartDelay(t * 3);
        anim421.setDuration(mDuration).setStartDelay(t * 3);
        anim422.setDuration(mDuration).setStartDelay(t * 3);
        anim511.setDuration(mDuration).setStartDelay(t * 4);
        anim512.setDuration(mDuration).setStartDelay(t * 4);


        AnimatorSet as1 = new AnimatorSet();
        AnimatorSet as2 = new AnimatorSet();
        AnimatorSet as3 = new AnimatorSet();
        AnimatorSet as4 = new AnimatorSet();
        AnimatorSet as5 = new AnimatorSet();

        as1.playTogether(anim111, anim112);
        as2.playTogether(anim211, anim212, anim221, anim222);
        as3.playTogether(anim311, anim312, anim321, anim322, anim331, anim332);
        as4.playTogether(anim411, anim412, anim421, anim422);
        as5.playTogether(anim511, anim512);


        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.playTogether(as1, as2, as3, as4, as5);
        animatorSet.start();
        animatorSet.addListener(new AnimatorEndListener() {
            @Override public void onAnimationEnd(Animator animation) {
                animatorSet.start();
            }
        });
    }
}

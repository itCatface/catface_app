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
import cc.catface.ctool.system.IInterface.ISystemInterface;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ThreeBounceView extends FrameLayout {
    private Context mCtx;
    private int mDuration = 900;


    @RequiresApi(api = Build.VERSION_CODES.M) public ThreeBounceView(Context context, AttributeSet attrs) {
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
        layoutParams.width = this.getWidth() / 4;
        layoutParams.height = this.getHeight() / 4;

        view1 = new View(mCtx);
        view2 = new View(mCtx);
        view3 = new View(mCtx);

        view1.setLayoutParams(layoutParams);
        view2.setLayoutParams(layoutParams);
        view3.setLayoutParams(layoutParams);


        view1.setTranslationY(getMeasuredHeight() / 3f);

        view2.setTranslationX(getMeasuredWidth() / 3f);
        view2.setTranslationY(getMeasuredHeight() / 3f);

        view3.setTranslationX(getMeasuredWidth() * 2 / 3f);
        view3.setTranslationY(getMeasuredHeight() / 3f);

        view1.setBackgroundResource(R.drawable.api_shape_circle);
        view2.setBackgroundResource(R.drawable.api_shape_circle);
        view3.setBackgroundResource(R.drawable.api_shape_circle);

        this.addView(view1);
        this.addView(view2);
        this.addView(view3);
    }


    private View view1, view2, view3;


    public void startAnim() {
        ObjectAnimator anim11 = ObjectAnimator.ofFloat(view1, "scaleX", 1f, 0f, 1, 1f);
        ObjectAnimator anim12 = ObjectAnimator.ofFloat(view1, "scaleY", 1f, 0f, 1, 1f);
        ObjectAnimator anim21 = ObjectAnimator.ofFloat(view2, "scaleX", 1f, 0f, 1, 1f);
        ObjectAnimator anim22 = ObjectAnimator.ofFloat(view2, "scaleY", 1f, 0f, 1, 1f);
        ObjectAnimator anim31 = ObjectAnimator.ofFloat(view3, "scaleX", 1f, 0f, 1, 1f);
        ObjectAnimator anim32 = ObjectAnimator.ofFloat(view3, "scaleY", 1f, 0f, 1, 1f);

        int t = 300;

        anim11.setDuration(mDuration);
        anim12.setDuration(mDuration);
        anim21.setDuration(mDuration).setStartDelay(t);
        anim22.setDuration(mDuration).setStartDelay(t);
        anim31.setDuration(mDuration).setStartDelay(t);
        anim32.setDuration(mDuration).setStartDelay(t);


        AnimatorSet as1 = new AnimatorSet();
        AnimatorSet as2 = new AnimatorSet();
        AnimatorSet as3 = new AnimatorSet();
        AnimatorSet as4 = new AnimatorSet();
        AnimatorSet as5 = new AnimatorSet();

        as1.playTogether(anim11, anim12);
        as2.playTogether(anim21, anim22, anim31, anim32);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.playTogether(as1, as2, as3, as4, as5);
        animatorSet.start();
        animatorSet.addListener(new ISystemInterface.AnimatorEndListener() {
            @Override public void onAnimationEnd(Animator animation) {
                animatorSet.start();
            }
        });
    }
}

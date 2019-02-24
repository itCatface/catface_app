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
 * @desc 左上和右下两个小方块无限互换位置
 */
public class WanderingCubesView extends FrameLayout {
    private Context mCtx;
    private int mDuration = 3_000;


    @RequiresApi(api = Build.VERSION_CODES.M) public WanderingCubesView(Context context, AttributeSet attrs) {
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
        layoutParams.width = this.getWidth() / 5;
        layoutParams.height = this.getHeight() / 5;

        view1 = new View(mCtx);
        view2 = new View(mCtx);

        view1.setLayoutParams(layoutParams);
        view2.setLayoutParams(layoutParams);

        view2.setTranslationX(getMeasuredWidth() * 0.8f);
        view2.setTranslationY(getMeasuredHeight() * 0.8f);

        view1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        view2.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        this.addView(view1);
        this.addView(view2);
    }


    private View view1, view2;


    public void startAnim() {
        ObjectAnimator animator11 = ObjectAnimator.ofFloat(view1, "translationX", 0f, getMeasuredWidth() * 0.8f);
        ObjectAnimator animator12 = ObjectAnimator.ofFloat(view1, "translationY", 0f, getMeasuredHeight() * 0.8f);
        ObjectAnimator animator13 = ObjectAnimator.ofFloat(view1, "translationX", getMeasuredWidth() * 0.8f, 0f);
        ObjectAnimator animator14 = ObjectAnimator.ofFloat(view1, "translationY", getMeasuredHeight() * 0.8f, 0f);

        ObjectAnimator animator31 = ObjectAnimator.ofFloat(view1, "rotation", 0f, 90f);

        ObjectAnimator animator51 = ObjectAnimator.ofFloat(view1, "scaleX", 1f, 0.5f);
        ObjectAnimator animator52 = ObjectAnimator.ofFloat(view1, "scaleX", 0.5f, 1f);

        ObjectAnimator animator71 = ObjectAnimator.ofFloat(view1, "scaleY", 1f, 0.5f);
        ObjectAnimator animator72 = ObjectAnimator.ofFloat(view1, "scaleY", 0.5f, 1f);


        ObjectAnimator animator21 = ObjectAnimator.ofFloat(view2, "translationX", getMeasuredWidth() * 0.8f, 0f);
        ObjectAnimator animator22 = ObjectAnimator.ofFloat(view2, "translationY", getMeasuredHeight() * 0.8f, 0f);
        ObjectAnimator animator23 = ObjectAnimator.ofFloat(view2, "translationX", 0f, getMeasuredHeight() * 0.8f);
        ObjectAnimator animator24 = ObjectAnimator.ofFloat(view2, "translationY", 0f, getMeasuredHeight() * 0.8f);

        ObjectAnimator animator41 = ObjectAnimator.ofFloat(view2, "rotation", 0f, 90f);

        ObjectAnimator animator61 = ObjectAnimator.ofFloat(view2, "scaleX", 1f, 0.5f);
        ObjectAnimator animator62 = ObjectAnimator.ofFloat(view2, "scaleX", 0.5f, 1f);

        ObjectAnimator animator81 = ObjectAnimator.ofFloat(view2, "scaleY", 1f, 0.5f);
        ObjectAnimator animator82 = ObjectAnimator.ofFloat(view2, "scaleY", 0.5f, 1f);

        animator11.setDuration(mDuration / 4);
        animator12.setDuration(mDuration / 4);
        animator13.setDuration(mDuration / 4);
        animator14.setDuration(mDuration / 4);
        animator21.setDuration(mDuration / 4);
        animator22.setDuration(mDuration / 4);
        animator23.setDuration(mDuration / 4);
        animator24.setDuration(mDuration / 4);
        animator31.setDuration(mDuration / 4);
        animator51.setDuration(mDuration / 4);
        animator52.setDuration(mDuration / 4);
        animator71.setDuration(mDuration / 4);
        animator72.setDuration(mDuration / 4);
        animator41.setDuration(mDuration / 4);
        animator61.setDuration(mDuration / 4);
        animator62.setDuration(mDuration / 4);
        animator81.setDuration(mDuration / 4);
        animator82.setDuration(mDuration / 4);


        AnimatorSet as1 = new AnimatorSet();
        AnimatorSet as2 = new AnimatorSet();
        AnimatorSet as3 = new AnimatorSet();
        AnimatorSet as4 = new AnimatorSet();

        as1.playTogether(animator11, animator21, animator31, animator41, animator51, animator61, animator71, animator81);
        as2.playTogether(animator12, animator22, animator31, animator41, animator52, animator62, animator72, animator82);
        as3.playTogether(animator13, animator23, animator31, animator41, animator51, animator61, animator71, animator81);
        as4.playTogether(animator14, animator24, animator31, animator41, animator52, animator62, animator72, animator82);


        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.playSequentially(as1, as2, as3, as4);
        animatorSet.start();
        animatorSet.addListener(new AnimatorEndListener() {
            @Override public void onAnimationEnd(Animator animation) {
                animatorSet.start();
            }
        });
    }
}

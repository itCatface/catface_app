package cc.catface.api.view.loading.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cc.catface.base.utils.android.common_print.log.TLog;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class WanderingCubesViewGroup extends RelativeLayout {
    private Context mCtx;
    private int mDuration = 1_000;

    public WanderingCubesViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        //        init();
    }

    private void init() {
        view1 = new View(mCtx);
        view2 = new View(mCtx);

        this.addView(view1);
        this.addView(view2);


        view1.setBackgroundColor(Color.RED);
        view2.setBackgroundColor(Color.RED);


        view1Params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view2Params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        view1Params.width = this.getWidth() / 5;
        view1Params.height = this.getHeight() / 5;

        TLog.d("--" + (this.getWidth() / 5) + " ||" + (this.getHeight() / 5));


        view2Params.width = this.getWidth() / 5;
        view2Params.height = this.getHeight() / 5;

        view1.setLayoutParams(view1Params);
        view2.setLayoutParams(view2Params);


    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
    }

    private View view1, view2;
    private RelativeLayout.LayoutParams view1Params, view2Params;


    public void startAnim() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view1, "translationX", this.getLeft(), this.getRight() / 2);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view1, "translationY", this.getTop(), this.getHeight() / 2);

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(view2, "translationX", this.getRight() / 2, this.getLeft());
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(view2, "translationY", this.getHeight() / 2, this.getTop());

        animator1.setDuration(mDuration / 2);
        animator2.setDuration(mDuration / 2);
        animator3.setDuration(mDuration / 2);
        animator4.setDuration(mDuration / 2);

        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(animator1);
//        builder.with(animator3).before(animator2).with(animator4);
        builder.before(animator2);
        animatorSet.start();

    }
}

package cc.catface.api.view.demo04_object;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityObjectBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

public class DemoObjectFm extends LightFm<LightPresenter, ApiActivityObjectBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_object;
    }

    @Override protected void initView() {
        mBinding.btAlpha.setOnClickListener(view -> alpha());
        mBinding.btTranslate.setOnClickListener(view -> translate());
        mBinding.btScale.setOnClickListener(view -> scale());
        mBinding.btRotate.setOnClickListener(view -> rotate());
        mBinding.btSet.setOnClickListener(view -> set());
        mBinding.btArgbEva.setOnClickListener(view -> argbEva());
    }


    /** 基本动画：当且仅当动画的只有一个过渡值时，系统才会调用对应属性的get函数来得到动画的初始值 */
    private void alpha() {
        ObjectAnimator.ofFloat(mBinding.tvTitle, "alpha", 0f, 1f, 0.5f, 1f).setDuration(2_000).start();
    }

    private void translate() {
        ObjectAnimator.ofFloat(mBinding.tvTitle, "translationX", 0f, 200f, -150f, 50f, 0f).setDuration(2_000).start();   // translationY
    }

    private void scale() {
        ObjectAnimator.ofFloat(mBinding.tvTitle, "scaleX", 0f, 2f, 0.5f, 1f).setDuration(2_000).start(); // scaleY
    }

    private void rotate() {
        ObjectAnimator.ofFloat(mBinding.tvTitle, "rotationY", 0, 180, 0, 180, 0).setDuration(3_000).start();  // rotationX |
    }

    private void set() {
        ObjectAnimator.ofInt(mBinding.pv, "pointRadius", mBinding.pv.getMeasuredWidth() / 2 - 20).setDuration(2_000).start();
    }

    private void argbEva() {
        ObjectAnimator animatorTvBg = ObjectAnimator.ofInt(mBinding.tvTitle, "backgroundColor", 0xffff00ff, 0xff0000ff, 0xff00ff00);
        animatorTvBg.setEvaluator(new ArgbEvaluator());
        animatorTvBg.setDuration(2_000).start();

        ObjectAnimator animatorTvColor = ObjectAnimator.ofInt(mBinding.tvTitle, "textColor", 0xffffffff, 0xff0000ff, 0xff000000);
        animatorTvColor.setEvaluator(new ArgbEvaluator());
        animatorTvColor.setDuration(2_000).start();
    }
}

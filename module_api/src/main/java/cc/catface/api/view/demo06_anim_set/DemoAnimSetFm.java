package cc.catface.api.view.demo06_anim_set;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityAnimSetBinding;
import cc.catface.base.core_framework.base_normal.NormalFragment;

public class DemoAnimSetFm extends NormalFragment<ApiActivityAnimSetBinding> {


    @Override public int layoutId() {
        return R.layout.api_activity_anim_set;
    }

    @Override public void createView() {
        mBinding.btAnimator1.setOnClickListener(view->animator1());
    }


    private void animator1() {
        ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(mActivity, R.animator.api_animator_anim_set);
        animator.addUpdateListener(valueAnimator -> {
            int value = (int) valueAnimator.getAnimatedValue();
            mBinding.tvTitle.layout(value, value, mBinding.tvTitle.getWidth() + value, mBinding.tvTitle.getHeight() + value);
        });
        animator.start();
    }
}

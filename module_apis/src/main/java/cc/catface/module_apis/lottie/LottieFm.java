package cc.catface.module_apis.lottie;

import android.animation.Animator;

import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisFragmentLottieBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class LottieFm extends NormalFragment<ApisFragmentLottieBinding> {
    @Override public int layoutId() {
        return R.layout.apis_fragment_lottie;
    }

    @Override protected void createView() {
        mBinding.lav.addAnimatorUpdateListener(animation -> {

        });
        mBinding.lav.addAnimatorListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animation) {

            }

            @Override public void onAnimationEnd(Animator animation) {

            }

            @Override public void onAnimationCancel(Animator animation) {

            }

            @Override public void onAnimationRepeat(Animator animation) {

            }
        });
        mBinding.lav.playAnimation();
    }
}

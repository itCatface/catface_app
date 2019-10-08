package cc.catface.module_apis.lottie;

import android.animation.Animator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

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
        initToolBar();
        mBinding.lav5.addAnimatorUpdateListener(animation -> {

        });
        mBinding.lav5.addAnimatorListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animation) {

            }

            @Override public void onAnimationEnd(Animator animation) {

            }

            @Override public void onAnimationCancel(Animator animation) {

            }

            @Override public void onAnimationRepeat(Animator animation) {

            }
        });
        mBinding.lav5.playAnimation();
    }

    /** tool bar */
    private void initToolBar() {
        Toolbar toolbar = mBinding.iTbLottie.tbTitle;
        mActivity.setSupportActionBar(toolbar);
        ActionBar bar = mActivity.getSupportActionBar();
        if (null != bar) {
            bar.setDisplayShowHomeEnabled(true);
            bar.setTitle("lottie示例");
        }
        toolbar.setNavigationIcon(R.mipmap.flaticon_back);
        toolbar.setNavigationOnClickListener(v -> mActivity.finish());
    }
}

package cc.catface.module_start.splash.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.alibaba.android.arouter.launcher.ARouter;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpActivity;
import cc.catface.ctool.context.TAppInfo;
import cc.catface.ctool.system.IInterface.ISystemInterface;
import cc.catface.module_start.R;
import cc.catface.module_start.databinding.ActivityStartSplashBinding;
import cc.catface.module_start.splash.presenter.SplashPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(SplashPresenterImp.class) public class SplashActivity extends MvpActivity<SplashView, SplashPresenterImp, ActivityStartSplashBinding> implements SplashView {
    @Override public int layoutId() {
        return R.layout.activity_start_splash;
    }

    @SuppressLint("SetTextI18n") @Override public void create() {
        mBinding.tvVerName.setText("v." + TAppInfo.getVerName());
        startAnim();
    }

    private void startAnim() {
        // 文字平移
        ObjectAnimator animTvTranslationY = ObjectAnimator.ofFloat(mBinding.tvSplash, "translationY", -50f, 0f);
        animTvTranslationY.setDuration(400);
        animTvTranslationY.setInterpolator(new DecelerateInterpolator());

        // 文字缩放
        ObjectAnimator animTvScaleX = ObjectAnimator.ofFloat(mBinding.tvSplash, "scaleX", 2.0f, 1f);
        animTvScaleX.setDuration(600);

        // logo渐变
        ObjectAnimator animIvAlpha = ObjectAnimator.ofFloat(mBinding.ivSplash, "alpha", 0f, 1f);
        animIvAlpha.setDuration(600);
        animIvAlpha.setInterpolator(new BounceInterpolator());
        animIvAlpha.addListener((ISystemInterface.AnimatorEndListener) animator -> {
            ARouter.getInstance().build(Const.ARouter.start_main).navigation();
            finish();
        });

        // 控制动画播放
        AnimatorSet animSet = new AnimatorSet();
        AnimatorSet.Builder builder = animSet.play(animTvTranslationY);
        builder.before(animIvAlpha).with(animTvScaleX);
        animSet.start();
    }
}

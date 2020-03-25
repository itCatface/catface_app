package cc.catface.start.splash.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.alibaba.android.arouter.launcher.ARouter;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.ctool.context.TAppInfo;
import cc.catface.ctool.system.IInterface.ISystemInterface;
import cc.catface.start.R;
import cc.catface.start.databinding.ActivityStartSplashBinding;
import cc.catface.start.splash.vp.SplashVP;
import cc.catface.start.splash.vp.SplashPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SplashActivity extends LightAct<SplashPresenterImp, ActivityStartSplashBinding> implements SplashVP.SplashView {
    @Override public int layoutId() {
        return R.layout.activity_start_splash;
    }

    @SuppressLint("SetTextI18n") @Override protected void initView() {
        mBinding.tvVerName.setText("v." + TAppInfo.getVerName());
        startAnim();
    }

    @Override protected void initPresenter() {
        mPresenter = new SplashPresenterImp(this, this);
        mPresenter.initCrashHandler();
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

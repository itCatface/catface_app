package cc.catface.app.module.start.splash.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Environment;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;

import butterknife.BindView;
import cc.catface.app.R;
import cc.catface.app.module.start.splash.presenter.SplashPresenterImp;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsAppCompatActivity;
import cc.catface.base.utils.android.TAppInfo;
import cc.catface.base.utils.android.listener.AnimatorEndListener;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(SplashPresenterImp.class)
public class SplashActivity extends AbsAppCompatActivity<SplashView, SplashPresenterImp> implements SplashView {

    @Override public int layoutId() {
        return R.layout.activity_start_splash;
    }

    @BindView(R.id.iv_splash) ImageView iv_splash;
    @BindView(R.id.tv_splash) TextView tv_splash;

    @SuppressLint("SetTextI18n") @Override public void create() {
        ((TextView) findViewById(R.id.tv_verName)).setText("v." + TAppInfo.getVerName(this));
        Beta.checkUpgrade();
        startAnim();
    }

    private void startAnim() {
        // 文字平移
        ObjectAnimator animTvTranslationY = ObjectAnimator.ofFloat(tv_splash, "translationY", -50f, 0f);
        animTvTranslationY.setDuration(600);
        animTvTranslationY.setInterpolator(new DecelerateInterpolator());

        // 文字缩放
        ObjectAnimator animTvScaleX = ObjectAnimator.ofFloat(tv_splash, "scaleX", 2f, 1f);
        animTvScaleX.setDuration(1_000);

        // logo渐变
        ObjectAnimator animIvAlpha = ObjectAnimator.ofFloat(iv_splash, "alpha", 0f, 1f);
        animIvAlpha.setDuration(800);
        animIvAlpha.setInterpolator(new BounceInterpolator());
        animIvAlpha.addListener(new AnimatorEndListener() {
            @Override public void onAnimationEnd(Animator animator) {
                ARouter.getInstance().build("/start/main").navigation();
                finish();
            }
        });

        // 控制动画播放
        AnimatorSet animSet = new AnimatorSet();
        AnimatorSet.Builder builder = animSet.play(animTvTranslationY);
        builder.before(animIvAlpha).with(animTvScaleX);
        animSet.start();
    }
}

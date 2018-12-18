package cc.catface.app.module.start.splash.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import cc.catface.app.R;
import cc.catface.app.module.start.main.view.MainActivity;
import cc.catface.app.module.start.splash.presenter.SplashPresenterImp;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsAppCompatActivity;
import cc.catface.base.utils.android.common_intent.TIntent;
import cc.catface.base.utils.android.listener.AnimatorEndListener;

@CreatePresenter(SplashPresenterImp.class)
public class SplashActivity extends AbsAppCompatActivity<SplashView, SplashPresenterImp> implements SplashView {

    @Override public int layoutId() {
        return R.layout.activity_start_splash;
    }

    @BindView(R.id.iv_splash) ImageView iv_splash;
    @BindView(R.id.tv_splash) TextView tv_splash;

    @Override public void create() {
        startAnim();
    }

    private void startAnim() {
        // 文字平移
        ObjectAnimator animTvTranslationY = ObjectAnimator.ofFloat(tv_splash, "translationY", -50f, 0f);
        animTvTranslationY.setDuration(1_000);
        animTvTranslationY.setInterpolator(new DecelerateInterpolator());

        // 文字缩放
        ObjectAnimator animTvScaleX = ObjectAnimator.ofFloat(tv_splash, "scaleX", 2f, 1f);
        animTvScaleX.setDuration(3_000);

        // logo渐变
        ObjectAnimator animIvAlpha = ObjectAnimator.ofFloat(iv_splash, "api_alpha", 0f, 1f);
        animIvAlpha.setDuration(1_000);
        animIvAlpha.setInterpolator(new BounceInterpolator());
        animIvAlpha.addListener(new AnimatorEndListener() {
            @Override public void onAnimationEnd(Animator animator) {
                TIntent.startActivityAndFinish(SplashActivity.this, MainActivity.class, true);
            }
        });

        // 控制动画播放
        AnimatorSet animSet = new AnimatorSet();
        AnimatorSet.Builder builder = animSet.play(animTvTranslationY);
        builder.before(animIvAlpha).with(animTvScaleX);
        animSet.start();
    }
}

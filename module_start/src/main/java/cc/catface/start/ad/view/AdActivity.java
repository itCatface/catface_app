package cc.catface.start.ad.view;

import android.annotation.SuppressLint;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.ctool.system.Timer.CountDownTimer;
import cc.catface.ctool.view.activity.TActivity;
import cc.catface.start.R;
import cc.catface.start.ad.vp.AdPresenterImp;
import cc.catface.start.ad.vp.AdVP;
import cc.catface.start.databinding.StartActivityAdBinding;
import cc.catface.start.main.mvp.view.MainActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class AdActivity extends LightAct<AdPresenterImp, StartActivityAdBinding> implements AdVP.AdView {

    @Override
    public void onClick(View view) {
        if (R.id.tv_share == view.getId()) {
            showShare();
        } else if (R.id.tv_jump == view.getId()) {
            TActivity.startActivityFinish(AdActivity.this, MainActivity.class);
        }
    }

    @Override
    public int layoutId() {
        return R.layout.start_activity_ad;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new AdPresenterImp(this, this);
        mPresenter.initCrashHandler();
    }

    @Override
    protected void initAction() {
        mBinding.tvShare.setOnClickListener(this);
        mBinding.tvJump.setOnClickListener(this);
    }

    private CountDownTimer mCountDownTimer;

    @Override
    public void created() {
        /* 广告图片展示 */
        Glide.with(this).asBitmap().load(Const.TestNetImageUrls.getRandomOneUrl()).transition(new BitmapTransitionOptions().crossFade(500)).into(mBinding.ivAd);

        /* 跳过广告按钮 */
        mCountDownTimer = new CountDownTimer(5000, 1_000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                mBinding.tvJump.setText("跳过(" + millisUntilFinished / 1_000 + "s)");
            }

            @Override
            public void onFinish() {
                TActivity.startActivityFinish(AdActivity.this, MainActivity.class);
            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mCountDownTimer) mCountDownTimer.cancel();
    }

    private void showShare() {

    }
}

package cc.catface.api.view.demo01_astrs;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityAstrsBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoASTRSFm extends LightFm<LightPresenter, ApiActivityAstrsBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_astrs;
    }

    @Override protected void initAction() {
        mBinding.btAlpha.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.api_alpha);
            mBinding.bt.startAnimation(animation);
        });

        mBinding.btScale.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.api_scale);
            mBinding.bt.startAnimation(animation);
        });

        mBinding.btTranslate.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.api_translate);
            mBinding.bt.startAnimation(animation);
        });

        mBinding.btRotate.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.api_rotate);
            mBinding.bt.startAnimation(animation);
        });
        mBinding.btSet.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.api_set);
            mBinding.bt.startAnimation(animation);
        });
    }
}

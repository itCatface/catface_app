package cc.catface.module_apis.brvah.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;

import androidx.recyclerview.widget.LinearLayoutManager;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.AnimationAdapter;
import cc.catface.module_apis.brvah.domain.Status;
import cc.catface.module_apis.databinding.BrvahFmAnimationBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_AnimationFm extends NormalFragment<BrvahFmAnimationBinding> implements View.OnClickListener {
    @Override public int layoutId() {
        return R.layout.brvah_fm_animation;
    }

    private AnimationAdapter mAnimationAdapter;

    @Override public void onClick(View view) {
        if (R.id.cb == view.getId()) {
            mAnimationAdapter.isFirstOnly(((CheckBox) view).isChecked());
            mAnimationAdapter.notifyDataSetChanged();
        } else {
            if (R.id.bt_alphaIn == view.getId()) {
                mBinding.tvCurrentAnimation.setText("当前：渐变");
                mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            } else if (R.id.bt_scaleIn == view.getId()) {
                mBinding.tvCurrentAnimation.setText("当前：缩放");
                mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            } else if (R.id.bt_custom == view.getId()) {
                mBinding.tvCurrentAnimation.setText("当前：自定义");
                mAnimationAdapter.openLoadAnimation(new CustomAnimation());
            } else if (R.id.bt_slideInBottom == view.getId()) {
                mBinding.tvCurrentAnimation.setText("当前：底部载入");
                mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
            } else if (R.id.bt_slideInLeft == view.getId()) {
                mBinding.tvCurrentAnimation.setText("当前：左侧载入");
                mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
            } else if (R.id.bt_slideInRight == view.getId()) {
                mBinding.tvCurrentAnimation.setText("当前：右侧载入");
                mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
            }
            mBinding.rvAnimation.setAdapter(mAnimationAdapter);
        }
    }

    @Override public void createView() {
        mBinding.cb.setOnClickListener(this);
        mBinding.btAlphaIn.setOnClickListener(this);
        mBinding.btScaleIn.setOnClickListener(this);
        mBinding.btSlideInBottom.setOnClickListener(this);
        mBinding.btSlideInLeft.setOnClickListener(this);
        mBinding.btSlideInRight.setOnClickListener(this);
        mBinding.btCustom.setOnClickListener(this);

        initView();
        initAdapter();
    }

    private void initView() {
        mBinding.rvAnimation.setHasFixedSize(true);
        mBinding.rvAnimation.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    private void initAdapter() {
        mAnimationAdapter = new AnimationAdapter();
        mAnimationAdapter.openLoadAnimation();
        mAnimationAdapter.setNotDoAnimationCount(3);
        mAnimationAdapter.isFirstOnly(false);
        mAnimationAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            String content;
            Status status = (Status) adapter.getItem(position);
            if (R.id.iv_icon == view.getId()) {
                content = "img:" + status.getUserAvatar();
                Toast.makeText(mActivity, content, Toast.LENGTH_SHORT).show();
            } else if (R.id.tv_title == view.getId()) {
                content = "name:" + status.getUserName();
                Toast.makeText(mActivity, content, Toast.LENGTH_SHORT).show();
            } else if (R.id.tv_content == view.getId()) {
                content = "tweetText:" + status.getUserName();
                Toast.makeText(mActivity, content, Toast.LENGTH_SHORT).show();
            }
        });
        mBinding.rvAnimation.setAdapter(mAnimationAdapter);
    }


    class CustomAnimation implements BaseAnimation {

        @Override public Animator[] getAnimators(View view) {
            return new Animator[]{ObjectAnimator.ofFloat(view, "scaleY", 1, 1.1f, 1), ObjectAnimator.ofFloat(view, "scaleX", 1, 1.1f, 1)};
        }
    }
}


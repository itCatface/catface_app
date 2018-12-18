package cc.catface.module_apis.brvah.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;

import cc.catface.base.core_framework.base_normal.NormalBaseFragmentID;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.AnimationAdapter;
import cc.catface.module_apis.brvah.domain.Status;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_AnimationFm extends NormalBaseFragmentID implements View.OnClickListener {
    @Override public int layoutId() {
        return R.layout.brvah_fm_animation;
    }

    private TextView tv_current_animation;
    private RecyclerView rv_animation;
    private AnimationAdapter mAnimationAdapter;

    @Override public void ids(View v) {
        tv_current_animation = (TextView) v.findViewById(R.id.tv_current_animation);
        rv_animation = (RecyclerView) v.findViewById(R.id.rv_animation);
        v.findViewById(R.id.cb).setOnClickListener(this);
        v.findViewById(R.id.bt_alphaIn).setOnClickListener(this);
        v.findViewById(R.id.bt_scaleIn).setOnClickListener(this);
        v.findViewById(R.id.bt_slideInBottom).setOnClickListener(this);
        v.findViewById(R.id.bt_slideInLeft).setOnClickListener(this);
        v.findViewById(R.id.bt_slideInRight).setOnClickListener(this);
        v.findViewById(R.id.bt_custom).setOnClickListener(this);
    }

    @Override public void onClick(View view) {
        if (R.id.cb == view.getId()) {
            mAnimationAdapter.isFirstOnly(((CheckBox) view).isChecked());
            mAnimationAdapter.notifyDataSetChanged();
        } else {
            if (R.id.bt_alphaIn == view.getId()) {
                tv_current_animation.setText("当前：渐变");
                mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            } else if (R.id.bt_scaleIn == view.getId()) {
                tv_current_animation.setText("当前：缩放");
                mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            } else if (R.id.bt_custom == view.getId()) {
                tv_current_animation.setText("当前：自定义");
                mAnimationAdapter.openLoadAnimation(new CustomAnimation());
            } else if (R.id.bt_slideInBottom == view.getId()) {
                tv_current_animation.setText("当前：底部载入");
                mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
            } else if (R.id.bt_slideInLeft == view.getId()) {
                tv_current_animation.setText("当前：左侧载入");
                mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
            } else if (R.id.bt_slideInRight == view.getId()) {
                tv_current_animation.setText("当前：右侧载入");
                mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
            }
            rv_animation.setAdapter(mAnimationAdapter);
        }
    }

    @Override public void createView() {
        initView();
        initAdapter();
    }

    private void initView() {
        rv_animation.setHasFixedSize(true);
        rv_animation.setLayoutManager(new LinearLayoutManager(mActivity));
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
        rv_animation.setAdapter(mAnimationAdapter);
    }


    class CustomAnimation implements BaseAnimation {

        @Override public Animator[] getAnimators(View view) {
            return new Animator[]{ObjectAnimator.ofFloat(view, "scaleY", 1, 1.1f, 1), ObjectAnimator.ofFloat(view, "scaleX", 1, 1.1f, 1)};
        }
    }
}


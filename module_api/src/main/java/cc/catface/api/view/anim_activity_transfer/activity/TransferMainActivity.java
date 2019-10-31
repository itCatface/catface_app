package cc.catface.api.view.anim_activity_transfer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiAnimActivityTransferMainBinding;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.base.utils.android.common_print.toast.TToast;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TransferMainActivity extends LightAct<LightPresenter, ApiAnimActivityTransferMainBinding> {

    public static final String TRANS_KEY = "direction";
    public static final String TRANS_VALUE_TOP = "top";
    public static final String TRANS_VALUE_BOTTOM = "bottom";
    public static final String TRANS_VALUE_LEFT = "left";
    public static final String TRANS_VALUE_RIGHT = "right";

    public static Activity mActivity;


    @Override public int layoutId() {
        return R.layout.api_anim_activity_transfer_main;
    }

    @Override protected void initAction() {
        /* 控制ConstraintLayout的显隐 */
        mBinding.btCtrl1.setOnClickListener(v -> {
            mBinding.cl1.setVisibility(mBinding.cl1.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        });

        mBinding.btCtrl2.setOnClickListener(v -> {
            mBinding.i.cl21.setVisibility(mBinding.i.cl21.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        });

        mBinding.btRightIn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AnimTransferRightActivity.class);
            intent.putExtra(TRANS_KEY, TRANS_VALUE_RIGHT);
            startActivity(intent);
        });
        mBinding.btLeftIn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AnimTransferLeftActivity.class);
            intent.putExtra(TRANS_KEY, TRANS_VALUE_LEFT);
            startActivity(intent);
        });
        mBinding.btTopIn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AnimTransferTopActivity.class);
            intent.putExtra(TRANS_KEY, TRANS_VALUE_TOP);
            startActivity(intent);
        });
        mBinding.btBottomIn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AnimTransferBottomActivity.class);
            intent.putExtra(TRANS_KEY, TRANS_VALUE_BOTTOM);
            startActivity(intent);
        });
    }


    @Override protected void created() {
        mActivity = this;
    }


    /** 手势 */
    float mDownX, mDownY, mDownTime;
    float mMoveX, mMoveY, mDurationTime, mDistanceLimit = 170, mDurationTimeLimit = 16;

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mDownTime = SystemClock.currentThreadTimeMillis();
            mDownX = event.getX();
            mDownY = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            mDurationTime = SystemClock.currentThreadTimeMillis() - mDownTime;
            mMoveX = event.getX() - mDownX;
            mMoveY = event.getY() - mDownY;

            TLog.d("root", "touch-->" + mMoveX + " || " + mMoveY + " || " + mDownTime + " || " + mDurationTime);

            if (mDurationTime > mDurationTimeLimit) {
                return super.onTouchEvent(event);
            }

            if (mMoveX > mDistanceLimit) {
                TToast.get(this).showBShortView("往右滑了" + mDurationTime, TToast.B_SUCCESS);
            } else if (mMoveX < -mDistanceLimit) {
                TToast.get(this).showBShortView("往左滑了" + mDurationTime, TToast.B_SUCCESS);
            } else if (mMoveY > mDistanceLimit) {
                TToast.get(this).showBShortView("往下划了" + mDurationTime, TToast.B_SUCCESS);
            } else if (mMoveY < -mDistanceLimit) {
                TToast.get(this).showBShortView("往上滑了" + mDurationTime, TToast.B_SUCCESS);
            }
        }
        return super.onTouchEvent(event);
    }
}

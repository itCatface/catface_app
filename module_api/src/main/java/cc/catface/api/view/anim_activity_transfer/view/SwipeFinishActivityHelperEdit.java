package cc.catface.api.view.anim_activity_transfer.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class SwipeFinishActivityHelperEdit {
    public static final int mode_left = 0x01;
    public static final int mode_right = 0x02;
    public static final int mode_top = 0x03;
    public static final int mode_bottom = 0x04;
    private int mMode = mode_right;
    private boolean mModeIsHorizontal = true;

    public SwipeFinishActivityHelperEdit(Activity activity) {
        new SwipeView(activity).replaceContentView();
    }

    public SwipeFinishActivityHelperEdit(Activity activity, int mode) {
        mMode = mode;
        mModeIsHorizontal = mMode == mode_left || mMode == mode_right;
        new SwipeView(activity).replaceContentView();
    }

    private Activity mBottomActivity;
    private View mBottomView;
    public SwipeFinishActivityHelperEdit(Activity activity, int mode, Activity showActivity) {
        mBottomActivity = showActivity;
        mMode = mode;
        mModeIsHorizontal = mMode == mode_left || mMode == mode_right;
        new SwipeView(activity).replaceContentView();
    }


    private class SwipeView extends FrameLayout {
        private Activity mActivity;
        private ViewGroup mDecorView;
        private View mContentView;
        private float mDown_X = 0;
        private float mDown_Y = 0;
        private int mTouchSlop = 0;

        public SwipeView(Context context) {
            this(context, null);
        }

        public SwipeView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public SwipeView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            this.mActivity = (Activity) context;

            mTouchSlop = ViewConfiguration.get(mActivity).getScaledTouchSlop();
            setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }


        /** [DecorView[ContentView]] --> [DecorView[SwipeView(FrameLayout)[ContentView]]] */
        private void replaceContentView() {
            mDecorView = (ViewGroup) mActivity.getWindow().getDecorView();
            mContentView = (ViewGroup) mDecorView.findViewById(android.R.id.content);
            ViewGroup contentParent = (ViewGroup) mContentView.getParent();
            contentParent.removeView(mContentView);
            addView(mContentView);
            contentParent.addView(this);

            mBottomView = (ViewGroup) mBottomActivity.getWindow().getDecorView().findViewById(android.R.id.content);
            mBottomView.setTranslationY(mContentView.getMeasuredHeight());
        }


        @Override public boolean onInterceptTouchEvent(MotionEvent event) {
            boolean shouldInterceptEvent = false;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDown_Y = event.getY();
                    mDown_X = event.getX();
                    break;

                case MotionEvent.ACTION_MOVE:
                    float offsetY = Math.abs(event.getY() - mDown_Y);
                    float offsetX = Math.abs(event.getX() - mDown_X);

                    if (mMode == mode_left) {
                        shouldInterceptEvent = offsetY < mTouchSlop * 3 && offsetY <= offsetX && event.getX() - mDown_X >= mTouchSlop * 3;
                    } else if (mMode == mode_right) {
                        shouldInterceptEvent = offsetY < mTouchSlop * 3 && offsetY <= offsetX && event.getX() - mDown_X >= mTouchSlop * 3;
                    } else if (mMode == mode_top) {
                        shouldInterceptEvent = offsetX < mTouchSlop * 3 && offsetX <= offsetY && event.getY() - mDown_Y >= mTouchSlop * 3;
                    } else if (mMode == mode_bottom) {
                        shouldInterceptEvent = offsetX < mTouchSlop * 3 && offsetX <= offsetY && event.getY() - mDown_Y >= mTouchSlop * 3;
                    }
                    break;
            }
            return shouldInterceptEvent;
        }

        @SuppressLint("ClickableViewAccessibility") public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    float offsetX = event.getX() - mDown_X;
                    float offsetY = event.getY() - mDown_Y;


                    mBottomView.setTranslationX(offsetY);

                    if (mMode == mode_left && offsetX < 0)
                        mContentView.setTranslationX(offsetX);
                    else if (mMode == mode_right && offsetX > 0)
                        mContentView.setTranslationX(offsetX);
                    else if (mMode == mode_top && offsetY < 0)
                        mContentView.setTranslationY(offsetY);
                    else if (mMode == mode_bottom && offsetY > 0)
                        mContentView.setTranslationY(offsetY);
                    break;

                case MotionEvent.ACTION_UP:
                    /* 横向滑动则判断横向滑动距离是否大于屏幕宽度的1/3 */
                    int distanceRefer = mModeIsHorizontal ? mContentView.getMeasuredWidth() : mContentView.getMeasuredHeight();
                    float distance = mModeIsHorizontal ? mContentView.getTranslationX() : mContentView.getTranslationY();
                    if (Math.abs(distance) >= distanceRefer / 4) {
                        /* 折叠当前Activity的动画 */
                        collapse();
                    } else {
                        reappear();
                    }
                    break;
            }
            return true;
        }


        private void reappear() {
            Property<View, Float> property = mModeIsHorizontal ? View.TRANSLATION_X : View.TRANSLATION_Y;

            mContentView.clearAnimation();
            ObjectAnimator.ofFloat(mContentView, property, 0).start();
        }

        private void collapse() {
            Property<View, Float> property = mModeIsHorizontal ? View.TRANSLATION_X : View.TRANSLATION_Y;
            float value = 0;
            if (mMode == mode_left) {
                value = -mContentView.getMeasuredWidth();
            } else if (mMode == mode_right) {
                value = mContentView.getMeasuredWidth();
            } else if (mMode == mode_top) {
                value = -mContentView.getMeasuredHeight();
            } else if (mMode == mode_bottom) {
                value = mContentView.getMeasuredHeight();
            }

            mContentView.clearAnimation();
            ObjectAnimator anim = ObjectAnimator.ofFloat(mContentView, property, value);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override public void onAnimationEnd(Animator animation) {
                    mActivity.finish();
                }
            });
            anim.start();
        }
    }
}
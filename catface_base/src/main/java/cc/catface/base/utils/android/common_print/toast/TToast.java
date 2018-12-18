package cc.catface.base.utils.android.common_print.toast;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

import cc.catface.base.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TToast implements TToastI {

    private static TToast mInstance;
    private Context mCtx;
    private Toast mToast;

    public static TToast get(Context ctx) {
        if (null == mInstance) {
            synchronized (TToast.class) {
                if (null == mInstance) {
                    mInstance = new TToast(ctx.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    private TToast(Context ctx) {
        mCtx = ctx;
    }


    /**/
    @Override public void showShortNormal(String content) {
        showNormal(content, Toast.LENGTH_SHORT);
    }

    @Override public void showLongNormal(String content) {
        showNormal(content, Toast.LENGTH_LONG);
    }

    private void showNormal(String content, int duration) {
        if (!MODE_DEBUG) return;
        Toast.makeText(mCtx, content, duration).show();
    }


    /**/
    @Override public void showShortContent(String content) {
        showContent(content, Toast.LENGTH_SHORT);
    }

    @Override public void showLongContent(String content) {
        showContent(content, Toast.LENGTH_LONG);
    }

    private void showContent(String content, int duration) {
        if (!MODE_DEBUG) return;
        if (null == mToast) {
            mToast = Toast.makeText(mCtx, content, duration);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }


    /**/
    @Override public void showShortImmediately(String content) {
        showImmediately(content, Toast.LENGTH_SHORT);
    }

    @Override public void showLongImmediately(String content) {
        showImmediately(content, Toast.LENGTH_LONG);
    }

    private void showImmediately(String content, int duration) {
        if (!MODE_DEBUG) return;
        clearToast();
        mToast = Toast.makeText(mCtx, content, duration);
        mToast.show();
    }


    /**/
    @Override public void showShortGravity(String content, int gravity) {
        showGravity(content, gravity, Toast.LENGTH_SHORT);
    }

    @Override public void showLongGravity(String content, int gravity) {
        showGravity(content, gravity, Toast.LENGTH_LONG);
    }

    private void showGravity(String content, int gravity, int duration) {
        if (!MODE_DEBUG) return;
        clearToast();
        mToast = Toast.makeText(mCtx, content, duration);
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }


    /**/
    @Override public void showShortLocationLeftBelow(String content, View view) {
        int[] locations = new int[2];
        view.getLocationInWindow(locations);
        showLocation(content, locations[0], locations[1] + view.getMeasuredHeight() / 2, Toast.LENGTH_SHORT);
    }

    @Override public void showLongLocationLeftBelow(String content, View view) {
        int[] locations = new int[2];
        view.getLocationInWindow(locations);
        showLocation(content, locations[0], locations[1] + view.getMeasuredHeight() / 2, Toast.LENGTH_LONG);
    }

    @Override public void showShortLocation(String content, int xOffSet, int yOffSet) {
        showLocation(content, xOffSet, yOffSet, Toast.LENGTH_SHORT);
    }

    @Override public void showLongLocation(String content, int xOffSet, int yOffSet) {
        showLocation(content, xOffSet, yOffSet, Toast.LENGTH_LONG);
    }

    private void showLocation(String content, int xOffSet, int yOffSet, int duration) {
        if (!MODE_DEBUG) return;
        clearToast();
        mToast = Toast.makeText(mCtx, content, duration);

        mToast.setGravity(Gravity.START | Gravity.TOP, xOffSet, yOffSet);
        mToast.show();
    }


    /**/
    public static final int B_NORMAL = 2;
    public static final int B_INFO = 3;
    public static final int B_SUCCESS = 1;
    public static final int B_CANCEL = -1;
    public static final int B_WARNING = 4;
    public static final int B_ERROR = 5;

    @Override public void showBShortView(String content, int level) {
        showBView(content, Toast.LENGTH_SHORT, level);
    }

    @Override public void showBLongView(String content, int level) {
        showBView(content, Toast.LENGTH_LONG, level);
    }

    private void showBView(String content, int duration, int level) {
        if (!MODE_DEBUG) return;
        clearToast();
        mToast = Toast.makeText(mCtx, content, duration);

        View view = LayoutInflater.from(mCtx).inflate(R.layout.toast_layout_default_view, null);
        ((TextView) view.findViewById(R.id.tv_content_toast)).setText(content);

        int bgColor = Color.parseColor(B_BG_COLOR_SUCCESS);
        int ivRes = B_ICON_ID_SUCCESS;
        switch (level) {
            case B_NORMAL:
                bgColor = Color.parseColor(B_BG_COLOR_NORMAL);
                ivRes = B_ICON_ID_NORMAL;
                break;
            case B_INFO:
                bgColor = Color.parseColor(B_BG_COLOR_INFO);
                ivRes = B_ICON_ID_INFO;
                break;
            case B_SUCCESS:
                bgColor = Color.parseColor(B_BG_COLOR_SUCCESS);
                ivRes = B_ICON_ID_SUCCESS;
                break;
            case B_CANCEL:
                bgColor = Color.parseColor(B_BG_COLOR_CANCEL);
                ivRes = B_ICON_ID_CANCEL;
                break;
            case B_WARNING:
                bgColor = Color.parseColor(B_BG_COLOR_WARNING);
                ivRes = B_ICON_ID_WARNING;
                break;
            case B_ERROR:
                bgColor = Color.parseColor(B_BG_COLOR_ERROR);
                ivRes = B_ICON_ID_ERROR;
                break;
        }
        ((LinearLayout) view.findViewById(R.id.ll_bg_toast)).setBackgroundColor(bgColor);
        ((ImageView) view.findViewById(R.id.iv_icon_toast)).setImageResource(ivRes);

        mToast.setView(view);
        mToast.show();
    }


    /**/
    @Override public void showShortAnim(String content, int animId) {
        showAnim(content, Toast.LENGTH_SHORT, animId);
    }

    @Override public void showLongAnim(String content, int animId) {
        showAnim(content, Toast.LENGTH_LONG, animId);
    }

    private void showAnim(String content, int duration, int animId) {
        this.mAnimId = animId;

        if (!MODE_DEBUG) return;
        clearToast();
        mToast = Toast.makeText(mCtx, content, duration);
        initTN();
        mToast.show();
    }

    private int mAnimId = -1;

    private void initTN() {
        try {
            Field tnField = mToast.getClass().getDeclaredField("mTN");
            tnField.setAccessible(true);
            Object mTN = tnField.get(mToast);

            if (mAnimId != -1) {
                Field tnParamsField = mTN.getClass().getDeclaredField("mParams");
                tnParamsField.setAccessible(true);
                WindowManager.LayoutParams params = (WindowManager.LayoutParams) tnParamsField.get(mTN);
                params.windowAnimations = mAnimId;
            }

            Field tnNextViewField = mTN.getClass().getDeclaredField("mNextView");
            tnNextViewField.setAccessible(true);
            tnNextViewField.set(mTN, mToast.getView());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**/
    @Override public void clearToast() {
        if (null != mToast) {
            mToast.cancel();
            mToast = null;
        }
    }
}

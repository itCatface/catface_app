package cc.catface.base.utils.android.common_title;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cc.catface.base.R;
import cc.catface.base.utils.android.common_print.log.TLog;

public class TitleBuilder {

    private View mView;
    private TextView tv_title_left;
    private ImageView iv_title_left;
    private TextView tv_title_center;
    private TextView tv_title_right;
    private ImageView iv_title_right;

    public TitleBuilder(Activity context) {
        mView = context.findViewById(R.id.rl_title);
        tv_title_left = (TextView) mView.findViewById(R.id.tv_title_left);
        iv_title_left = (ImageView) mView.findViewById(R.id.iv_title_left);
        tv_title_center = (TextView) mView.findViewById(R.id.tv_title_center);
        tv_title_right = (TextView) mView.findViewById(R.id.tv_title_right);
        iv_title_right = (ImageView) mView.findViewById(R.id.iv_title_right);
    }


    /** 标题 */
    public TitleBuilder setTVCenterText(String text) {
        tv_title_center.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        tv_title_center.setText(text);
        return this;
    }

    public TitleBuilder setTVCenterColor(int color) {
        tv_title_center.setBackgroundResource(color);
        return this;
    }


    /** 左侧图标/文字 */
    public TitleBuilder setIVLeftRes(int resId) {
        iv_title_left.setImageResource(resId);
        tv_title_left.setVisibility(View.GONE);
        iv_title_left.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        return this;
    }

    public TitleBuilder setTVLeftText(String text) {
        tv_title_left.setText(text);
        iv_title_left.setVisibility(View.GONE);
        tv_title_left.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        return this;
    }


    /** 右侧图标/文字 */
    public TitleBuilder setIVRightRes(int resId) {
        iv_title_right.setImageResource(resId);
        tv_title_right.setVisibility(View.GONE);
        iv_title_right.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        return this;
    }

    public TitleBuilder setTVRightText(String text) {
        tv_title_right.setText(text);
        iv_title_right.setVisibility(View.GONE);
        tv_title_right.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        return this;
    }


    /** 点击事件 */
    public TitleBuilder setListener(View.OnClickListener listener) {
        if (iv_title_left.getVisibility() == View.VISIBLE) iv_title_left.setOnClickListener(listener);
        if (tv_title_left.getVisibility() == View.VISIBLE) tv_title_left.setOnClickListener(listener);
        if (iv_title_right.getVisibility() == View.VISIBLE) iv_title_right.setOnClickListener(listener);
        if (tv_title_right.getVisibility() == View.VISIBLE) tv_title_right.setOnClickListener(listener);

        return this;
    }


    public View getRootView() {
        return mView;
    }
}
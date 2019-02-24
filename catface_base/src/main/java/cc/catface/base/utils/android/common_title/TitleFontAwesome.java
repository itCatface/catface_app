package cc.catface.base.utils.android.common_title;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cc.catface.base.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TitleFontAwesome extends RelativeLayout implements View.OnClickListener {

    private TextView ttvt;
    private TextView ttv1;
    private TextView ttv2;
    private TextView ttv3;
    private TextView ttv4;


    public TitleFontAwesome(Context context) {
        this(context, null);
    }

    public TitleFontAwesome(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleFontAwesome(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initClearText();
        initEvent();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.head_activity_default_top_title_font_awesome, null);
        ttvt = (TextView) view.findViewById(R.id.ttvt);
        ttv1 = (TextView) view.findViewById(R.id.ttv1);
        ttv2 = (TextView) view.findViewById(R.id.ttv2);
        ttv3 = (TextView) view.findViewById(R.id.ttv3);
        ttv4 = (TextView) view.findViewById(R.id.ttv4);

        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/fontawesome_webfont.ttf");
        ttv1.setTypeface(font);
        ttv2.setTypeface(font);
        ttv3.setTypeface(font);
        ttv4.setTypeface(font);

        this.addView(view);
    }

    private void initClearText() {
        ttvt.setText("");
        ttv1.setText("");
        ttv2.setText("");
        ttv3.setText("");
        ttv4.setText("");
    }

    public void initEvent() {
        ttvt.setOnClickListener(this);
        ttv1.setOnClickListener(this);
        ttv2.setOnClickListener(this);
        ttv3.setOnClickListener(this);
        ttv4.setOnClickListener(this);
    }

    public TitleFontAwesome setTitle(String text) {
        ttvt.setText(text);
        return this;
    }

    public TitleFontAwesome setIcon1(String text) {
        ttv1.setText(text);
        return this;
    }

    public TitleFontAwesome setIcon2(String text) {
        ttv2.setText(text);
        return this;
    }

    public TitleFontAwesome setIcon3(String text) {
        ttv3.setText(text);
        return this;
    }

    public TitleFontAwesome setIcon4(String text) {
        ttv4.setText(text);
        return this;
    }


    public TitleFontAwesome setTitle(int id) {
        ttvt.setText(id);
        return this;
    }

    public TitleFontAwesome setIcon1(int id) {
        ttv1.setText(id);
        return this;
    }

    public TitleFontAwesome setIcon2(int id) {
        ttv2.setText(id);
        return this;
    }

    public TitleFontAwesome setIcon3(int id) {
        ttv3.setText(id);
        return this;
    }

    public TitleFontAwesome setIcon4(int id) {
        ttv4.setText(id);
        return this;
    }


    /** Listener */
    public interface OnClickListener {
        void onClick(View view);
    }

    private OnClickListener mOnClickListener = null;

    public void setOnClickListener(OnClickListener listener) {
        mOnClickListener = listener;
    }

    @Override public void onClick(View view) {
        if (null != mOnClickListener) {
            if (view.getId() == R.id.ttv1) ((Activity) getContext()).finish();
            mOnClickListener.onClick(view);
        } else {
            if (view.getId() == R.id.ttv1) ((Activity) getContext()).finish();
        }
    }
}

package cc.catface.base.utils.android.common_title;

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

    private TextView tv_serial_1;
    private TextView tv_serial_2;
    private TextView tv_title;
    private TextView tv_serial_3;
    private TextView tv_serial_4;


    public TitleFontAwesome(Context context) {
        this(context, null);
    }

    public TitleFontAwesome(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleFontAwesome(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initEvent();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.head_activity_default_top_title_font_awesome, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_serial_1 = (TextView) view.findViewById(R.id.tv_serial_1);
        tv_serial_2 = (TextView) view.findViewById(R.id.tv_serial_2);
        tv_serial_3 = (TextView) view.findViewById(R.id.tv_serial_3);
        tv_serial_4 = (TextView) view.findViewById(R.id.tv_serial_4);

        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/fontawesome-webfont.ttf");
        tv_serial_1.setTypeface(font);
        tv_serial_2.setTypeface(font);
        tv_serial_3.setTypeface(font);
        tv_serial_4.setTypeface(font);


        this.addView(view);
    }

    public void initEvent() {
        tv_title.setOnClickListener(this);
        tv_serial_1.setOnClickListener(this);
        tv_serial_2.setOnClickListener(this);
        tv_serial_3.setOnClickListener(this);
        tv_serial_4.setOnClickListener(this);
    }

    public TitleFontAwesome setTitle(String title) {
        tv_title.setText(title);
        return this;
    }


    public TitleFontAwesome setIcon1(int iconId) {
        tv_serial_1.setText(iconId);
        return this;
    }

    public TitleFontAwesome setIcon2(int iconId) {
        tv_serial_2.setText(iconId);
        return this;
    }

    public TitleFontAwesome setIcon3(int iconId) {
        tv_serial_3.setText(iconId);
        return this;
    }

    public TitleFontAwesome setIcon4(int iconId) {
        tv_serial_4.setText(iconId);
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
            mOnClickListener.onClick(view);
        }
    }
}

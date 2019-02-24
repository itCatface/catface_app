package cc.catface.module_start.main.domain;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import cc.catface.module_start.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 * 主页底部tab[带消息数]
 */
public class Tab {

    private Class<? extends Fragment> mFmClz;       // 当前fragment[c]
    private String mTabTitle;                       // tab标题[c]
    private int mTabTextColorDefault = Color.argb(255, 204, 204, 204);          // 默认tab文字颜色
    private int mTabTextColorSelected;              // 选中tab文字颜色[c]
    private int mTabIconDefault;                    // 默认icon[c]
    private int mTabIconSelected;                   // 选中icon[c]


    public Tab(Class<? extends Fragment> fragmentClass, String tabTitle, int tabTextColorSelected, int tabIconDefault, int tabIconSelected) {
        this.mFmClz = fragmentClass;
        this.mTabTitle = tabTitle;
        this.mTabTextColorSelected = tabTextColorSelected;
        this.mTabIconDefault = tabIconDefault;
        this.mTabIconSelected = tabIconSelected;
    }

    /* 获取当前fragment类 */
    public Class<? extends Fragment> getFmClz() {
        return mFmClz;
    }

    /* 获取当前tab标题 */
    public String getTabText() {
        return mTabTitle;
    }

    /** 设置tab选中状态 */
    public void setChecked(boolean checked) {
        if (checked) {
            tv_tab.setTextColor(mTabTextColorSelected);
            iv_tab.setImageResource(mTabIconSelected);
        } else {
            tv_tab.setTextColor(mTabTextColorDefault);
            iv_tab.setImageResource(mTabIconDefault);
        }
    }


    /* 布局 & 控件 */
    private ImageView iv_tab;
    private TextView tv_tab;
    private TextView tv_new_msg;

    public View getTabView(Context context) {
        View view_main_tab = View.inflate(context, R.layout.view_main_tab, null);
        iv_tab = (ImageView) view_main_tab.findViewById(R.id.iv_tab);
        tv_tab = (TextView) view_main_tab.findViewById(R.id.tv_tab);
        tv_new_msg = (TextView) view_main_tab.findViewById(R.id.tv_new_msg);
        iv_tab.setImageResource(mTabIconDefault);
        tv_tab.setText(mTabTitle);
        return view_main_tab;
    }

    /** 设置新消息数量 */
    public void setNewMsgCount(int count) {
        if (count > 0) {
            tv_new_msg.setText(String.valueOf(count));
        }
        tv_new_msg.setBackgroundResource(R.drawable.ic_new_msg);
    }
}

package cc.catface.base.utils.android.common_print.toast;

import android.view.View;

import cc.catface.base.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface TToastI {


    boolean MODE_DEBUG = true;


    /** 系统普通Toast */
    void showShortNormal(String content);

    void showLongNormal(String content);


    /** 立即更新Toast显示内容 */
    void showShortContent(String content);

    void showLongContent(String content);


    /** 立即弹出Toast */
    void showShortImmediately(String content);

    void showLongImmediately(String content);


    /** 自定义Gravity对齐 */
    void showShortGravity(String content, int gravity);

    void showLongGravity(String content, int gravity);


    /** 自定义Location位置 */
    void showShortLocationLeftBelow(String content, View view);

    void showLongLocationLeftBelow(String content, View view);

    void showShortLocation(String content, int xOffSet, int yOffSet);

    void showLongLocation(String content, int xOffSet, int yOffSet);


    /** 定制Toast样式[https://github.com/bravinshi/BToast] */
    String B_BG_COLOR_NORMAL = "#DDDDDD";
    String B_BG_COLOR_INFO = "#31B0D5";
    String B_BG_COLOR_SUCCESS = "#449D44";
    String B_BG_COLOR_CANCEL = "#388E3C";
    String B_BG_COLOR_WARNING = "#EC971F";
    String B_BG_COLOR_ERROR = "#D9534F";

    int B_ICON_ID_NORMAL = R.mipmap.toast_ic_default_warning_sharp_white_96;
    int B_ICON_ID_INFO = R.mipmap.toast_ic_default_info_white_96;
    int B_ICON_ID_SUCCESS = R.mipmap.toast_ic_default_check_white_96;
    int B_ICON_ID_CANCEL = R.mipmap.toast_ic_default_clear_white_96;
    int B_ICON_ID_WARNING = R.mipmap.toast_ic_default_warning_white_96;
    int B_ICON_ID_ERROR = R.mipmap.toast_ic_default_error_white_96;

    void showBShortView(String content, int level);

    void showBLongView(String content, int level);


    /** 定制Toast动画 */
    void showShortAnim(String content, int animId);

    void showLongAnim(String content, int animId);


    /** 取消Toast实例并置空 */
    void clearToast();
}

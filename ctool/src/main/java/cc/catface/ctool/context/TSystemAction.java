package cc.catface.ctool.context;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc: 跳转系统界面
 */
public class TSystemAction {


    /* 系统wifi&流量设置页面 */
    public static final String ACTION_WIRELESS_SETTINGS = android.provider.Settings.ACTION_WIRELESS_SETTINGS;

    public static void openAction(String action) {
        TApp.getInstance().startActivity(new Intent(action).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public static void call(Context context, String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }

}

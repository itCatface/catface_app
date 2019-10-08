package cc.catface.ctool.system.netstate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class NetStateUtil {
    public static final int NETWORK_NONE = -1;  // 无网络
    public static final int NETWORK_MOBILE = 0; // 移动网络
    public static final int NETWORK_WIFI = 1;   // 无线网络

    public static int getNetWorkType(Context context) {
        int result = NETWORK_NONE;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (null == activeNetworkInfo || !activeNetworkInfo.isConnected()) return result;
        if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) result = NETWORK_WIFI;
        if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) result = NETWORK_MOBILE;

        return result;
    }
}

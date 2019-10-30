package cc.catface.ctool.context.netstate;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import cc.catface.ctool.context.TContext;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TNetwork {

    /** 判断手机是否连接网络 */
    public static boolean isNetAvail() {
        ConnectivityManager cwjManager = (ConnectivityManager) TContext.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cwjManager.getActiveNetworkInfo();
        return null != info && info.isAvailable();
    }


    /** ip */
    public static String getIp() {
        WifiManager wifiManager = (WifiManager) TContext.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (null == wifiManager) return "";
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        return convertIp(connectionInfo.getIpAddress());
    }

    private static String convertIp(int ip) {
        return (ip & 0xff) + "." + ((ip >> 8) & 0xff) + "." + ((ip >> 16) & 0xff) + "." + ((ip >> 24) & 0xff);
    }
}

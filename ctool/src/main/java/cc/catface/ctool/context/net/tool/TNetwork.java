package cc.catface.ctool.context.net.tool;

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
        ConnectivityManager manager = (ConnectivityManager) TContext.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == manager) return false;
        NetworkInfo info = manager.getActiveNetworkInfo();
        return null != info && info.isAvailable();
    }

    /* 判断设备是否wifi连接 */
    public static boolean isWIFI() {
        ConnectivityManager manager = (ConnectivityManager) TContext.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == manager) return false;
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return null != networkInfo && networkInfo.isConnected();
    }

    /* 判断设备是否数据连接 */
    public static boolean isMobile() {
        ConnectivityManager manager = (ConnectivityManager) TContext.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == manager) return false;
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE_DUN);
        return null != networkInfo && networkInfo.isConnected();
    }

    /* 判断是否以太网连接 */
    public static boolean isEthernet() {
        ConnectivityManager manager = (ConnectivityManager) TContext.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == manager) return false;
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        return null != networkInfo && networkInfo.isConnected();
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

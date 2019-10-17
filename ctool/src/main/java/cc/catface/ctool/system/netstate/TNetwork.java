package cc.catface.ctool.system.netstate;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TNetwork {
    /** ip */
    public static String getIp(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (null == wifiManager) return "";
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        return convertIp(connectionInfo.getIpAddress());
    }

    private static String convertIp(int ip) {
        return (ip & 0xff) + "." + ((ip >> 8) & 0xff) + "." + ((ip >> 16) & 0xff) + "." + ((ip >> 24) & 0xff);
    }
}

package cc.catface.base.utils.android.common_print.log;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TLog {

    /**  */
    private static boolean MODE_DEBUG = true;
    private static String DEFAULT_TAG = "root";

    /** 仅供调试使用 */
    private static boolean SAVE_LOGS = false;
    private static String LOGS_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/log_catface/" + new Date() + "-" + UUID.randomUUID();
    private static int IS_NORMAL_LOGS = 0, IS_NET_LOGS = 1;

    public static void d(String content) {
        d(DEFAULT_TAG, content);
    }

    public static void d(String tag, String content) {
        if (!MODE_DEBUG) return;
        content = ("❥❥❥[" + DEFAULT_TAG + "]" + tag + "❥❥" + content);
        Log.d(tag, content);
        saveLogs(IS_NORMAL_LOGS, content);
    }

    public static void net(String url, Map<String, String> map) {
        net(DEFAULT_TAG, url, map);
    }

    public static void net(String tag, String url, Map<String, String> map) {
        if (!MODE_DEBUG) return;
        url = ("❥❥❥[" + DEFAULT_TAG + "]" + tag + "❥❥url[" + url + "]❥map[" + map.toString() + "]");
        Log.d(tag, url);
        saveLogs(IS_NET_LOGS, url);
    }


    public static void saveLogs(int situation, String content) {
        if (!SAVE_LOGS) return;

        try {
            File file = new File(LOGS_PATH);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) parentFile.mkdirs();
            if (!file.exists()) file.createNewFile();
        } catch (Exception e) {
            d("创建logs收集目录及文件失败：" + e.toString());
        }
    }
}
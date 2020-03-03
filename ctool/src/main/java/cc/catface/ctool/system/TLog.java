package cc.catface.ctool.system;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TLog {

    private static boolean IS_PRINT_LOG = true;
    private static boolean IS_SAVE_LOG = false;
    private static String DEFAULT_TAG = "tag_catface";
    private static String LOG_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/log_catface/" + new Date() + "-" + UUID.randomUUID();


    public static void init(String defaultTag, boolean isPrintLog, boolean isSaveLog, String logPath) {
        DEFAULT_TAG = defaultTag;
        IS_PRINT_LOG = isPrintLog;
        IS_SAVE_LOG = isSaveLog;
        LOG_PATH = logPath;
    }


    public static void d(Object content) {
        d(DEFAULT_TAG, content);
    }

    public static void d(String tag, Object content) {
        if (!IS_PRINT_LOG) return;

        String log = "";
        if (content instanceof String) {
            log = ("卍卍卍 " + content);
        } else if (content instanceof Object[]) {
            log = ("卍卍卍 " + Arrays.toString((Object[]) content));
        } else if (content instanceof List || content instanceof Map) {
            log = ("卍卍卍 " + content.toString());
        }

        Log.d(tag, log);
        saveLogs(log);
    }

    public static void e(Object content) {
        e(DEFAULT_TAG, content);
    }

    public static void e(String tag, Object content) {
        if (!IS_PRINT_LOG) return;

        String log = "";
        if (content instanceof String) {
            log = ("卍卍卍 " + content);
        } else if (content instanceof Object[]) {
            log = ("卍卍卍 " + Arrays.toString((Object[]) content));
        } else if (content instanceof List || content instanceof Map) {
            log = ("卍卍卍 " + content.toString());
        }

        Log.e(tag, log);
        saveLogs(log);
    }

    public static void net(String url, Map<String, String> map) {
        net(DEFAULT_TAG, url, map);
    }

    public static void net(String tag, String url, Map<String, String> map) {
        if (!IS_PRINT_LOG) return;

        url = ("卍卍卍卍卍 url[" + url + "] ╬ map[" + map.toString() + "]");

        Log.d(tag, url);
        saveLogs(url);
    }


    public static void saveLogs(String content) {
        if (!IS_SAVE_LOG) return;

        try {
            File file = new File(LOG_PATH);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) parentFile.mkdirs();
            if (!file.exists()) file.createNewFile();
        } catch (Exception e) {
            d("创建logs收集目录及文件失败：" + e.toString());
        }
    }
}

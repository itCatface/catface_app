package cc.catface.base.utils.android.common_print.log;

import android.os.Environment;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
interface TLogI {
    boolean MODE_DEBUG = true;

    String DEFAULT_TAG = "root";

    void d(String content);

    void d(String tag, String content);


    void net(String url, Map<String, String> map);

    void net(String tag, String url, Map<String, String> map);


    /** 仅供调试使用 */
    boolean SAVE_LOGS = false;
    String LOGS_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/log_catface/" + new Date() + "-" + UUID.randomUUID();
    int IS_NORMAL_LOGS = 0, IS_NET_LOGS = 1;

    void saveLogs(int situation, String content);
}

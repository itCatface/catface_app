package cc.catface.ctool.system;

import android.text.TextUtils;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TNull {

    /* 判断对象是否为null */
    public static boolean isNull(Object object) {
        return null == object;
    }

    /* 判断字符串是否为null或空串 */
    public static boolean isNullOrEmpty(String string) {
        return null == string || TextUtils.isEmpty(string);
    }

}

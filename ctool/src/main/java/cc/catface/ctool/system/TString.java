package cc.catface.ctool.system;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TString {

    public static String convert2String(Object object) {
        return "" + object;
    }


    /* 去除文本中的所有空格和换行符 */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}

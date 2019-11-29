package cc.catface.ctool.system;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TDate {

    public static final long ONE_DAY_MS = 86400000; // 1天(d)=86400000毫秒(ms)
    // 20170915000000 || 20170914000000 || 20170913000000 || 20170907000000
    public static final long TODAY_24 = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Long(System.currentTimeMillis() + ONE_DAY_MS)).substring(0, 8) + "000000");
    public static final long TODAY_0 = Long.parseLong(transDate(new Date(), 6).substring(0, 8) + "000000");
    public static final long YESTERDAY_0 = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Long(System.currentTimeMillis() - ONE_DAY_MS)).substring(0, 8) + "000000");
    public static final long SEVEN_DAYS_AGO = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Long(System.currentTimeMillis() - ONE_DAY_MS * 7)).substring(0, 8) + "000000");


    /* 获取本地系统日期 */
    public static Date getLocalDate() {
        return new Date(System.currentTimeMillis());
    }

    /* 获取当前网络时间戳 */
    public static String getNetTimestamp() {
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection conn = url.openConnection();
            conn.connect();
            return conn.getDate() + "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /* 日期字符串转日期 */
    public static Date str2Date(String dateStr, int type) {
        Date date = null;
        try {
            switch (type) {
                case 0:
                    date = new SimpleDateFormat("yyyyMMddHHmmss").parse(dateStr);
                    break;
                case 1:
                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    /* 时间戳转日期字符串 */
    public static String timeStamp2DateStr(long timeStamp, int type) {
        String dt = "";
        switch (type) {
            case 0:
                dt = new SimpleDateFormat("yyyyMMddHHmmss").format(timeStamp);
                break;
            case 1:
                dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(timeStamp);
                break;
        }
        return dt;
    }


    public static String transDate(Date date, int type) {

        String formatDate = null;

        switch (type) {

            case 0: // 2016-7-6
                formatDate = DateFormat.getDateInstance().format(date);
                break;

            case 1: // 2016年7月6日 星期三
                formatDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                break;

            case 2: // 2016-07-06 09:39:58
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                formatDate = format.format(date);
                break;

            case 3: // 2016-07-06 09:42:44
                DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                formatDate = format2.format(date);
                break;

            case 4: // 20160706094533
                DateFormat format3 = new SimpleDateFormat("yyyyMMddHHmmss");
                formatDate = format3.format(date);
                break;

            case 5: // 0830
                DateFormat format4 = new SimpleDateFormat("HHmm");
                formatDate = format4.format(date);
                break;

            case 6: // 201607060945
                DateFormat format5 = new SimpleDateFormat("yyyyMMddHHmm");
                formatDate = format5.format(date);
                break;
            case 7: // 0706_0942_2016
                DateFormat format6 = new SimpleDateFormat("MMdd_HHmm_yyyy");
                formatDate = format6.format(date);
                break;
        }

        return formatDate;
    }

}

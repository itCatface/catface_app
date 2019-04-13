package cc.catface.base.utils.android.net.Utils.core;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc handle exceptions of the retrofit request
 */
public class RxExceptionUtil {

    public static String exceptionHandler(Throwable e) {
        String errorMsg = "未知错误";
        if (e instanceof UnknownHostException) {
            errorMsg = "网络不可用";
        } else if (e instanceof SocketTimeoutException) {
            errorMsg = "请求网络超时";
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            errorMsg = convertStatusCode(httpException);
        } else if (e instanceof ParseException || e instanceof JSONException) {
            errorMsg = "数据解析错误";
        }
        return errorMsg;
    }

    private static String convertStatusCode(HttpException httpException) {
        String msg = "";
        if (httpException.code() >= 500 && httpException.code() < 600) {
            msg = "服务器处理请求错误";
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            msg = "服务器无法处理请求";
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            msg = "当前请求已被重定向";
        } else {
            msg = httpException.message();
        }
        return msg;
    }
}

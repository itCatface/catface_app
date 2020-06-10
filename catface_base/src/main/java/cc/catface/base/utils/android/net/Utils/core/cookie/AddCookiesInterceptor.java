package cc.catface.base.utils.android.net.Utils.core.cookie;

import android.content.Context;

import java.io.IOException;
import java.util.HashSet;

import cc.catface.ctool.context.TApp;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc support cookies & stored in SharedPreferences
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet<String>) TApp.getInstance().getSharedPreferences("config", Context.MODE_PRIVATE).getStringSet("cookie", null);
        if (null != preferences) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}
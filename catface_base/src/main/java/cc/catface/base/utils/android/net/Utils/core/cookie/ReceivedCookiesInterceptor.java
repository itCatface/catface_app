package cc.catface.base.utils.android.net.Utils.core.cookie;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import cc.catface.ctool.context.TContext;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc support cookies & stored in SharedPreferences
 */
public class ReceivedCookiesInterceptor implements Interceptor {

    @Override public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        List<String> headers = originalResponse.headers("Set-Cookie");

        if (!headers.isEmpty()) {
            HashSet<String> cookies = new HashSet<>(headers);
            SharedPreferences.Editor config = TContext.getContext().getSharedPreferences("config", Context.MODE_PRIVATE).edit();
            config.putStringSet("cookie", cookies);
            config.apply();
        }

        return originalResponse;
    }
}
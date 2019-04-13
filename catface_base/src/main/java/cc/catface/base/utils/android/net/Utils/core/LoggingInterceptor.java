package cc.catface.base.utils.android.net.Utils.core;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class LoggingInterceptor implements Interceptor {
    private final String TAG = getClass().getName();

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        // you can print the message of the request.
        Response response = chain.proceed(request);
        // you can print the message of the response.
        return response;
    }
}

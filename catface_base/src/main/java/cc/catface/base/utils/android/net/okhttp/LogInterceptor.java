package cc.catface.base.utils.android.net.okhttp;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LogInterceptor implements Interceptor {
    @NonNull @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();//请求发起的时间
        Log.i("OkHttp", String.format("发送请求==>%s\n%s", request.url(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();//收到响应的时间

        ResponseBody responseBody = response.peekBody(1024 * 1024);

        Log.i("OkHttp", String.format("接收响应<==%s\n%s\n%.1fms%n\n%s", response.request().url(), responseBody.string(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
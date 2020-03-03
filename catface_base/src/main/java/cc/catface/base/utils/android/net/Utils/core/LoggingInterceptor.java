package cc.catface.base.utils.android.net.Utils.core;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import cc.catface.ctool.system.TLog;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class LoggingInterceptor implements Interceptor {
    private final String TAG = "okhttp-->";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        return interceptLog0(chain);
    }


    private final Charset UTF8 = Charset.forName("UTF-8");

    private Response interceptLog0(Chain chain) throws IOException {
        /*
         Request request = chain.request();                 // 可获取请求
                                                            // 可对请求做统一修改
         Response response = chain.proceed(request);        // 处理请求得到响应
        */

        Request request = chain.request();


        TLog.d("--->method" + request.method());
        String url = URLDecoder.decode(request.url().toString(), "UTF-8");
        TLog.d("url-->" + url);
        TLog.d("body-->" + request.body());
        TLog.d("cacheControl-->" + request.cacheControl());
        TLog.d("headers-->" + request.headers());
        TLog.d("isHttps-->" + request.isHttps());
        TLog.d("newBuilder-->" + request.newBuilder());
        TLog.d("tag-->" + request.tag());
        TLog.d(request.toString());

        Response response = chain.proceed(request);
        TLog.d("<---isSuccessful-->" + response.isSuccessful());
        TLog.d("body-->" + response.body());
        TLog.d("cacheControl-->" + response.cacheControl());
        TLog.d("headers-->" + response.headers());
        TLog.d("newBuilder-->" + response.newBuilder());
        TLog.d("string-->" + response.toString());


        return response;
    }


    Response interceptLog1(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("token", "");
        builder.addHeader("code", "");

        RequestBody requestBody = request.body();

        String body = null;

        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            body = buffer.readString(charset);
        }

        TLog.d(String.format("发送请求\nmethod：%s\nurl：%s\nheaders: %sbody：%s", request.method(), request.url(), request.headers(), body));

        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        String rBody = null;


        //        if (HttpEngine.hasBody(response)) {
        //            BufferedSource source = responseBody.source();
        //            source.request(Long.MAX_VALUE); // Buffer the entire body.
        //            Buffer buffer = source.buffer();
        //
        //            Charset charset = UTF8;
        //            MediaType contentType = responseBody.contentType();
        //            if (contentType != null) {
        //                try {
        //                    charset = contentType.charset(UTF8);
        //                } catch (UnsupportedCharsetException e) {
        //                    e.printStackTrace();
        //                }
        //            }
        //            rBody = buffer.clone().readString(charset);
        //        }

        TLog.d(String.format("收到响应 %s%s %ss\n请求url：%s\n请求body：%s\n响应body：%s", response.code(), response.message(), tookMs, response.request().url(), body, rBody));

        return response;
    }


    Response interceptLog2(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间

        TLog.d("method-->" + request.method());

        RequestBody body1 = request.body();
        TLog.d(body1.contentLength());

        String method = request.method();
        if ("POST".equals(method)) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                TLog.d(String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}", request.url(), chain.connection(), request.headers(), sb.toString()));
            }
        } else {
            TLog.d(String.format("发送请求 %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        }
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();//收到响应的时间
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(response.body().contentLength());

        String s = response.request().url().toString();
        String s1 = new String(s.getBytes(), "GBK");
        TLog.d("s1-->" + s1);

        TLog.d(String.format("接收响应: [%s] %n返回json:%s %n响应时长:%.1fms %n%s", response.request().url(), responseBody.string(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    }
}

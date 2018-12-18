package cc.catface.base.utils.android.net.retrofit;


import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 * 同一个项目中的请求拦截器最好统一处理
 */
public class LoggingInterceptor implements Interceptor {
    private final Charset UTF8 = Charset.forName("UTF-8");

    @Override public Response intercept(Chain chain) throws IOException {
        return interceptLog0(chain);
    }

    private Response interceptLog0(Chain chain) throws IOException {
        /*
         Request request = chain.request();                 // 可获取请求
                                                            // 可对请求做统一修改
         Response response = chain.proceed(request);        // 处理请求得到响应
        */

        Request request = chain.request();


        System.out.println("method" + request.method());
        System.out.println("url-->" + request.url());
        HttpUrl url = request.url();
        String decode = URLDecoder.decode(url.toString(), "UTF-8");
        System.out.println(decode);

        System.out.println("body-->" + request.body());
        System.out.println("cacheControl-->" + request.cacheControl());
        System.out.println("headers-->" + request.headers());
        System.out.println("isHttps-->" + request.isHttps());
        System.out.println("newBuilder-->" + request.newBuilder());
        System.out.println("tag-->" + request.tag());
        System.out.println(request.toString());


        long t1 = System.nanoTime();
        Response response = chain.proceed(request);
        long t = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - t1);
        System.out.println(t);


//        ResponseBody responseBody = response.peekBody(response.body().contentLength());
//        System.out.println("string-->" + responseBody.string());


        System.out.println("isSuccessful-->" + response.isSuccessful());
        System.out.println("body-->" + response.body());
        System.out.println("cacheControl-->" + response.cacheControl());
        System.out.println("headers-->" + response.headers());
        System.out.println("newBuilder-->" + response.newBuilder());
        System.out.println("string-->" + response.toString());


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

        System.out.println(String.format("发送请求\nmethod：%s\nurl：%s\nheaders: %sbody：%s", request.method(), request.url(), request.headers(), body));

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

        System.out.println(String.format("收到响应 %s%s %ss\n请求url：%s\n请求body：%s\n响应body：%s", response.code(), response.message(), tookMs, response.request().url(), body, rBody));

        return response;
    }


    Response interceptLog2(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间

        System.out.println("method-->" + request.method());

        RequestBody body1 = request.body();
        System.out.println(body1.contentLength());

        String method = request.method();
        if ("POST".equals(method)) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                System.out.println(String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}", request.url(), chain.connection(), request.headers(), sb.toString()));
            }
        } else {
            System.out.println(String.format("发送请求 %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        }
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();//收到响应的时间
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(response.body().contentLength());

        String s = response.request().url().toString();
        String s1 = new String(s.getBytes(), "GBK");
        System.out.println("s1-->" + s1);

        System.out.println(String.format("接收响应: [%s] %n返回json:%s %n响应时长:%.1fms %n%s", response.request().url(), responseBody.string(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    }
}

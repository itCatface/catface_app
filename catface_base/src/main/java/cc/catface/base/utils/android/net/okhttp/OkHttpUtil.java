package cc.catface.base.utils.android.net.okhttp;

import android.os.Bundle;
import android.os.Message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtil {

    private final static String TAG = OkHttpUtil.class.getSimpleName();

    private static OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(6, TimeUnit.SECONDS)
            .readTimeout(6, TimeUnit.SECONDS)
            .build();

    private static OkHttpHandler mOkHttpHandler = new OkHttpHandler();

    /**
     * get
     *
     * @param url                   请求地址
     * @param okHttpRequestCallback 回调
     */
    public static void get(final String url, final OkHttpRequestCallback okHttpRequestCallback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendMessage(e.toString(), false, okHttpRequestCallback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    sendMessage(response.body().string(), true, okHttpRequestCallback);
                } else {
                    sendMessage("errorCode:" + response.code(), false, okHttpRequestCallback);
                }
            }
        });
    }

    /**
     * get
     *
     * @param url                   请求地址
     * @param json                  json字符串
     * @param okHttpRequestCallback 回调
     */
    public static void get(final String url, final String json, final OkHttpRequestCallback okHttpRequestCallback) {
        String requestUrl = null;
        try {
            requestUrl = url + "?jsonStr=" + URLEncoder.encode(json, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            sendMessage("errorCode:" + "UnsupportedEncodingException", false, okHttpRequestCallback);
        }
        try {
            Request request = new Request.Builder()
                    .url(requestUrl)
                    .build();
            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    sendMessage(e.toString(), false, okHttpRequestCallback);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        sendMessage(response.body().string(), true, okHttpRequestCallback);
                    } else {
                        sendMessage("errorCode:" + response.code(), false, okHttpRequestCallback);
                    }
                }
            });
        } catch (IllegalArgumentException e) {
            sendMessage(e.toString(), false, okHttpRequestCallback);
            e.printStackTrace();
        }
    }

    /**
     * get
     *
     * @param url                   请求地址
     * @param params                参数
     * @param okHttpRequestCallback 回调
     */
    public static void get(final String url, Map<String, String> params, final OkHttpRequestCallback okHttpRequestCallback) {
        StringBuilder builder = new StringBuilder();
        builder.append(url);
        builder.append("?");
        int pos = 0;
        for (String key : params.keySet()) {
            if (pos > 0) {
                builder.append("&");
            }
            builder.append(String.format("%s=%s", key, params.get(key)));
            pos++;
        }
        try {
            Request request = new Request.Builder()
                    .url(builder.toString())
                    .build();
            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    sendMessage(e.toString(), false, okHttpRequestCallback);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        sendMessage(response.body().string(), true, okHttpRequestCallback);
                    } else {
                        sendMessage("errorCode:" + response.code(), false, okHttpRequestCallback);
                    }
                }
            });
        } catch (IllegalArgumentException e) {
            sendMessage(e.toString(), false, okHttpRequestCallback);
            e.printStackTrace();
        }
    }

    /**
     * post
     *
     * @param url                   请求地址
     * @param okHttpRequestCallback 回调
     */
    public static void post(String url, final OkHttpRequestCallback okHttpRequestCallback) {
        Map<String, String> params = null;
        post(url, params, okHttpRequestCallback);
    }

    /**
     * post
     *
     * @param url                   请求地址
     * @param params                参数
     * @param okHttpRequestCallback 回调
     */
    public static void post(final String url, final Map<String, String> params, final OkHttpRequestCallback okHttpRequestCallback) {
        FormBody.Builder builder = new FormBody.Builder();
        if (null != params) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendMessage(e.toString(), false, okHttpRequestCallback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    sendMessage(response.body().string(), true, okHttpRequestCallback);
                } else {
                    sendMessage("errorCode:" + response.code(), false, okHttpRequestCallback);
                }
            }
        });
    }

    /**
     * post
     *
     * @param url                   请求地址
     * @param json                  json字符串
     * @param okHttpRequestCallback 回调
     */
    public static void post(final String url, final String json, final OkHttpRequestCallback okHttpRequestCallback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendMessage(e.toString(), false, okHttpRequestCallback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    sendMessage(response.body().string(), true, okHttpRequestCallback);
                } else {
                    sendMessage("errorCode:" + response.code(), false, okHttpRequestCallback);
                }
            }
        });
    }

    private static void sendMessage(String result, boolean requestSuccess, OkHttpRequestCallback okHttpRequestCallback) {
        Message message = Message.obtain();
        message.what = requestSuccess ? OkHttpHandler.MESSAGE_REQUEST_SUCCESS : OkHttpHandler.MESSAGE_REQUEST_FAILURE;
        Bundle bundle = new Bundle();
        bundle.putString("result", result);
        message.obj = okHttpRequestCallback;
        message.setData(bundle);
        mOkHttpHandler.sendMessage(message);
    }
}

package cc.catface.base.utils.android.net.retrofit;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import cc.catface.base.utils.android.common_print.log.TLog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.Result;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class RetrofitT implements RetrofitI {
    final String TAG = getClass().getSimpleName();


    private static RetrofitT mInstance;
    private static OkHttpClient mClient;

    public static RetrofitT getInstance() {
        if (null == mInstance) {
            synchronized (RetrofitT.class) {
                if (null == mInstance) {
                    mInstance = new RetrofitT();
                }
            }
        }

        return mInstance;
    }


    private RetrofitT() {
        mClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
//                .sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory(CtxT.getCtx()), SSLSocketFactoryUtils.createTrustAllManager())
//                .hostnameVerifier(new SSLSocketFactoryUtils.TrustAllHostnameVerifier())
                .build();
    }


    private Class<RetrofitApi> clz = RetrofitApi.class;

    private <T> T getApi(String host, Class<T> clz) {
        if (!host.endsWith("/")) host += "/";
        return new Retrofit.Builder().baseUrl(host).client(mClient).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(ScalarsConverterFactory.create()).build().create(clz);
    }


    /** ----------------------------------------- 普通请求[基本方法] ----------------------------------------- */
    @Override public void get(String host, String suffix, final RetrofitCallback callback) {
        RetrofitApi api = getApi(host, clz);
        Call<String> call = api.get(suffix);
        call.enqueue(new Callback<String>() {
            @Override public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                response(response, callback);
            }

            @Override public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                failure(t.toString(), callback);
            }
        });
    }

    @Override public void get(String host, String suffix, Map<String, String> map, final RetrofitCallback callback) {
        RetrofitApi api = getApi(host, clz);
        Call<String> call = api.get(suffix, map);
        call.enqueue(new Callback<String>() {
            @Override public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                response(response, callback);
            }

            @Override public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                failure(t.toString(), callback);
            }
        });
    }

    @Override public void post(String host, String suffix, Map<String, String> map, final RetrofitCallback callback) {
        RetrofitApi api = getApi(host, clz);
        Call<String> call = api.post(suffix, map);
        call.enqueue(new Callback<String>() {
            @Override public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                response(response, callback);
            }

            @Override public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                failure(t.toString(), callback);
            }
        });
    }

    @Override public void post(String host, String suffix, String json, final RetrofitCallback callback) {
        RetrofitApi api = getApi(host, clz);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Call<String> call = api.post(suffix, body);
        call.enqueue(new Callback<String>() {
            @Override public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                response(response, callback);
            }

            @Override public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                failure(t.toString(), callback);
            }
        });
    }

    @Override
    public void postField(String host, String suffix, Map<String, String> map, final RetrofitCallback callback) {
        RetrofitApi api = getApi(host, clz);
        Call<String> call = api.postField(suffix, map);
        call.enqueue(new Callback<String>() {
            @Override public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                response(response, callback);
            }

            @Override public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                failure(t.toString(), callback);
            }
        });
    }

    @Override public void upload(String host, String suffix, String filePath, final RetrofitCallback callback) {
        RetrofitApi api = getApi(host, clz);
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            failure("当前上传文件不存在: " + filePath, callback);
            return;
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        Call<String> call = api.upload(suffix, part);
        call.enqueue(new Callback<String>() {
            @Override public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                response(response, callback);
            }

            @Override public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                failure(t.toString(), callback);
            }
        });
    }

    @Override
    public void upload(String host, String suffix, Map<String, String> map, String filePath, RetrofitCallback callback) {
        Map<String, String> fileMap = new HashMap<>();
        fileMap.put("file", filePath);
        upload(host, suffix, map, fileMap, callback);
    }

    @Override
    public void upload(String host, String suffix, Map<String, String> map, Map<String, String> fileMap, final RetrofitCallback callback) {
        RetrofitApi api = getApi(host, clz);
        Map<String, RequestBody> bodyMap = new HashMap<>();
        for (String key : fileMap.keySet()) {
            File file = new File(fileMap.get(key));
            if (!file.exists() || !file.isFile())
                System.out.println("RetrofitT-->upload-->当前文件不可上传: " + file.getAbsolutePath());
            else {
                System.out.println("RetrofitT-->upload-->当前文件存在: " + file.getAbsolutePath());
                bodyMap.put(key + "\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
            }
        }

        Call<String> call = api.upload(suffix, map != null ? map : new HashMap<String, String>(), bodyMap);   // java.lang.IllegalArgumentException: Query map was null.
        call.enqueue(new Callback<String>() {
            @Override public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                response(response, callback);
            }

            @Override public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                failure(t.toString(), callback);
            }
        });
    }

    @Override
    public void upload(String host, String suffix, Map<String, String> map, List<File> files, final RetrofitCallback callback) {
        RetrofitApi api = getApi(host, clz);

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (String key : map.keySet()) {
            builder.addFormDataPart(key, map.get(key));
        }

        for (File file : files) {
            builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        }
        RequestBody requestBody = builder.build();


        Call<String> call = api.upload(suffix, requestBody);
        call.enqueue(new Callback<String>() {
            @Override public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                response(response, callback);
            }

            @Override public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                failure(t.toString(), callback);
            }
        });
    }

    @Override public void download(String host, String suffix, final RetrofitCallback callback) {
        RetrofitApi api = getApi(host, clz);
        Call<ResponseBody> call = api.download(suffix);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                //                System.out.println("++++++++++++"+response.headers().names());
                DownloadEngine.response2ROM(response.body(), "C:\\Users\\Administrator\\Desktop\\temp1.jpg", new DownloadEngine.OnDownloadListener() {
                    @Override public void onSuc() {
                        System.out.println("suc");
                    }

                    @Override public void onProgress(Float progress) {
                        System.out.println(progress);
                    }

                    @Override public void onErr(String error) {

                    }
                });
                response(response, callback);
            }

            @Override public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                failure(t.toString(), callback);
            }
        });
    }

    /***** 带进度的文件上传和下载 *****/
    @Override public void uploadWithProgress(String url, String filePath, DownloadCallback callback) {
        RetrofitApi api = getApi(url, clz);

        File file = new File(filePath);
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        Call<Result> call = api.uploadWithProgress(body);
        if (null != callback) call.enqueue(callback);
    }


    private void failure(String msg, RetrofitCallback callback) {
        callback.onError(msg);
    }

    private void response(Response response, RetrofitCallback callback) {
        try {
            int code = response.code();
            String result = response.body().toString();
            TLog.d(TAG, "response's successful-->" + result + " || code is-->" + code);
            if (response.isSuccessful()) callback.onSuccess(result);
            else failure("response's not successful, response.code()-->" + code, callback);
        } catch (Exception e) {
            TLog.d(TAG, "exception-->" + e.toString());
            failure(e.toString(), callback);
        }
    }


    /** 传入完整url */
    @Override public void get(String url, RetrofitCallback callback) {
        get(url, "", callback);
    }

    @Override public void get(String url, Map<String, String> map, RetrofitCallback callback) {
        get(url, "", map, callback);
    }

    @Override public void post(String url, Map<String, String> map, RetrofitCallback callback) {
        TLog.d("post", url + " || " + map.toString());
        post(url, "", map, callback);
    }

    @Override public void post(String url, String json, RetrofitCallback callback) {
        post(url, "", json, callback);
    }

    @Override public void postField(String url, Map<String, String> map, RetrofitCallback callback) {
        postField(url, "", map, callback);
    }

    @Override public void upload(String url, String filePath, RetrofitCallback callback) {
        upload(url, "", filePath, callback);
    }

    @Override public void upload(String url, Map<String, String> map, String filePath, RetrofitCallback callback) {
        upload(url, "", map, filePath, callback);
    }

    @Override
    public void upload(String url, Map<String, String> map, Map<String, String> fileMap, RetrofitCallback callback) {
        TLog.d("upload", url + " || " + map.toString());

        upload(url, "", map, fileMap, callback);
    }

    @Override public void upload(String host, Map<String, String> map, List<File> files, RetrofitCallback callback) {
        upload(host, "", map, files, callback);
    }

    @Override public void download(String url, RetrofitCallback callback) {
        download(url, "", callback);
    }


    /** 使用默认host */
    private static String defaultHost;

    private String getDefaultHost() {
        if (null == defaultHost || !defaultHost.contains("http")) defaultHost = "--";
        return defaultHost;
    }

    @Override public void getWithDefaultHost(String suffix, RetrofitCallback callback) {
        get(getDefaultHost(), suffix, callback);
    }

    @Override public void getWithDefaultHost(String suffix, Map<String, String> map, RetrofitCallback callback) {
        get(getDefaultHost(), suffix, map, callback);
    }

    @Override public void postWithDefaultHost(String suffix, Map<String, String> map, RetrofitCallback callback) {
        post(getDefaultHost(), suffix, map, callback);
    }

    @Override public void postWithDefaultHost(String suffix, String json, RetrofitCallback callback) {
        post(getDefaultHost(), suffix, json, callback);
    }

    @Override public void postFieldWithDefaultHost(String suffix, Map<String, String> map, RetrofitCallback callback) {
        postField(getDefaultHost(), suffix, map, callback);
    }

    @Override public void uploadWithDefaultHost(String suffix, String filePath, RetrofitCallback callback) {
        upload(getDefaultHost(), suffix, filePath, callback);
    }

    @Override
    public void uploadWithDefaultHost(String suffix, Map<String, String> map, String filePath, RetrofitCallback callback) {
        upload(getDefaultHost(), suffix, map, filePath, callback);
    }

    @Override
    public void uploadWithDefaultHost(String suffix, Map<String, String> map, Map<String, String> fileMap, RetrofitCallback callback) {
        upload(getDefaultHost(), suffix, map, fileMap, callback);
    }

    @Override
    public void uploadWithDefaultHost(String suffix, Map<String, String> map, List<File> files, RetrofitCallback callback) {
        upload(getDefaultHost(), suffix, map, files, callback);
    }

    @Override public void downloadWithDefaultHost(String suffix, RetrofitCallback callback) {
        download(getDefaultHost(), suffix, callback);
    }
}

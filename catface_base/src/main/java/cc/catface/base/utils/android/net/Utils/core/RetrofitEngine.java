package cc.catface.base.utils.android.net.Utils.core;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import cc.catface.base.utils.android.net.Utils.NetApi;
import cc.catface.base.utils.android.net.Utils.core.cookie.AddCookiesInterceptor;
import cc.catface.base.utils.android.net.Utils.core.cookie.ReceivedCookiesInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc init the retrofit with the okhttp client & get the api by single instance
 */
public class RetrofitEngine {

    private static class Holder {
        static NetApi instance = new RetrofitEngine().getRetrofit();
    }

    public static NetApi getNetApi() {
        return Holder.instance;
    }

    public NetApi getRetrofit() {
        return initRetrofit(initOkHttp()).create(NetApi.class);
    }

    @NonNull
    private Retrofit initRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(RetrofitConstant.host)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // support rx
                .addConverterFactory(GsonConverterFactory.create())         // support gson[auto parse json]
                .build();
    }

    @NonNull
    private OkHttpClient initOkHttp() {
        return new OkHttpClient().newBuilder()
                .readTimeout(RetrofitConstant.timeout, TimeUnit.SECONDS)
                .connectTimeout(RetrofitConstant.timeout, TimeUnit.SECONDS)
                .writeTimeout(RetrofitConstant.timeout, TimeUnit.SECONDS)
                .addInterceptor(new ReceivedCookiesInterceptor()).addInterceptor(new AddCookiesInterceptor())   // cookie
                .addInterceptor(new cc.catface.base.utils.android.net.Utils.core.HttpLoggingInterceptor().setLevel(cc.catface.base.utils.android.net.Utils.core.HttpLoggingInterceptor.Level.BODY))
//                .addInterceptor(new CommonInterceptor())    // interceptor of common params.
                .addInterceptor(new LoggingInterceptor())   // interceptor of logging.
                .retryOnConnectionFailure(true)
                .build();
    }
}


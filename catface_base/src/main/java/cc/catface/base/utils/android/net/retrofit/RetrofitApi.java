package cc.catface.base.utils.android.net.retrofit;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc 封装风格与OkHttp一样(使用)
 */
public interface RetrofitApi {

    /* retrofit2 Missing either @GET URL or @Url parameter. --> @GET(".") or @GET("/") */
    @GET Call<String> get(@Url String suffix);

    /* 有参数 + @QueryMap */
    @GET Call<String> get(@Url String suffix, @QueryMap Map<String, String> map);


    @POST Call<String> post(@Url String suffix, @QueryMap Map<String, String> map);

    /* 有参数 + @FieldMap and @FormUrlEncoded */
    @FormUrlEncoded @POST Call<String> postField(@Url String suffix, @FieldMap Map<String, String> map);

    @Headers({"Content-Type: application/json", "Accept: application/json"})    // 需要添加头
    @POST Call<String> post(@Url String suffix, @Body RequestBody json);              // 传入的参数为RequestBody


    @Multipart @POST Call<String> upload(@Url String suffix, @Part MultipartBody.Part file);

    @Multipart @POST
    Call<String> upload(@Url String suffix, @QueryMap Map<String, String> map, @PartMap Map<String, RequestBody> fileMap);

    @POST Call<String> upload(@Url String suffix, @Body RequestBody Body);


    @Streaming  // 避免下载大文件被Retrofit整个读进内存
    @GET Call<ResponseBody> download(@Url String suffix);


    /* 带进度的文件上传和下载 */
    @Multipart @POST Call<Result> uploadWithProgress(@Part RequestBody file);
}

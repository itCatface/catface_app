package cc.catface.base.utils.android.net.Utils;

import com.google.gson.JsonObject;

import java.util.Map;

import cc.catface.base.utils.android.net.Utils.core.domain.BaseResponse;
import cc.catface.base.utils.android.net.Utils.core.domain.TestData;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * 1. @FormUrlEncoded与@FieldMap需连用
 * 2. 参数添加方式@QueryMap/@FieldMap/@HeaderMap
 * 3. url传递方式[1.base_url(host)+注解(接口) | 2.通过注解@Url传递完整接口路径带host]
 * 4. 传递json需使用RequestBody包装该json串[如同时需添加参数可用@HeaderMap传递]
 * 5. 文件下载时需添加@Streaming防止内存泄漏
 */
public interface NetApi {


    @GET("test_get")
    Observable<BaseResponse<TestData>> test_get();

    @GET("test_get")
    Call<JsonObject> test_get1();

    @GET("test_get_")
    Observable<BaseResponse<TestData>> test_get(@Query("name") String name, @Query("pass") String pass);

    @POST("test_post")
    Observable<BaseResponse<TestData>> test_post();

    @FormUrlEncoded
    @POST("test_post_")
    Observable<BaseResponse<TestData>> test_post(@Field("name") String name, @Field("pass") String pass);

    @PUT("test_put")
    Observable<BaseResponse<TestData>> test_put();

    @PUT("test_put_")
    Observable<BaseResponse<TestData>> test_put(@Query("name") String name, @Query("pass") String pass);

    @DELETE("test_delete_/{id}")
    Observable<BaseResponse<TestData>> test_delete(@Path("id") int id);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("test_json_")
    Observable<BaseResponse<TestData>> test_json_(@Body RequestBody json);

    @Multipart
    @POST("test_upload")
    Call<String> test_upload(@QueryMap Map<String, String> map, @Part MultipartBody.Part file);

    @Streaming
    @GET("test_download")
    Call<ResponseBody> test_download(@Query("name") String name, @Query("pass") String pass);

    @GET("login")
    Observable<BaseResponse<TestData>> login(@QueryMap Map<String, String> map);


    /** 仅返回后台返回数据 */
    @GET("test_get")
    Call<JsonObject> testGet();

    /** common api */
    @Streaming
    @GET
    Observable<ResponseBody> commonDownload(@Url String url, @Header("Range") String range);

    /** 录音笔-下载验证码图片 */
    @Streaming
    @GET
    Observable<ResponseBody> downloadCode(@Url String url);

}

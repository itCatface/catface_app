package cc.catface.base.utils.android.net.Utils;

import java.util.Map;

import cc.catface.base.utils.android.net.Utils.core.BaseResponse;
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
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

public interface NetApi {


    @GET("test_get")
    Observable<BaseResponse<TestData>> test_get();

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


    //    /**
    //     * 有效链接
    //    @GET(Constans.retrofit)
    //    Call<Bean> getRetrofit();
    //
    //    @GET(Constans.retrofit)
    //    Observable<BaseResponse<Demo>> getData();
    //
    //    @GET(Constans.retrofitList)
    //    Observable<BaseResponse<List<Demo>>> getDemoList();
    //
    //
    //    *//**
    //     * TODO Get请求
    //     *//*
    //    //第一种方式：GET不带参数
    //    @GET("retrofit.txt")
    //    Observable<BaseResponse<Demo>> getUser();
    //    @GET
    //    Observable<Demo> getUser(@Url String url);
    //    @GET
    //    Observable<Demo> getUser1(@Url String url); //简洁方式   直接获取所需数据
    //    //第二种方式：GET带参数
    //    @GET("api/data/{type}/{count}/{page}")
    //    Observable<Demo> getUser(@Path("type") String type, @Path("count") int count, @Path("page") int page);
    //    //第三种方式：GET带请求参数：https://api.github.com/users/whatever?client_id=xxxx&client_secret=yyyy
    //    @GET("users/whatever")
    //    Observable<Demo> getUser(@Query("client_id") String id, @Query("client_secret") String secret);
    //    @GET("users/whatever")
    //    Observable<Demo> getUser(@QueryMap Map<String, String> info);
    //
    //    *//**
    //     * TODO POST请求
    //     *//*
    //    //第一种方式：@Body
    //    @Headers("Accept:application/json")
    //    @POST("login")
    //    Observable<Demo> postUser(@Body RequestBody body);
    //    //第二种方式：@Field
    //
    //    @Headers("Accept:application/json")
    //    @POST("auth/login")
    //    @FormUrlEncoded
    //    Observable<Demo> postUser(@Field("username") String username, @Field("password") String password);
    //    //多个参数
    //    Observable<Demo> postUser(@FieldMap Map<String, String> map);
    //
    //    *//**
    //     * TODO DELETE
    //     *//*
    //    @DELETE("member_follow_member/{id}")
    //    Observable<Demo> delete(@Header("Authorization") String auth, @Path("id") int id);
    //
    //    *//**
    //     * TODO PUT
    //     *//*
    //    @PUT("member")
    //    Observable<Demo> put(@HeaderMap Map<String, String> headers,
    //                         @Query("nickname") String nickname);
    //
    //    *//**
    //     * TODO 文件上传
    //     *//*
    //    @Multipart
    //    @POST("upload")
    //    Observable<ResponseBody> upload(@Part("description") RequestBody description, @Part MultipartBody.Part file);
    //
    //    //亲测可用
    //    @Multipart
    //    @POST("member/avatar")
    //    Observable<Demo> uploadImage(@HeaderMap Map<String, String> headers, @Part MultipartBody.Part file);
    //
    //    *//**
    //     * 多文件上传
    //     *//*
    //    @Multipart
    //    @POST("register")
    //    Observable<ResponseBody> upload(@PartMap Map<String, RequestBody> params, @Part("description") RequestBody description);
    //    //Observable<ResponseBody> upload(@Part() List<MultipartBody.Part> parts);
    //
    //    @Multipart
    //    @POST("member/avatar")
    //    Observable<Demo> uploadImage1(@HeaderMap Map<String, String> headers, @Part List<MultipartBody.Part> file);
    //
    //    *//**
    //     * 来自https://blog.csdn.net/impure/article/details/79658098
    //     * @Streaming 这个注解必须添加，否则文件全部写入内存，文件过大会造成内存溢出
    //     *//*
    //    @Streaming
    //    @GET
    //    Observable<ResponseBody> download(@Header("RANGE") String start, @Url String url);*/
}

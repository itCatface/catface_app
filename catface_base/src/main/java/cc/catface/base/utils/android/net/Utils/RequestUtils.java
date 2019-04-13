package cc.catface.base.utils.android.net.Utils;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cc.catface.base.utils.android.net.Utils.core.CustomObserver;
import cc.catface.base.utils.android.net.Utils.core.RetrofitEngine;
import cc.catface.base.utils.android.net.Utils.core.RxDispatcher;
import cc.catface.base.utils.android.net.Utils.core.domain.TestData;
import cc.catface.base.utils.android.net.Utils.download.DownloadEngine;
import cc.catface.base.utils.android.net.Utils.upload.ProgressRequestBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 提交参数方式
 */
public class RequestUtils {

    public static void test_get(RxFragment rxFragment, CustomObserver<TestData> observer) {
        RetrofitEngine.getNetApi().test_get().compose(RxDispatcher.observableIO2Main(rxFragment)).subscribe(observer);
    }

    public static void test_get(RxFragment rxFragment, String name, String pass, CustomObserver<TestData> observer) {
        RetrofitEngine.getNetApi().test_get(name, pass).compose(RxDispatcher.observableIO2Main(rxFragment)).subscribe(observer);
    }

    public static void test_post(RxFragment rxFragment, CustomObserver<TestData> observer) {
        RetrofitEngine.getNetApi().test_post().compose(RxDispatcher.observableIO2Main(rxFragment)).subscribe(observer);
    }

    public static void test_post(RxFragment rxFragment, String name, String pass, CustomObserver<TestData> observer) {
        RetrofitEngine.getNetApi().test_post(name, pass).compose(RxDispatcher.observableIO2Main(rxFragment)).subscribe(observer);
    }

    public static void test_put(RxFragment rxFragment, CustomObserver<TestData> observer) {
        RetrofitEngine.getNetApi().test_put().compose(RxDispatcher.observableIO2Main(rxFragment)).subscribe(observer);
    }

    public static void test_put(RxFragment rxFragment, String name, String pass, CustomObserver<TestData> observer) {
        RetrofitEngine.getNetApi().test_put(name, pass).compose(RxDispatcher.observableIO2Main(rxFragment)).subscribe(observer);
    }


    public static void test_delete(RxFragment rxFragment, int id, CustomObserver<TestData> observer) {
        RetrofitEngine.getNetApi().test_delete(id).compose(RxDispatcher.observableIO2Main(rxFragment)).subscribe(observer);
    }

    public static void test_json(RxFragment rxFragment, String json, CustomObserver<TestData> observer) {
        RetrofitEngine.getNetApi().test_json_(json2RequestBody(json)).compose(RxDispatcher.observableIO2Main(rxFragment)).subscribe(observer);
    }

    public static void test_upload(Map<String, String> map, File file, ProgressRequestBody requestFile) {
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        RetrofitEngine.getNetApi().test_upload(map != null ? map : new HashMap<>(), body).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public static void test_download(String name, String pass, String savedPath, DownloadEngine.Callback listener) {
        RetrofitEngine.getNetApi().test_download(name, pass).enqueue(new Callback<ResponseBody>() {
            @Override public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                new DownloadEngine(response, savedPath, listener).start();
            }

            @Override public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFailure(t.toString());
            }
        });
    }


    public static void login(RxAppCompatActivity ctx, Map<String, String> map, CustomObserver<TestData> observer) {
        RetrofitEngine.getNetApi().login(map).compose(RxDispatcher.observableIO2Main(ctx)).subscribe(observer);
    }


    private static RequestBody json2RequestBody(String json) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    }
}


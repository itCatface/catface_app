package cc.catface.base.utils.android.net.retrofit;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface RetrofitI {


    /** 普通请求 */
    // 普通GET
    void get(String host, String suffix, final RetrofitCallback callback);

    // 带键值对参数GET
    void get(String host, String suffix, Map<String, String> map, final RetrofitCallback callback);

    // 带键值对参数POST
    void post(String host, String suffix, Map<String, String> map, final RetrofitCallback callback);

    // 带json参数POST
    void post(String host, String suffix, String json, final RetrofitCallback callback);

    // 带键值对表单参数POST
    void postField(String host, String suffix, Map<String, String> map, final RetrofitCallback callback);

    // 单个文件上传[文件对应的key暂在代码中固定]
    void upload(String host, String suffix, String filePath, final RetrofitCallback callback);

    // 带键值对参数及单个文件上传[文件对应的key暂在代码中固定]
    void upload(String host, String suffix, Map<String, String> map, String filePath, RetrofitCallback callback);

    // 带键值对参数及多个文件上传
    void upload(String host, String suffix, Map<String, String> map, Map<String, String> fileMap, final RetrofitCallback callback);

    // 带键值对参数及文件数组上传
    void upload(String host, String suffix, Map<String, String> map, List<File> files, final RetrofitCallback callback);

    // 下载
    void download(String host, String suffix, final RetrofitCallback callback);

    // 带进度的上传
    void uploadWithProgress(String url, String filePath, DownloadCallback callback);


    /** 使用完整url */
    void get(String url, RetrofitCallback callback);

    void get(String url, Map<String, String> map, RetrofitCallback callback);

    void post(String url, Map<String, String> map, RetrofitCallback callback);

    void post(String url, String json, RetrofitCallback callback);

    void postField(String url, Map<String, String> map, RetrofitCallback callback);

    void upload(String url, String filePath, RetrofitCallback callback);

    void upload(String url, Map<String, String> map, String filePath, RetrofitCallback callback);

    void upload(String url, Map<String, String> map, Map<String, String> fileMap, RetrofitCallback callback);

    void upload(String host, Map<String, String> map, List<File> files, RetrofitCallback callback);

    void download(String url, RetrofitCallback callback);


    /** 使用默认host */
    void getWithDefaultHost(String suffix, RetrofitCallback callback);

    void getWithDefaultHost(String suffix, Map<String, String> map, RetrofitCallback callback);

    void postWithDefaultHost(String suffix, Map<String, String> map, RetrofitCallback callback);

    void postWithDefaultHost(String suffix, String json, RetrofitCallback callback);

    void postFieldWithDefaultHost(String suffix, Map<String, String> map, RetrofitCallback callback);

    void uploadWithDefaultHost(String suffix, String filePath, RetrofitCallback callback);

    void uploadWithDefaultHost(String suffix, Map<String, String> map, String filePath, RetrofitCallback callback);

    void uploadWithDefaultHost(String suffix, Map<String, String> map, Map<String, String> fileMap, RetrofitCallback callback);

    void uploadWithDefaultHost(String suffix, Map<String, String> map, List<File> files, RetrofitCallback callback);

    void downloadWithDefaultHost(String suffix, RetrofitCallback callback);
}

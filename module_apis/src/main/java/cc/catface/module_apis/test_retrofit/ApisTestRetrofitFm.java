package cc.catface.module_apis.test_retrofit;

import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.base.utils.android.net.Utils.RequestUtils;
import cc.catface.base.utils.android.net.Utils.core.CustomObserver;
import cc.catface.base.utils.android.net.Utils.core.domain.TestData;
import cc.catface.base.utils.android.net.Utils.download.GoOnDownloadEngine;
import cc.catface.base.utils.android.net.Utils.download.SimpleDownloadEngine;
import cc.catface.base.utils.android.net.Utils.upload.ProgressRequestBody;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisFragmentTestRetrofitBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ApisTestRetrofitFm extends NormalFragment<ApisFragmentTestRetrofitBinding> {
    @Override public int layoutId() {
        return R.layout.apis_fragment_test_retrofit;
    }

    @Override protected void initAction() {
        mBinding.tvResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        mBinding.btGet.setOnClickListener(v -> RequestUtils.test_get(this, new CustomObserver<TestData>(mActivity) {
            @Override public void onSuccess(TestData result) {
                mBinding.tvResult.setText("test_get\r\n" + result.toString());
            }

            @Override public void onFailure(Throwable e, String errorMsg) {
                mBinding.tvResult.setText("请求失败-test_get\n" + e.toString() + "\r\n" + errorMsg);
            }
        }));

        mBinding.btGetParams.setOnClickListener(v -> RequestUtils.test_get(this, "gg-nn", "gg-pp", new CustomObserver<TestData>(mActivity) {
            @Override public void onSuccess(TestData result) {
                mBinding.tvResult.setText("test_get_\r\n" + result.toString());
            }

            @Override public void onFailure(Throwable e, String errorMsg) {
                mBinding.tvResult.setText("请求失败-test_get_\n" + e.toString() + "\r\n" + errorMsg);
            }
        }));

        mBinding.btPost.setOnClickListener(v -> RequestUtils.test_post(this, new CustomObserver<TestData>(mActivity) {
            @Override public void onSuccess(TestData result) {
                mBinding.tvResult.setText("test_post\r\n" + result.toString());
            }

            @Override public void onFailure(Throwable e, String errorMsg) {
                mBinding.tvResult.setText("请求失败-test_post\n" + e.toString() + "\r\n" + errorMsg);
            }
        }));

        mBinding.btPostParams.setOnClickListener(v -> RequestUtils.test_post(this, "pp-nn", "pp&-=pp", new CustomObserver<TestData>(mActivity) {
            @Override public void onSuccess(TestData result) {
                mBinding.tvResult.setText("test_post_\r\n" + result.toString());
            }

            @Override public void onFailure(Throwable e, String errorMsg) {
                mBinding.tvResult.setText("请求失败-test_post_\n" + e.toString() + "\r\n" + errorMsg);
            }
        }));

        mBinding.btPut.setOnClickListener(v -> RequestUtils.test_put(this, new CustomObserver<TestData>(mActivity) {
            @Override public void onSuccess(TestData result) {
                mBinding.tvResult.setText("test_put\r\n" + result.toString());
            }

            @Override public void onFailure(Throwable e, String errorMsg) {
                mBinding.tvResult.setText("请求失败-test_put\n" + e.toString() + "\r\n" + errorMsg);
            }
        }));

        mBinding.btPutParams.setOnClickListener(v -> RequestUtils.test_put(this, "pupu-nn", "pupu-pp", new CustomObserver<TestData>(mActivity) {
            @Override public void onSuccess(TestData result) {
                mBinding.tvResult.setText("test_put_\r\n" + result.toString());
            }

            @Override public void onFailure(Throwable e, String errorMsg) {
                mBinding.tvResult.setText("请求失败-test_put_\n" + e.toString() + "\r\n" + errorMsg);
            }
        }));

        mBinding.btDeleteParams.setOnClickListener(v -> RequestUtils.test_delete(this, 99, new CustomObserver<TestData>(mActivity) {
            @Override public void onSuccess(TestData result) {
                mBinding.tvResult.setText("test_delete_\r\n" + result.toString());
            }

            @Override public void onFailure(Throwable e, String errorMsg) {
                mBinding.tvResult.setText("请求失败-test_delete_\n" + e.toString() + "\r\n" + errorMsg);
            }
        }));

        String json = "{\"reqType\":0,\"perception\":{\"inputText\":{\"text\":\"附近的酒店\"},\"inputImage\":{\"url\":\"imageUrl\"},\"selfInfo\":{\"location\":{\"city\":\"北京\",\"province\":\"北京\",\"street\":\"信息路\"}}},\"userInfo\":{\"apiKey\":\"\",\"userId\":\"\"}}";
        mBinding.btJson.setOnClickListener(v -> RequestUtils.test_json(this, json, new CustomObserver<TestData>(mActivity) {
            @Override public void onSuccess(TestData result) {
                mBinding.tvResult.setText("test_json_\r\n" + result.toString());
            }

            @Override public void onFailure(Throwable e, String errorMsg) {
                mBinding.tvResult.setText("请求失败-test_json_\n" + e.toString() + "\r\n" + errorMsg);
            }
        }));

        mBinding.btDownload.setOnClickListener(v -> {
            RequestUtils.test_download("name_d", "pass_d", "sdcard/temp.exe", new SimpleDownloadEngine.Callback() {
                @Override public void onComplete() {
                    Toast.makeText(mActivity, "下载完成", Toast.LENGTH_SHORT).show();
                }

                @Override public void onProgress(float progress, long downloadedSize, long totalSize) {
                    mBinding.tvResult.setText("当前下载进度：" + progress + " || " + downloadedSize + "/" + totalSize);
                }

                @Override public void onFailure(String error) {
                    Toast.makeText(mActivity, "下载失败：" + error, Toast.LENGTH_SHORT).show();
                }
            });
        });


        Map<String, String> map = new HashMap<>();
        map.put("name", "value1");
        map.put("pass", "value2");
        File file = new File("sdcard/temp.exe");
        mBinding.btUpload.setOnClickListener(v -> {
            RequestUtils.test_upload(map, file, new ProgressRequestBody(file, new ProgressRequestBody.Callback() {
                @Override public void onComplete() {
                    Toast.makeText(mActivity, "上传完成", Toast.LENGTH_SHORT).show();
                }

                @Override public void onProgress(float progress, long uploadedSize, long totalSize) {
                    mBinding.tvResult.setText("上传进度：" + progress + " || " + uploadedSize + "/" + totalSize);
                }

                @Override public void onFailure(String error) {
                    Toast.makeText(mActivity, "上传失败：", Toast.LENGTH_SHORT).show();
                }
            }));
        });


        /**  */
        mBinding.btDownloadHttp.setOnClickListener(v -> {
            cc.catface.base.utils.android.net.http.point_download.DownloadEngine.getInstance().start("http://dldir1.qq.com/qqyy/pc/QQPlayerSetup4.1.3.658.exe", "d:/sasa", "temp.exe", new cc.catface.base.utils.android.net.http.point_download.DownloadEngine.Callback() {
                @Override
                public void onProgress(long length, long totalLength) {
                    TLog.d("正在下载： " + length / totalLength);
                }

                @Override
                public void onFailure(String error) {
                    TLog.d("下载失败：" + error);
                }

                @Override
                public void onSuccess() {
                    TLog.d("下载成功");
                }
            });
        });
    }

    String url = "http://dldir1.qq.com/qqmi/aphone_p2p/TencentVideo_V6.0.0.14297_848.apk";

    @Override public void createView() {
        mBinding.btGoOnDownload.setOnClickListener(v -> {
            GoOnDownloadEngine.getInstance().downloadFile(url, Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp_0", "temp.apk", new GoOnDownloadEngine.DownloadCallBack() {
                @Override public void onProgress(int progress) {
                    mBinding.tvResult.setText("正在下载: " + progress);
                }

                @Override public void onComplete() {
                    mBinding.tvResult.setText("下载完成");
                }

                @Override public void onError(String msg) {
                    mBinding.tvResult.setText("下载失败：" + msg);
                }

                @Override public void onExist() {
                    mBinding.tvResult.setText("当前文件已存在");
                }
            });
        });
    }
}

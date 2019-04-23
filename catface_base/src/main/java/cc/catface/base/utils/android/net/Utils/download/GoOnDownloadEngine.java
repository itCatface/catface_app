package cc.catface.base.utils.android.net.Utils.download;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

import cc.catface.base.utils.android.TSP;
import cc.catface.base.utils.android.net.Utils.core.RetrofitEngine;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class GoOnDownloadEngine {
    private final String TAG = GoOnDownloadEngine.class.getName();

    private static final class Holder {
        private static final GoOnDownloadEngine instance = new GoOnDownloadEngine();
    }

    public static GoOnDownloadEngine getInstance() {
        return Holder.instance;
    }


    /** 接口回调 */
    private DownloadCallBack mCallback;

    public interface DownloadCallBack {
        void onProgress(int progress);

        void onComplete();

        void onError(String msg);

        void onExist();
    }


    /** request params: 1. url 2. dir 3. filename 4. range[get with each enter] */
    private String mUrl;
    private String mStoreDir;
    private String mFileName;
    private long mRange;

    public void downloadFile(final String url, final String storeDir, final String fileName, final DownloadCallBack callBack) {
        this.mUrl = url;
        this.mStoreDir = storeDir + "/";
        this.mFileName = fileName;
        this.mCallback = callBack;

        if (isExist()) {
            mHandler.obtainMessage(mFlagExist).sendToTarget();
            return;
        }

        // request length when point download
        File file = new File(mStoreDir, fileName);
        String totalLength = "-";
        if (file.exists()) {
            totalLength += file.length();
        }

        RetrofitEngine.getNetApi().commonDownload(url, "bytes=" + Long.toString(mRange) + totalLength)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        RandomAccessFile randomAccessFile = null;
                        InputStream inputStream = null;
                        long total = mRange;
                        long responseLength = 0;
                        try {
                            byte[] buf = new byte[2048];
                            int len = 0;
                            responseLength = responseBody.contentLength();
                            inputStream = responseBody.byteStream();
                            String filePath = mStoreDir;
                            File file = new File(filePath, fileName);
                            File dir = new File(filePath);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }
                            randomAccessFile = new RandomAccessFile(file, "rwd");
                            if (mRange == 0) {
                                randomAccessFile.setLength(responseLength);
                            }
                            randomAccessFile.seek(mRange);

                            int progress = 0;
                            int lastProgress = 0;

                            while ((len = inputStream.read(buf)) != -1) {
                                randomAccessFile.write(buf, 0, len);
                                total += len;
                                TSP.getInstance().setLong(url, total);
                                lastProgress = progress;
                                progress = (int) (total * 100 / randomAccessFile.length());
                                if (progress > 0 && progress != lastProgress) {
                                    mProgress = progress;
                                    mHandler.obtainMessage(mFLagProgress).sendToTarget();
                                }
                            }

                            mHandler.obtainMessage(mFlagComplete).sendToTarget();
                        } catch (Exception e) {
                            mError = e.getMessage();
                            mHandler.obtainMessage(mFlagError).sendToTarget();
                        } finally {
                            try {
                                if (null != randomAccessFile) randomAccessFile.close();
                                if (null != inputStream) inputStream.close();
                            } catch (Exception e) {
                                mError = e.getMessage();
                                mHandler.obtainMessage(mFlagError).sendToTarget();
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mError = e.toString();
                        mHandler.obtainMessage(mFlagError).sendToTarget();
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    private boolean isExist() {
        boolean isExist = false;
        final File file = new File(mStoreDir, mFileName);
        mRange = 0;
        int progress = 0;
        if (file.exists()) {
            mRange = TSP.getInstance().getlong(mUrl, 0);
            progress = (int) (mRange * 100 / file.length());
            if (mRange == file.length()) {
                isExist = true;
            }
        }
        return isExist;
    }


    private int mProgress;
    private String mError;

    private final int mFlagComplete = 0x01;
    private final int mFlagError = 0x02;
    private final int mFLagProgress = 0x03;
    private final int mFlagExist = 0x04;
    @SuppressLint("HandlerLeak") private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            if (null == mCallback) return;
            switch (msg.what) {
                case mFlagComplete:
                    mCallback.onComplete();
                    break;
                case mFlagError:
                    mCallback.onError(mError);
                    break;
                case mFLagProgress:
                    mCallback.onProgress(mProgress);
                    break;
                case mFlagExist:
                    mCallback.onExist();
                    break;
            }
        }
    };
}

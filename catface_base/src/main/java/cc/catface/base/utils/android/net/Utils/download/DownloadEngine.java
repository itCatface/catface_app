package cc.catface.base.utils.android.net.Utils.download;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc simple download with progress
 */
public class DownloadEngine extends Thread {

    private final int FLAG_SUCCESS = 0x00;
    private final int FLAG_PROGRESS = 0x01;
    private final int FLAG_FAILURE = -0x99;
    @SuppressLint("HandlerLeak") private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            if (null == mCallback) return;
            switch (msg.what) {
                case FLAG_SUCCESS:
                    mCallback.onComplete();
                    break;
                case FLAG_PROGRESS:
                    mCallback.onProgress(mDownloadedSize / (mTotalSize + 0f), mDownloadedSize, mTotalSize);
                    break;
                case FLAG_FAILURE:
                    mCallback.onFailure(mErrorMessage);
                    break;
            }
        }
    };


    private ResponseBody mResponseBody;
    private String mSavedPath;
    private Callback mCallback;

    public DownloadEngine(Response<ResponseBody> response, String savedPath, Callback callback) {
        this.mResponseBody = response.body();
        this.mSavedPath = savedPath;
        this.mCallback = callback;
    }


    private long mDownloadedSize = 0L, mTotalSize = 0L;
    private String mErrorMessage = "未知异常";

    @Override public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            File tempFile = new File(mSavedPath);
            byte[] fileReader = new byte[4096];
            mTotalSize = mResponseBody.contentLength();
            is = mResponseBody.byteStream();
            os = new FileOutputStream(tempFile);
            while (true) {
                int read = is.read(fileReader);
                if (-1 == read) {
                    break;
                }
                os.write(fileReader, 0, read);
                mDownloadedSize += read;

                mHandler.obtainMessage(FLAG_PROGRESS).sendToTarget();
            }
            os.flush();
            mHandler.obtainMessage(FLAG_SUCCESS).sendToTarget();

        } catch (Exception e) {
            mErrorMessage = e.toString();
            mHandler.obtainMessage(FLAG_FAILURE).sendToTarget();

        } finally {
            try {
                if (null != os) os.close();
                if (null != is) is.close();
            } catch (IOException e) {
                mErrorMessage = e.toString();
                mHandler.obtainMessage(FLAG_FAILURE).sendToTarget();
            }
        }
    }


    public interface Callback {
        void onComplete();

        void onProgress(float progress, long downloadedSize, long totalSize);

        void onFailure(String error);
    }
}

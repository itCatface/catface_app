package cc.catface.base.utils.android.net.Utils.upload;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc simple upload with progress
 */
public class ProgressRequestBody extends RequestBody {

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
                    mCallback.onProgress(mUploadedSize / (mTotalSize + 0f), mUploadedSize, mTotalSize);
                    break;
                case FLAG_FAILURE:
                    mCallback.onFailure(mErrorMessage);
                    break;
            }
        }
    };


    private File mFile;
    private String mMediaType = "multipart/form-data";
    private Callback mCallback;


    public ProgressRequestBody(final File file, final Callback callback) {
        mFile = file;
        mCallback = callback;
    }

    public ProgressRequestBody(final File file, String mediaType, final Callback callback) {
        mFile = file;
        mMediaType = mediaType;
        mCallback = callback;
    }

    @Override
    public MediaType contentType() {
        return MediaType.parse(mMediaType);
    }


    private long mUploadedSize = 0L, mTotalSize = 0L;
    private String mErrorMessage = "未知异常";

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        mTotalSize = mFile.length();
        byte[] buffer = new byte[4096];
        FileInputStream in = new FileInputStream(mFile);
        try {
            int read;
            while ((read = in.read(buffer)) != -1) {
                mUploadedSize += read;
                sink.write(buffer, 0, read);

                mHandler.obtainMessage(FLAG_PROGRESS).sendToTarget();
            }
            mHandler.obtainMessage(FLAG_SUCCESS).sendToTarget();

        } catch (Exception e) {
            mErrorMessage = e.toString();
            mHandler.obtainMessage(FLAG_FAILURE).sendToTarget();

        } finally {
            if (null != in) in.close();
        }
    }

    public interface Callback {
        void onComplete();

        void onProgress(float progress, long uploadedSize, long totalSize);

        void onFailure(String error);
    }
}

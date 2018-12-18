package cc.catface.base.utils.android.net.http.point_download;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import cc.catface.base.utils.android.common_print.log.TLog;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 * 1. 断点续传:本地无文件从0开始下载 || 本地已有部分文件-->自动读取文件断点并从该点续传
 *
 * 2. 避免CV时引入过多文件依赖故未使用rx调度
 */
public class PointDownloadT {
    private final String TAG = PointDownloadT.class.getSimpleName();

    private static PointDownloadT mInstance;

    public static PointDownloadT getInstance() {
        if (null == mInstance) {
            synchronized (PointDownloadT.class) {
                if (null == mInstance) {
                    mInstance = new PointDownloadT();
                }
            }
        }

        return mInstance;
    }


    public interface Callback {
        void onProgress(long length, long totalLength);

        void onErr(String err);

        void onSuc();
    }


    private void download(String URL, long nPos, String file, Callback callback) {
        try {
            java.net.URL url = new URL(URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "NetFox");
            httpURLConnection.setRequestProperty("RANGE", "bytes=" + nPos + "-");
            InputStream inputStream = httpURLConnection.getInputStream();
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(nPos);
            byte[] b = new byte[4096];  // you can control download speed by modifying this bytes' size
            int len;
            while ((len = inputStream.read(b, 0, 4096)) > 0) {
                (randomAccessFile).write(b, 0, len);
                callback.onProgress(randomAccessFile.length(), mContentTotalSize);
                sout("总大小|已下载大小|比例-->[" + mContentTotalSize + "]-[" + randomAccessFile.length() + "]-[" + (randomAccessFile.length() * 100 / mContentTotalSize) + "]");
            }

            httpURLConnection.disconnect();
            sout("download-->httpURLConnection.disconnect();");
            if (randomAccessFile.length() < mContentTotalSize) {
                callback.onErr("下载中断且未完成");
            } else {
                callback.onSuc();
                sout("下载完成");
            }

        } catch (Exception e) {
            callback.onErr("download-->exception: " + e.toString());
        }
    }

    private long mContentTotalSize = 0;

    private long getRemoteFileSize(String url, Callback callback) {
        try {
            HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
            mContentTotalSize = conn.getContentLength();
            conn.disconnect();
        } catch (Exception e) {
            callback.onErr("getRemoteFileSize-->exception: " + e.toString());
        }
        return mContentTotalSize;
    }

    public void start(String url, String savePath, String fileName, Callback callback) {
        String fileNam = fileName;
        File file = new File(savePath + "/" + fileName);
        long remoteFileSize = getRemoteFileSize(url, callback);
        sout("远程文件大小: " + remoteFileSize);
        int i = 0;
        if (file.exists()) {
            long localFileSize = file.length();
            sout("本地已有文件大小: " + localFileSize);

            if (localFileSize < remoteFileSize) {
                sout("准备进入-->文件续传");
                download(url, localFileSize, savePath + "/" + fileName, callback);
            } else {
                sout("同名文件已存在且完整-->重复下载并自动更换文件名");
                do {
                    i++;
                    fileName = fileNam.substring(0, fileNam.indexOf(".")) + "(" + i + ")" + fileNam.substring(fileNam.indexOf("."));

                    file = new File(savePath + "/" + fileName);
                } while (file.exists());
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    callback.onErr("start-->file.createNewFile();-->exception: " + e.toString());
                }
                download(url, 0, savePath + "/" + fileName, callback);
            }

        } else {
            try {
                file.createNewFile();
                sout("start-->file.createNewFile()-->下载文件");
                download(url, 0, savePath + "/" + fileName, callback);
            } catch (IOException e) {
                callback.onErr("start-->exception: " + e.toString());
            }
        }
    }


    private void sout(String msg) {
        TLog.d(TAG, msg);
    }
}

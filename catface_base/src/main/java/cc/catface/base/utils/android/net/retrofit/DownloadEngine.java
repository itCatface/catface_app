package cc.catface.base.utils.android.net.retrofit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DownloadEngine {

    public static boolean response2ROM(ResponseBody body, String path, OnDownloadListener listener) {
        InputStream is = null;
        OutputStream os = null;

        try {
            File futureStudioIconFile = new File(path);

            byte[] fileReader = new byte[4096];

            long fileSize = body.contentLength();
            long fileSizeDownloaded = 0;

            is = body.byteStream();
            os = new FileOutputStream(futureStudioIconFile);

            while (true) {
                int read = is.read(fileReader);

                if (read == -1) {
                    break;
                }

                os.write(fileReader, 0, read);

                fileSizeDownloaded += read;

                listener.onProgress(fileSizeDownloaded / (fileSize + 0f));
            }

            os.flush();
            listener.onSuc();

            return true;


        } catch (Exception e) {
            listener.onErr(e.toString());
        } finally {
            try {
                if (null != os) os.close();
                if (null != is) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }


    public interface OnDownloadListener {
        void onSuc();

        void onProgress(Float progress);

        void onErr(String error);
    }
}

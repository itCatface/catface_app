package cc.catface.api.hardware.camera2;

import android.media.Image;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Saves a JPEG {@link Image} into the specified {@link File}.
 * 保存图片到自定目录
 * 保存jpeg到指定的文件夹下, 开启子线程执行保存操作
 */
public class ImageSaver implements Runnable {

    /**
     * The JPEG image
     * 要保存的图片
     */
    private final Image mImage;
    /**
     * The file we save the image into.
     * 图片存储的路径
     */
    private final File mFile;

    ImageSaver(Image image, File file) {
        mImage = image;
        mFile = file;
    }

    @Override public void run() {
        ByteBuffer buffer = mImage.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(mFile);
            output.write(bytes);

            Log.d("root_tag", "保存完成：" + mFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mImage.close();
            if (null != output) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
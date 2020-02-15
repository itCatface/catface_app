package cc.catface.ctool.system.sensor.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TPhoto {


    /* android 7.0系统解决拍照的问题 */
    public static void handle7Camera() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }


    /**
     * 相册
     */
    public static final int RC_CHOOSE_PHOTO = 2;

    public static void gallery(Activity activity) {
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intentToPickPic, RC_CHOOSE_PHOTO);
    }


    /**
     * 拍照
     */
    public static final int RC_TAKE_PHOTO = 1;

    public static String take(Activity activity) {
        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "catface_temp_photos" + File.separator);
        boolean mkdirs = dir.mkdirs();
        Intent intentToTakePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(dir, "take.jpg");
        Uri imageUri = FileProvider4Photo.getUriForFile(activity, file);
        intentToTakePhoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intentToTakePhoto, RC_TAKE_PHOTO);
        return file.getAbsolutePath();
    }


    /**
     * 裁剪
     */
    public static final int RC_CROP_PHOTO = 3;

    public static String crop(Uri uri, Activity activity) {
        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "catface_temp_photos" + File.separator);
        boolean mkdirs = dir.mkdirs();
        File file = new File(dir, System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        // 华为的裁剪页显示为圆形
        if (Build.MANUFACTURER.equals("HUAWEI")) {
            intent.putExtra("aspectX", 9998);
            intent.putExtra("aspectY", 9999);
        } else {
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
        }
        // outputX和outputY越大越清晰，过大会崩
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 800);
        intent.putExtra("return-data", false);
        Uri cropUri = Uri.parse("file://" + "/" + file.getAbsolutePath());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, RC_CROP_PHOTO);
        return file.getAbsolutePath();
    }

}

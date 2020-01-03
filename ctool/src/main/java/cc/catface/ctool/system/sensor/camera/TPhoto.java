package cc.catface.ctool.system.sensor.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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


    /** 相册 */
    public static final int RC_CHOOSE_PHOTO = 2;

    public static void gallery(Activity activity) {
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intentToPickPic, RC_CHOOSE_PHOTO);
    }


    /** 拍照 */
    public static final int RC_TAKE_PHOTO = 1;

    public static String take(Activity activity) {
        Intent intentToTakePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "tempPhotoDir" + File.separator);
        fileDir.mkdirs();
        File photoFile = new File(fileDir, "photo.jpg");
        Uri imageUri = FileProvider4Photo.getUriForFile(activity, photoFile);
        intentToTakePhoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intentToTakePhoto, RC_TAKE_PHOTO);
        return photoFile.getAbsolutePath();
    }

}

package cc.catface.api.hardware.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cc.catface.api.ApiHolderActivity;
import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityVibratorCameraBinding;
import cc.catface.api.hardware.qrcode.DemoScanQrcodeFm;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.ctool.system.sensor.TFlash;
import cc.catface.ctool.system.sensor.TVibrator;
import cc.catface.base.utils.android.view.ImageUtil;

import static android.app.Activity.RESULT_OK;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoHardwareFm extends NormalFragment<ApiActivityVibratorCameraBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_vibrator_camera;
    }

    @Override protected void initAction() {
        mBinding.btVibrateOnce.setOnClickListener(v -> TVibrator.play(mActivity, 2_000));
        mBinding.btVibrateRepeat.setOnClickListener(v -> TVibrator.play(mActivity, new long[]{500, 1_000, 1_000, 500}, 2));
        mBinding.btVibrateCancel.setOnClickListener(v -> TVibrator.cancel(mActivity));
        mBinding.btFlashOpen.setOnClickListener(v -> TFlash.get(mActivity).open());
        mBinding.btFlashClose.setOnClickListener(v -> TFlash.get(mActivity).close());
        mBinding.btScan.setOnClickListener(v ->  ((ApiHolderActivity)mActivity).replace(new DemoScanQrcodeFm()));

        mBinding.btTakePictureJust.setOnClickListener(v -> takePictureJust());
        mBinding.btTakePictureWithCrop.setOnClickListener(v -> takePictureWithCrop());
        mBinding.btChoosePicture.setOnClickListener(v -> chosePicture());
        mBinding.btWater.setOnClickListener(v -> addWaterMask2Picture());
    }

    public static final int TAKE_PICTURE_JUST = 0;
    public static final int TAKE_PICTURE_WITH_CROP = 1;
    public static final int CROP_PICTURE = 2;
    private Uri imageUri; // 完整的file路径: ///图片地址

    @Override public void createView() {
    }

    private final String picPath = Environment.getExternalStorageDirectory() + "/pic_take_" + System.currentTimeMillis() + ".jpg";

    private void takePictureJust() {
        //        File file = new File(picPath);
        //        try {
        //            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        //            file.createNewFile();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        //        imageUri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PICTURE_JUST);
    }

    private void takePictureWithCrop() {
        File file = new File(picPath);
        try {
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PICTURE_WITH_CROP); // 启动裁剪
    }


    private void chosePicture() {
        File file = new File(picPath);
        try {
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("exexex-->存储照片时发生异常: " + e.toString());
        }
        imageUri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_PICK); // 拿到本地已存在资源
        intent.setType("image/*");
        intent.putExtra("crop", true);
        intent.putExtra("api_scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PICTURE_WITH_CROP); // 启动裁剪
    }

    private void addWaterMask2Picture() {
        if (!new File(picPath).exists()) {
            Toast.makeText(mActivity, "当前无可加水印的图片...", Toast.LENGTH_SHORT).show();
            return;
        }

        Bitmap sourBitmap = BitmapFactory.decodeFile(picPath);
        Bitmap waterBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Bitmap watermarkBitmap = ImageUtil.createWaterMaskCenter(sourBitmap, waterBitmap);
        watermarkBitmap = ImageUtil.createWaterMaskLeftBottom(mActivity, watermarkBitmap, waterBitmap, 0, 0);
        watermarkBitmap = ImageUtil.createWaterMaskRightBottom(mActivity, watermarkBitmap, waterBitmap, 0, 0);
        watermarkBitmap = ImageUtil.createWaterMaskLeftTop(mActivity, watermarkBitmap, waterBitmap, 0, 0);
        watermarkBitmap = ImageUtil.createWaterMaskRightTop(mActivity, watermarkBitmap, waterBitmap, 0, 0);

        Bitmap textBitmap = ImageUtil.drawTextToLeftTop(mActivity, watermarkBitmap, "左上角", 16, Color.RED, 0, 0);
        textBitmap = ImageUtil.drawTextToRightBottom(mActivity, textBitmap, "右下角", 16, Color.RED, 0, 0);
        textBitmap = ImageUtil.drawTextToRightTop(mActivity, textBitmap, "右上角", 16, Color.RED, 0, 0);
        textBitmap = ImageUtil.drawTextToLeftBottom(mActivity, textBitmap, "左下角", 16, Color.RED, 0, 0);
        textBitmap = ImageUtil.drawTextToCenter(mActivity, textBitmap, "中间", 16, Color.RED);

        mBinding.ivPicture.setImageBitmap(textBitmap);
    }

    private void scanPhoto(File file) {
        Uri contentUri = Uri.fromFile(file);
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, contentUri);
        //        mediaScanIntent.setData(contentUri);
        mActivity.sendBroadcast(mediaScanIntent);

    }


    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE_JUST:
                try {
                    //                    scanPhoto(new File(picPath));
                    //                    ContentValues values = new ContentValues();
                    //                    ContentResolver resolver = mActivity.getContentResolver();
                    //                    values.put(MediaStore.Images.ImageColumns.DATA, picPath);
                    //                    values.put(MediaStore.Images.ImageColumns.TITLE, picPath.substring(picPath.lastIndexOf("/") + 1));
                    //                    values.put(MediaStore.Images.ImageColumns.DATE_TAKEN, System.currentTimeMillis());
                    //                    values.put(MediaStore.Images.ImageColumns.MIME_TYPE, "image/jpeg");
                    //                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


                    //                    TLog.d("exexex-->" + new File(picPath).exists());
                    //                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    //
                    //                    /********************************
                    //                     图片可做压缩处理，防止OOM
                    //                     ********************************/
                    //                    iv_picture.setImageBitmap(bitmap); // 将裁剪后的照片显示出来

                    //                    iv_picture.setImageURI(Uri.fromFile(new File(picPath)));


                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    FileOutputStream b = null;
                    try {
                        b = new FileOutputStream(picPath);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
                    } catch (FileNotFoundException e) { e.printStackTrace(); } finally {
                        try {
                            b.flush();
                            b.close();
                        } catch (IOException e) { e.printStackTrace(); }
                    }
                    mBinding.ivPicture.setImageBitmap(bitmap);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case TAKE_PICTURE_WITH_CROP:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        imageUri = data.getData();
                    }
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("api_scale", true);
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("outputX", 340);
                    intent.putExtra("outputY", 340);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PICTURE); // 启动裁剪程序
                }
                break;
            case CROP_PICTURE:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(mActivity.getContentResolver().openInputStream(imageUri));

                        /********************************
                         图片可做压缩处理，防止OOM
                         ********************************/
                        mBinding.ivPicture.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}

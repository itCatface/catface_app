package cc.catface.ctool.context;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TFlash {
    private boolean isFlashAvailable;
    private Camera mCamera;
    private CameraManager mCameraManager;
    private String mCameraId;


    private volatile static TFlash mInstance;

    public static TFlash get() {
        if (null == mInstance) {
            synchronized (TFlash.class) {
                if (null == mInstance) {
                    mInstance = new TFlash();
                }
            }
        }

        return mInstance;
    }

    private TFlash() {
        isFlashAvailable = TContext.getContext().getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mCameraManager = (CameraManager) TContext.getContext().getApplicationContext().getSystemService(Context.CAMERA_SERVICE);
            try {
                if (mCameraManager != null) {
                    mCameraId = mCameraManager.getCameraIdList()[0];
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /** 开启闪光灯 */
    public void open() {
        if (!isFlashAvailable) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                mCameraManager.setTorchMode(mCameraId, true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                mCamera = Camera.open();
                mCamera.startPreview();
                Camera.Parameters parameters = mCamera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                mCamera.setParameters(parameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /** 关闭闪光灯 */
    public void close() {
        if (!isFlashAvailable) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                mCameraManager.setTorchMode(mCameraId, false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (mCamera != null) {
                    Camera.Parameters parameters = mCamera.getParameters();
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    mCamera.setParameters(parameters);
                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
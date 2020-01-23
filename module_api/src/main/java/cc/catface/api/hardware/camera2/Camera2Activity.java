package cc.catface.api.hardware.camera2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityCamera2Binding;

/**
 * @author Marlon
 * @desc Camera2Activity 基于Camera2 API 自定义相机
 * @date 2018/6/13
 */
public class Camera2Activity extends AppCompatActivity {
    private ApiActivityCamera2Binding mBinding;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.api_activity_camera2);

        mBinding.btTake.setOnClickListener(v -> {
            captureStillPicture();
        });


        initView();
    }

    private void initView() {
        mBinding.s.setOnCheckedChangeListener((buttonView, isChecked) -> {
            closeCamera();
            if (!isChecked) {
                //后置摄像头
                mCameraId = CameraCharacteristics.LENS_FACING_FRONT;
                if (mBinding.tvCamera2.isAvailable()) {
                    openCamera(mBinding.tvCamera2.getWidth(), mBinding.tvCamera2.getHeight(), mCameraId);
                } else {
                    mBinding.tvCamera2.setSurfaceTextureListener(textureListener);
                }
            } else {
                //前置摄像头
                mCameraId = CameraCharacteristics.LENS_FACING_BACK;
                if (mBinding.tvCamera2.isAvailable()) {
                    openCamera(mBinding.tvCamera2.getWidth(), mBinding.tvCamera2.getHeight(), mCameraId);
                } else {
                    mBinding.tvCamera2.setSurfaceTextureListener(textureListener);
                }
            }
        });

        // 获取CameraManager 相机设备管理器
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);




    }







    /*** 相机管理类*/
    CameraManager mCameraManager;

    /*** 指定摄像头ID对应的Camera实体对象*/
    CameraDevice mCameraDevice;


    /**
     * 预览尺寸
     */
    private Size mPreviewSize;
    private int mSurfaceWidth;
    private int mSurfaceHeight;

    /*** 打开摄像头的ID{@link CameraDevice}.*/
    private int mCameraId = CameraCharacteristics.LENS_FACING_FRONT;

    /*** 处理静态图像捕获的ImageReader。{@link ImageReader}*/
    private ImageReader mImageReader;

    /*** 用于相机预览的{@Link CameraCaptureSession}。*/
    private CameraCaptureSession mCaptureSession;

    /*** {@link CaptureRequest.Builder}用于相机预览请求的构造器*/
    private CaptureRequest.Builder mPreviewRequestBuilder;

    /***预览请求, 由上面的构建器构建出来*/
    private CaptureRequest mPreviewRequest;
    /**
     * 从屏幕旋转图片转换方向。
     */
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    /***判断是否支持闪关灯*/
    private boolean mFlashSupported;

    /*** 用于运行不应阻塞UI的任务的附加线程。*/
    private HandlerThread mBackgroundThread;

    /*** 用于在后台运行任务的{@link Handler}。*/
    private Handler mBackgroundHandler;
    /**
     * 预览请求构建器, 用来构建"预览请求"(下面定义的)通过pipeline发送到Camera device
     * 这是{@link ImageReader}的回调对象。 当静止图像准备保存时，将会调用“onImageAvailable”。
     */
    private final ImageReader.OnImageAvailableListener mOnImageAvailableListener = new ImageReader.OnImageAvailableListener() {
        @Override public void onImageAvailable(ImageReader reader) {
          File  mFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg");
            mBackgroundHandler.post(new ImageSaver(reader.acquireNextImage(), mFile));
        }

    };

    /*** {@link CameraDevice.StateCallback}打开指定摄像头回调{@link CameraDevice}*/
    private final CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {

        @Override public void onOpened(@NonNull CameraDevice cameraDevice) {
            mCameraDevice = cameraDevice;
            createCameraPreview();
        }

        @Override public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            cameraDevice.close();
            cameraDevice = null;
        }

        @Override public void onError(@NonNull CameraDevice cameraDevice, int error) {
            cameraDevice.close();
            cameraDevice = null;
        }

    };

    /**
     * TextureView 生命周期响应
     */
    private final TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override //创建
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            //当TextureView创建完成，打开指定摄像头相机
            openCamera(width, height, mCameraId);
        }

        @Override //尺寸改变
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) { }

        @Override //销毁
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override //更新
        public void onSurfaceTextureUpdated(SurfaceTexture surface) { }
    };
    private int CONTROL_AE_MODE;


    /**
     * 打开指定摄像头ID的相机
     *
     * @param width
     * @param height
     * @param cameraId
     */
    private void openCamera(int width, int height, int cameraId) {
        if (ActivityCompat.checkSelfPermission(Camera2Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        try {
            mSurfaceWidth = width;
            mSurfaceHeight = height;
            //            getCameraId(cameraId);
            CameraCharacteristics characteristics = mCameraManager.getCameraCharacteristics(mCameraId + "");
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            // 获取设备方向
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            int totalRotation = sensorToDeviceRotation(characteristics, rotation);
            boolean swapRotation = totalRotation == 90 || totalRotation == 270;
            int rotatedWidth = mSurfaceWidth;
            int rotatedHeight = mSurfaceHeight;
            if (swapRotation) {
                rotatedWidth = mSurfaceHeight;
                rotatedHeight = mSurfaceWidth;
            }
            // 获取最佳的预览尺寸
            mPreviewSize = getPreferredPreviewSize(map.getOutputSizes(SurfaceTexture.class), rotatedWidth, rotatedHeight);
            if (swapRotation) {
                mBinding.tvCamera2.setAspectRatio(mPreviewSize.getWidth(), mPreviewSize.getHeight());
            } else {
                mBinding.tvCamera2.setAspectRatio(mPreviewSize.getHeight(), mPreviewSize.getWidth());
            }

            if (mImageReader == null) {
                // 创建一个ImageReader对象，用于获取摄像头的图像数据,maxImages是ImageReader一次可以访问的最大图片数量
                mImageReader = ImageReader.newInstance(mPreviewSize.getWidth(), mPreviewSize.getHeight(), ImageFormat.JPEG, 2);
                mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, mBackgroundHandler);
            }
            //检查是否支持闪光灯
            Boolean available = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
            mFlashSupported = available == null ? false : available;
            mCameraManager.openCamera(mCameraId + "", mStateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置相机闪关灯模式
     *
     * @param AE_MODE 闪关灯的模式
     * @throws CameraAccessException
     */
    private void setFlashMode(int AE_MODE) {
        if (mFlashSupported) {
            this.CONTROL_AE_MODE = AE_MODE;
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, AE_MODE);
            if (AE_MODE == CaptureRequest.CONTROL_AE_MODE_OFF) {
                mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, CaptureRequest.FLASH_MODE_OFF);
            }
        }

        // 构建上述的请求
        mPreviewRequest = mPreviewRequestBuilder.build();
        // 重复进行上面构建的请求, 用于显示预览
        try {
            mCaptureSession.setRepeatingRequest(mPreviewRequest, null, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建预览对话
     */
    private void createCameraPreview() {
        try {
            // 获取texture实例
            SurfaceTexture surfaceTexture = mBinding.tvCamera2.getSurfaceTexture();
            assert surfaceTexture != null;
            //我们将默认缓冲区的大小配置为我们想要的相机预览的大小。
            surfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
            // 用来开始预览的输出surface
            Surface surface = new Surface(surfaceTexture);
            //创建预览请求构建器
            mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            //将TextureView的Surface作为相机的预览显示输出
            mPreviewRequestBuilder.addTarget(surface);
            //在这里，我们为相机预览创建一个CameraCaptureSession。
            mCameraDevice.createCaptureSession(Arrays.asList(surface, mImageReader.getSurface()), new CameraCaptureSession.StateCallback() {

                @Override public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    // 相机关闭时, 直接返回
                    if (null == mCameraDevice) {
                        return;
                    }
                    //会话准备就绪后，我们开始显示预览。
                    // 会话可行时, 将构建的会话赋给field
                    mCaptureSession = cameraCaptureSession;

                    //相机预览应该连续自动对焦。
                    mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                    //设置闪关灯模式
                    setFlashMode(CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                }

                @Override public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Log.d("root_tag", "预览失败");
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拍照时调用方法
     */
    private void captureStillPicture() {
        try {
            if (mCameraDevice == null) {
                return;
            }
            // 创建作为拍照的CaptureRequest.Builder
            mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            // 将imageReader的surface作为CaptureRequest.Builder的目标
            mPreviewRequestBuilder.addTarget(mImageReader.getSurface());
/*            // 设置自动对焦模式
            mBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                    CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            // 设置自动曝光模式
            mBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                    CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);*/
            //设置为自动模式
            //            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);

            setFlashMode(CONTROL_AE_MODE);
            // 停止连续取景
            mCaptureSession.stopRepeating();
            // 捕获静态图像
            mCaptureSession.capture(mPreviewRequestBuilder.build(), new CameraCaptureSession.CaptureCallback() {
                // 拍照完成时激发该方法
                @Override public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                    //重新打开预览
                    createCameraPreview();
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取设备方向
     *
     * @param characteristics
     * @param deviceOrientation
     * @return
     */
    private static int sensorToDeviceRotation(CameraCharacteristics characteristics, int deviceOrientation) {
        int sensorOrientation = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        deviceOrientation = ORIENTATIONS.get(deviceOrientation);
        return (sensorOrientation + deviceOrientation + 360) % 360;
    }

    /**
     * 设置最佳尺寸
     */
    private Size getPreferredPreviewSize(Size[] sizes, int width, int height) {
        List<Size> collectorSizes = new ArrayList<>();
        for (Size option : sizes) {
            if (width > height) {
                if (option.getWidth() > width && option.getHeight() > height) {
                    collectorSizes.add(option);
                }
            } else {
                if (option.getHeight() > width && option.getWidth() > height) {
                    collectorSizes.add(option);
                }
            }
        }
        if (collectorSizes.size() > 0) {
            return Collections.min(collectorSizes, new Comparator<Size>() {
                @Override public int compare(Size s1, Size s2) {
                    return Long.signum(s1.getWidth() * s1.getHeight() - s2.getWidth() * s2.getHeight());
                }
            });
        }
        return sizes[0];
    }


    /**
     * 初试化拍照线程
     */
    public void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("Camera Background");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    public void stopBackgroundThread() {
        if (mBackgroundThread != null) {
            mBackgroundThread.quitSafely();
            try {
                mBackgroundThread.join();
                mBackgroundThread = null;
                mBackgroundHandler = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes the current {@link CameraDevice}.
     * 关闭正在使用的相机
     */
    private void closeCamera() {
        // 关闭捕获会话
        if (null != mCaptureSession) {
            mCaptureSession.close();
            mCaptureSession = null;
        }
        // 关闭当前相机
        if (null != mCameraDevice) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
        // 关闭拍照处理器
        if (null != mImageReader) {
            mImageReader.close();
            mImageReader = null;
        }
    }

    @Override protected void onResume() {
        super.onResume();
        if (mBinding.tvCamera2.isAvailable()) {
            openCamera(mBinding.tvCamera2.getWidth(), mBinding.tvCamera2.getHeight(), mCameraId);
        } else {
            mBinding.tvCamera2.setSurfaceTextureListener(textureListener);
        }
        startBackgroundThread();
    }

    @Override protected void onPause() {
        closeCamera();
        stopBackgroundThread();
        super.onPause();
    }
}

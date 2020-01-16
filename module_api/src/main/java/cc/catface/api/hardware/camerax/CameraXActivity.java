package cc.catface.api.hardware.camerax;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Environment;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysisConfig;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.OptionsBundle;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import java.io.File;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityCameraxBinding;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;


//saus: https://codelabs.developers.google.com/codelabs/camerax-getting-started/
/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * TODO camerax示例，因处于alpha版本，api变动较大，当前仅可用，其他待更新
 */
public class CameraXActivity extends LightAct<LightPresenter, ApiActivityCameraxBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_camerax;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) @Override protected void created() {
            startCamera(); //start camera if permission has been granted by user
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) @SuppressLint("RestrictedApi") private void startCamera() {
        //make sure there isn't another camera instance running before starting
        CameraX.unbindAll();

        /* start preview */
        int aspRatioW = mBinding.tvCamerax.getWidth(); //get width of screen
        int aspRatioH = mBinding.tvCamerax.getHeight(); //get height
        Rational asp = new Rational(aspRatioW, aspRatioH); //aspect ratio
        Size screen = new Size(aspRatioW, aspRatioH); //size of the screen

        //config obj for preview/viewfinder thingy.
        PreviewConfig pConfig = new PreviewConfig.Builder().setTargetAspectRatio(asp).setTargetResolution(screen).build();
        Preview preview = new Preview(pConfig); //lets build it

        preview.setOnPreviewOutputUpdateListener(new Preview.OnPreviewOutputUpdateListener() {
            //to update the surface texture we have to destroy it first, then re-add it
            @Override public void onUpdated(Preview.PreviewOutput output) {
                ViewGroup parent = (ViewGroup) mBinding.tvCamerax.getParent();
                parent.removeView(mBinding.tvCamerax);
                parent.addView(mBinding.tvCamerax, 0);

                mBinding.tvCamerax.setSurfaceTexture(output.getSurfaceTexture());
                updateTransform();
            }
        });

        /* image capture */

        //config obj, selected capture mode
        ImageCaptureConfig imgCapConfig = new ImageCaptureConfig.Builder().setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY).setTargetRotation(getWindowManager().getDefaultDisplay().getRotation()).build();
        final ImageCapture imgCap = new ImageCapture(imgCapConfig);

        findViewById(R.id.btTake).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".jpg");
                imgCap.takePicture(file, new ImageCapture.OnImageSavedListener() {
                    @Override public void onImageSaved(@NonNull File file) {
                        String msg = "Photo capture succeeded: " + file.getAbsolutePath();
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    }

                    @Override public void onError(@NonNull ImageCapture.UseCaseError useCaseError, @NonNull String message, @Nullable Throwable cause) {
                        String msg = "Photo capture failed: " + message;
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        if (cause != null) {
                            cause.printStackTrace();
                        }
                    }
                });
            }
        });

        /* image analyser */

        ImageAnalysisConfig imgAConfig = new ImageAnalysisConfig.Builder().setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE).build();
        ImageAnalysis analysis = new ImageAnalysis(imgAConfig);

        analysis.setAnalyzer(new ImageAnalysis.Analyzer() {
            @Override public void analyze(ImageProxy image, int rotationDegrees) {
                //y'all can add code to analyse stuff here idek go wild.
            }
        });

        //bind to lifecycle:
        CameraX.bindToLifecycle((LifecycleOwner) this, analysis, imgCap, preview);
    }

    private void updateTransform() {
        /*
         * compensates the changes in orientation for the viewfinder, bc the rest of the layout stays in portrait mode.
         * methinks :thonk:
         * imgCap does this already, this class can be commented out or be used to optimise the preview
         */
        Matrix mx = new Matrix();
        float w = mBinding.tvCamerax.getMeasuredWidth();
        float h = mBinding.tvCamerax.getMeasuredHeight();

        float centreX = w / 2f; //calc centre of the viewfinder
        float centreY = h / 2f;

        int rotationDgr;
        int rotation = (int) mBinding.tvCamerax.getRotation(); //cast to int bc switches don't like floats

        switch (rotation) { //correct output to account for display rotation
            case Surface.ROTATION_0:
                rotationDgr = 0;
                break;
            case Surface.ROTATION_90:
                rotationDgr = 90;
                break;
            case Surface.ROTATION_180:
                rotationDgr = 180;
                break;
            case Surface.ROTATION_270:
                rotationDgr = 270;
                break;
            default:
                return;
        }

        mx.postRotate((float) rotationDgr, centreX, centreY);
        mBinding.tvCamerax.setTransform(mx); //apply transformations to textureview
    }
}

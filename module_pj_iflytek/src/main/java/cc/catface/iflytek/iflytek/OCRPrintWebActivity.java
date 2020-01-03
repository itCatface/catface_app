package cc.catface.iflytek.iflytek;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import cc.catface.base.utils.android.view.BitmapUtil;
import cc.catface.ctool.system.sensor.camera.TFile4Photo;
import cc.catface.ctool.system.sensor.camera.TPhoto;
import cc.catface.iflytek.R;
import cc.catface.iflytek.databinding.ApisIflytekOcrPrintWebBinding;
import cc.catface.iflytek.domain.OcrResult;
import cc.catface.iflytek.tools.ocr.WebOCR;

public class OCRPrintWebActivity extends AppCompatActivity {
    private ApisIflytekOcrPrintWebBinding mBinding;

    String result = "";
    String json = "";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.apis_iflytek_ocr_print_web);

        /* 权限申请 */
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x0010);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        /* 拍照&相册 */
        mBinding.btCamera.setOnClickListener(v -> {
            mPath = TPhoto.take(this);
        });
        mBinding.btGallery.setOnClickListener(v -> {
            TPhoto.gallery(this);
        });

        /* OCR识别 */
        findViewById(R.id.btOCR).setOnClickListener(v -> {
            mBinding.btOCR.setText("OCR[识别中，请稍等]");
            Bitmap bitmap = BitmapFactory.decodeFile(mPath);
            bitmap.copyPixelsToBuffer(ByteBuffer.allocate(bitmap.getByteCount()));
            new Thread(() -> {
                result = "";
                try {
                    byte[] bytes = BitmapUtil.bitmap2Byte(bitmap);
                    json = WebOCR.request(bytes);
                    runOnUiThread(() -> {
                        mBinding.btOCR.setText("OCR[识别完成]");
                        mBinding.tvOCR.setText(json);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        });

        /* 解析结果 */
        findViewById(R.id.btGson).setOnClickListener(v -> {
            result = "";
            try {
                OcrResult ocrResult = new Gson().fromJson(json, OcrResult.class);
                List<OcrResult.Data.Block> blocks = ocrResult.getData().getBlock();
                for (OcrResult.Data.Block block : blocks) {
                    List<OcrResult.Data.Block.Line> lines = block.getLine();
                    for (OcrResult.Data.Block.Line line : lines) {
                        List<OcrResult.Data.Block.Line.Word> words = line.getWord();
                        for (OcrResult.Data.Block.Line.Word word : words) {
                            result += word.getContent();
                        }
                    }
                }

                runOnUiThread(() -> mBinding.tvParse.setText(result));
            } catch (Exception e) {
                runOnUiThread(() -> mBinding.tvParse.setText("gson解析异常"));
            }
        });
    }


    private String mPath;

    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case TPhoto.RC_TAKE_PHOTO:
                RequestOptions requestOptions = new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
                Glide.with(this).load(mPath).apply(requestOptions).into(mBinding.iv);
                break;
            case TPhoto.RC_CHOOSE_PHOTO:
                Uri uri = data.getData();
                String filePath = TFile4Photo.getFilePathByUri(this, uri);
                mPath = filePath;
                if (!TextUtils.isEmpty(filePath)) {
                    RequestOptions requestOptions1 = new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
                    Glide.with(this).load(filePath).apply(requestOptions1).into(mBinding.iv);
                }
                break;
        }
    }
}
package cc.catface.iflytek.iflytek;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import cc.catface.iflytek.R;
import cc.catface.iflytek.databinding.ApisIflytekOcrPrintWebBinding;
import cc.catface.iflytek.domain.OcrResult;
import cc.catface.iflytek.tools.CameraGalleryUtil;
import cc.catface.iflytek.tools.ocr.WebOCR;

public class OCRPrintWebActivity extends AppCompatActivity implements View.OnClickListener {

    private ApisIflytekOcrPrintWebBinding mBinding;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.apis_iflytek_ocr_print_web);
        mBinding.btnCamera.setOnClickListener(this);
        mBinding.btnGallery.setOnClickListener(this);
        mBinding.btnRequest.setOnClickListener(this);
    }

    private Bitmap mBitmap;

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = CameraGalleryUtil.getBitmapFromCG(this, requestCode, resultCode, data);
        mBinding.iv.setImageBitmap(bitmap);
        mBitmap = bitmap;
    }

    private String result = "";

    @Override public void onClick(View view) {
        int id = view.getId();
        Intent intent;
        if (id == R.id.btnCamera) {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, CameraGalleryUtil.fileUri);
            startActivityForResult(intent, CameraGalleryUtil.PHOTO_REQUEST_TAKE_PHOTO);
        } else if (id == R.id.btnGallery) {
            intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, CameraGalleryUtil.PHOTO_REQUEST_GALLERY);

        } else if (id == R.id.btnRequest) {
            if (null == mBitmap) return;
            result = "";
            new Thread(() -> {
                String json = null;
                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] datas = baos.toByteArray();
                    json = WebOCR.request(datas);
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

                runOnUiThread(() -> mBinding.tvResult.setText(result));
            }).start();
        }
    }
}
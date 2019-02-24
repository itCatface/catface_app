package cc.catface.api.hardware.qrcode;

import android.view.View;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityScanQrcodeBinding;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cn.bingoogolapple.qrcode.core.QRCodeView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ScanQrcodeActivity extends NormalActivity<ApiActivityScanQrcodeBinding> implements View.OnClickListener, QRCodeView.Delegate {
    @Override public int layoutId() {
        return R.layout.api_activity_scan_qrcode;
    }

    @Override protected void initAction() {
        mBinding.btIncessantScan.setOnClickListener(this);
        mBinding.btFlash.setOnClickListener(this);

        mBinding.zxv.setDelegate(this);
    }

    private boolean isIncessantScan = false, isFlashOn = false;

    @Override public void onClick(View view) {
        if (R.id.bt_incessant_scan == view.getId()) {
            isIncessantScan = !isIncessantScan;
            mBinding.btIncessantScan.setText(isIncessantScan ? "已关闭连续扫描" : "已开启连续扫描");
            if (isIncessantScan) mBinding.zxv.startSpot();
        } else if (R.id.bt_flash == view.getId()) {
            isFlashOn = !isFlashOn;
            if (isFlashOn) mBinding.zxv.openFlashlight();
            else mBinding.zxv.closeFlashlight();
            mBinding.btFlash.setText(isFlashOn ? "闪光灯已开启" : "闪光灯已关闭");
        }
    }

    @Override protected void onStart() {
        super.onStart();
        mBinding.zxv.startSpot();
    }

    @Override public void create() { }

    @Override protected void onStop() {
        super.onStop();
        mBinding.zxv.stopCamera();
        mBinding.zxv.stopSpot();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mBinding.zxv.onDestroy();
    }

    @Override public void onScanQRCodeSuccess(String result) {
        if (isIncessantScan) mBinding.zxv.startSpot();
        mBinding.tvResult.setText(result);
    }

    @Override public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override public void onScanQRCodeOpenCameraError() {

    }
}

package cc.catface.api.hardware.qrcode;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cc.catface.api.R;
import cc.catface.base.core_framework.base_normal.NormalBaseActivity;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ScanQrcodeActivity extends NormalBaseActivity implements View.OnClickListener, QRCodeView.Delegate {
    @Override public int layoutId() {
        return R.layout.api_activity_scan_qrcode;
    }

    private ZXingView zxv;
    private TextView tv_result;
    private Button bt_incessant_scan;
    private Button bt_flash;
    private boolean isIncessantScan = false, isFlashOn = false;

    @Override public void onClick(View view) {
        if (R.id.bt_incessant_scan == view.getId()) {
            isIncessantScan = !isIncessantScan;
            bt_incessant_scan.setText(isIncessantScan ? "已关闭连续扫描" : "已开启连续扫描");
            if (isIncessantScan) zxv.startSpot();
        } else if (R.id.bt_flash == view.getId()) {
            isFlashOn = !isFlashOn;
            if (isFlashOn) zxv.openFlashlight();
            else zxv.closeFlashlight();
            bt_flash.setText(isFlashOn ? "闪光灯已开启" : "闪光灯已关闭");
        }
    }

    @Override protected void onStart() {
        super.onStart();
        zxv.startSpot();
    }

    @Override public void create() {
        zxv = (ZXingView) findViewById(R.id.zxv);
        tv_result = (TextView) findViewById(R.id.tv_result);
        bt_incessant_scan = (Button) findViewById(R.id.bt_incessant_scan);
        bt_flash = (Button) findViewById(R.id.bt_flash);
        bt_incessant_scan.setOnClickListener(this);
        bt_flash.setOnClickListener(this);

        zxv.setDelegate(this);
    }

    @Override protected void onStop() {
        super.onStop();
        zxv.stopCamera();
        zxv.stopSpot();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        zxv.onDestroy();
    }

    @Override public void onScanQRCodeSuccess(String result) {
        if (isIncessantScan) zxv.startSpot();
        tv_result.setText(result);
    }

    @Override public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override public void onScanQRCodeOpenCameraError() {

    }
}

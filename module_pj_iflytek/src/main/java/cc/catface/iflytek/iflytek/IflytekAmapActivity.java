package cc.catface.iflytek.iflytek;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.amap.api.location.AMapLocation;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;

import java.util.ArrayList;

import cc.catface.iflytek.R;
import cc.catface.iflytek.service.AmapEngine;

public class IflytekAmapActivity extends AppCompatActivity {

    private Button btFinish, btStartLoc, btStopLoc;
    private CheckBox cbIsOnce;
    private TextView tvLoc, tvPoi;

    @Override protected void onDestroy() {
        super.onDestroy();
        mAmapEngine.release();
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apis_iflytek_activity_amap);

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.LOCATION_HARDWARE}, 0x0010);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btFinish = (Button) findViewById(R.id.btFinish);
        btStartLoc = (Button) findViewById(R.id.btStartLoc);
        btStopLoc = (Button) findViewById(R.id.btStopLoc);
        cbIsOnce = (CheckBox) findViewById(R.id.cbIsOnce);
        tvLoc = (TextView) findViewById(R.id.tvLoc);
        tvPoi = (TextView) findViewById(R.id.tvPoi);

        btFinish.setOnClickListener(v -> finish());
        initAmap();
        btStartLoc.setOnClickListener(v -> {
            btStartLoc.setText("再次定位前请先停止定位");
            mAmapEngine.startLocation();
        });
        btStopLoc.setOnClickListener(v -> {
            btStartLoc.setText("开始定位");
            mAmapEngine.stopLocation();
        });
    }

    private AmapEngine mAmapEngine;

    private void initAmap() {
        if (null != mAmapEngine) return;
        mAmapEngine = new AmapEngine();
        mAmapEngine.setLocationCallback(new AmapEngine.LocationCallback() {
            @SuppressLint("SetTextI18n") @Override public void onLocation(long timestamp, AMapLocation location) {
                if (cbIsOnce.isChecked()) {
                    mAmapEngine.stopLocation();
                }
                tvLoc.setText(timestamp + " || " + location.getAddress() + "\n");
            }

            @Override public void onError(int code, String info, String detail) {

            }
        });
        mAmapEngine.setPoiSearchCallback(new AmapEngine.PoiSearchCallback() {
            @Override public void onResult(PoiResult result) {
                ArrayList<PoiItem> pois = result.getPois();
                String poiResult = "";
                for (PoiItem poi : pois) {
                    poiResult += poi.getSnippet() + "\n";
                    tvPoi.setText(poiResult);
                }
            }
        });
    }
}

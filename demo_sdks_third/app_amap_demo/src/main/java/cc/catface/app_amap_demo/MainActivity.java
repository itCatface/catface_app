package cc.catface.app_amap_demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.amap.api.location.AMapLocation;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cc.catface.app_amap_demo.databinding.ActivityMainBinding;
import cc.catface.ctool.system.IInterface.ISystemInterface;
import cc.catface.ctool.system.TLog;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;

    private void checkPermission() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permission = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
                TLog.d("checkPermission:" + permission);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.LOCATION_HARDWARE}, 0x0010);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        checkPermission();
        demo();
    }

    private AmapEngine mAmapEngine;

    private void demo() {
        mBinding.btFinish.setOnClickListener(v -> finish());
        mAmapEngine = AmapEngine.getInstance();

        mBinding.btCheckPermission.setOnClickListener(v -> checkPermission());
        mBinding.btStart.setOnClickListener(v -> mAmapEngine.startLocation());
        mBinding.btStop.setOnClickListener(v -> mAmapEngine.stopLocation());
        mBinding.btPoi.setOnClickListener(v -> {
            mAmapEngine.startPoi(mBinding.etPoi.getText().toString().trim(), Integer.parseInt(mBinding.etPoiPage.getText().toString().trim()));
        });
        mBinding.etPoi.addTextChangedListener((ISystemInterface.TextChangedWatcher) (s, start, before, count) -> {
            InputtipsQuery inputQuery = new InputtipsQuery(s.toString(), mAmapEngine.getCity());
            Inputtips inputTips = new Inputtips(this, inputQuery);
            inputTips.setInputtipsListener(new Inputtips.InputtipsListener() {
                @Override
                public void onGetInputtips(List<Tip> list, int i) {
                    for (Tip tip : list) {
                        TLog.d("onGetInputtips -- i:" + i + " & tip:" + tip.toString());
                    }
                }
            });
            inputTips.requestInputtipsAsyn();
        });
        mBinding.btRelease.setOnClickListener(v -> mAmapEngine.release());

        mAmapEngine.setLocationCallback(new AmapEngine.LocationCallback() {
            @Override
            public void onLocation(long timestamp, AMapLocation location) {
                mBinding.tvLocation.setText("定位成功\r\n" + location.toStr());
                TLog.d("定位成功: " + timestamp + " || " + location.toStr());
            }

            @Override
            public void onError(int code, String info, String detail) {
                mBinding.tvLocation.setText("定位失败\r\n" + code + "\r\n" + info + "\r\n" + detail);
                TLog.d("定位失败：" + code + "\r\n" + info + "\r\n" + detail);
            }
        });

        mAmapEngine.setPoiSearchCallback(new AmapEngine.PoiSearchCallback() {
            @Override
            public void onResult(PoiResult result) {
                StringBuilder info = new StringBuilder(new Gson().toJson(result)).append("\r\n");
                TLog.d("位置搜索成功\r\n" + new Gson().toJson(result));
                List<String> poiList = new ArrayList<>();
                ArrayList<PoiItem> pois = result.getPois();
                for (PoiItem poi : pois) {
                    String snippet = poi.getSnippet();
                    if (!TextUtils.isEmpty(snippet)) {  // 过滤可能为空串的位置条目
                        poiList.add(poi.getSnippet());
                    }
                }

                for (String poi : poiList) {
                    TLog.d("位置搜索成功--> " + poi);
                    info.append(poi).append("\r\n");
                }
                mBinding.tvPoi.setText(info);
            }
        });
    }
}

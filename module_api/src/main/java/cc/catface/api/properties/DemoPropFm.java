package cc.catface.api.properties;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Map;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFmPropBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.system.TProperties;

public class DemoPropFm extends LightFm<LightPresenter, ApiFmPropBinding> {
    @Override public int layoutId() {
        return R.layout.api_fm_prop;
    }

    @RequiresApi(api = Build.VERSION_CODES.N) @Override protected void initAction() {
        mBinding.btSet.setOnClickListener(v -> {
            TProperties.set("sdcard/temp.prop", mBinding.etSetKey.getText().toString().trim(), mBinding.etSetValue.getText().toString().trim());
        });

        mBinding.btGet.setOnClickListener(v -> {
            String value = TProperties.get("sdcard/temp.prop", mBinding.etGetKey.getText().toString().trim());
            Log.d("catface", value);
        });

        mBinding.btGetAll.setOnClickListener(v -> {
            Map<String, String> allKV = TProperties.getAllKV("sdcard/temp.prop");
            allKV.forEach((key, value) -> {
                Log.d("catface", key + " || " + value);
            });
        });
    }
}

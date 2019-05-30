package cc.catface.base.utils.android;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TPermission {

    @SuppressLint("CheckResult") public static void request(FragmentActivity activity) {
//        new RxPermissions(activity)
//                .request(Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.ACCESS_FINE_LOCATION)
//                .subscribe(granted -> {
//                    if (!granted) {
//                        Toast.makeText(activity, "请重启应用允许请求的权限", Toast.LENGTH_SHORT).show();
//                    }
//                    //所有权限通过，初始化界面
//                    onPermissionChecked();
//                });
    }

}

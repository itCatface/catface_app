package cc.catface.start.main.mvp.vp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.CallSuper;
import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class MainPresenterImp extends LightPresenter<MainVP.MainView> implements MainVP.MainPresenter {

    public MainPresenterImp(MainVP.MainView view, Context context) {
        super(view, context);
    }

    @SuppressLint("CheckResult") @Override public void requestPermission(FragmentActivity activity) {
        new RxPermissions(activity).request(Manifest.permission.READ_PHONE_STATE/*,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION*/).subscribe(granted -> {
            if (!granted) {
                getView().requestPermissionFailure();
            }
            //所有权限通过，初始化界面
            onPermissionChecked();
        });
    }

    @CallSuper private void onPermissionChecked() {
        getView().requestPermissionSuccess();
    }
}

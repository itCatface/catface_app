package cc.catface.module_start.main.mvp.presenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;

import com.tbruyelle.rxpermissions2.RxPermissions;

import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.module_start.main.mvp.view.MainView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class MainPresenterImp extends MvpPresenter<MainView> implements MainPresenter {
    @SuppressLint("CheckResult") @Override public void requestPermission(FragmentActivity activity) {
        new RxPermissions(activity)
                .request(Manifest.permission.READ_PHONE_STATE/*,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION*/)
                .subscribe(granted -> {
                    if (!granted) {
                        mView.requestPermissionFailure();
                    }
                    //所有权限通过，初始化界面
                    onPermissionChecked();
                });
    }

    @CallSuper private void onPermissionChecked() {
        mView.requestPermissionSuccess();
    }
}

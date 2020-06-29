package cc.catface.app_aidl_client;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import cc.catface.aidls.login.SendBean;
import cc.catface.aidls.login.ILogin;
import cc.catface.aidls.login.ILoginLoginCallback;
import cc.catface.aidls.login.ILoginSendCallback;
import cc.catface.aidls.login.LoginBean;

public class MainActivity extends AppCompatActivity {

    private void log(String msg) {
        Log.d("catface-aidl-client", msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btSend).setOnClickListener(v -> {
            try {
                mService.send(getPhone(), new ILoginSendCallback.Stub() {
                    @Override
                    public void onSuccess(SendBean sendBean) throws RemoteException {
                        log("send success-->response: " + sendBean.toString());
                    }

                    @Override
                    public void onFailure(String error) throws RemoteException {
                        log("send failure-->error: " + error);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        findViewById(R.id.btLogin).setOnClickListener(v -> {
            try {
                mService.login(getPhone(), getCode(), new ILoginLoginCallback.Stub() {
                    @Override
                    public void onSuccess(LoginBean loginBean) throws RemoteException {
                        log("login success-->loginBean: " + loginBean.toString());
                    }

                    @Override
                    public void onFailure(String error) throws RemoteException {
                        log("login failure-->error: " + error);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        findViewById(R.id.btIsLogin).setOnClickListener(v -> {
            int i = new Random().nextInt(2);
            try {
                Toast.makeText(this, mService.isLogin(i == 0) + "--" + i, Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });


        findViewById(R.id.btIn).setOnClickListener(v -> {
            SendBean bean = new SendBean("111111", "test in");
            try {
                mService.testSendBeanIn(bean);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        findViewById(R.id.btOut).setOnClickListener(v -> {
            SendBean bean = new SendBean("222222", "test out");
            try {
                mService.testSendBeanOut(bean);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        findViewById(R.id.btInout).setOnClickListener(v -> {
            SendBean bean = new SendBean("333333", "test inout");
            try {
                mService.testSendBeanInout(bean);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        initConnAndBind();
    }

    protected ILogin mService;
    private ServiceConnection mServiceConnection;

    private void initConnAndBind() {
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {
                log("服务断开连接" + " || 当前线程：" + Thread.currentThread().getName());
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = ILogin.Stub.asInterface(service);
                log("服务已连接 || service is: " + mService);
            }
        };

        if (mService == null) {
            Intent intent = new Intent();
            intent.setClassName("cc.catface.app_aidl_service", "cc.catface.aidls.login.LoginService");
            intent.setAction("cc.catface.aidls.login.action");
            bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
            log("bind service login");
        }
    }

    private String getPhone() {
        return ((EditText) findViewById(R.id.etPhone)).getText().toString().trim();
    }

    private String getCode() {
        return ((EditText) findViewById(R.id.etCode)).getText().toString().trim();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mServiceConnection) {
            unbindService(mServiceConnection);
        }
    }
}

package cc.catface.aidls.login;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class LoginService extends Service {

    private void log(String msg) {
        Log.d("catface login service", msg);
    }

    private RemoteCallbackList<ILoginSendCallback> mSendCallbacks = new RemoteCallbackList<>();
    private RemoteCallbackList<ILoginLoginCallback> mLoginCallbacks = new RemoteCallbackList<>();

    public class Binder extends ILogin.Stub {
        @Override
        public void send(String phone, ILoginSendCallback callback) throws RemoteException {
            mSendCallbacks.register(callback);

            if (phone.trim().length() == 11) {
                SendBean bean = new SendBean("000000", "发送成功！");
                int count = mSendCallbacks.beginBroadcast();
                for (int i = 0; i < count; i++) {
                    mSendCallbacks.getBroadcastItem(i).onSuccess(bean);
                }
            } else {
                int count = mSendCallbacks.beginBroadcast();
                for (int i = 0; i < count; i++) {
                    mSendCallbacks.getBroadcastItem(i).onFailure("请检查手机号是否正确！[" + phone.trim() + "]");
                }
            }
            mSendCallbacks.finishBroadcast();
            mSendCallbacks.unregister(callback);
        }

        @Override
        public void login(String phone, String code, ILoginLoginCallback callback) throws RemoteException {
            mLoginCallbacks.register(callback);

            if (phone.trim().length() == 11 && code.trim().length() > 0) {
                LoginBean bean = new LoginBean();
                bean.setCode("000000");
                bean.setDesc("登录成功！");
                LoginBean.Biz biz = new LoginBean.Biz();
                biz.setSessionId("id is: " + phone.trim());
                bean.setBiz(biz);
                int count = mLoginCallbacks.beginBroadcast();
                for (int i = 0; i < count; i++) {
                    mLoginCallbacks.getBroadcastItem(i).onSuccess(bean);
                }
            } else {
                int count = mLoginCallbacks.beginBroadcast();
                for (int i = 0; i < count; i++) {
                    mLoginCallbacks.getBroadcastItem(i).onFailure("请检查手机号和验证码是否正确！[" + phone.trim() + "]-[" + code.trim() + "]");
                }
            }
            mLoginCallbacks.finishBroadcast();
            mLoginCallbacks.unregister(callback);
        }

        @Override
        public boolean isLogin(boolean isLogin) throws RemoteException {
            return isLogin;
        }


        /**
         * 测试aidl非基本型参数的tag区别[in/out/inout]
         */
        @Override
        public void testSendBeanIn(SendBean sendBean) throws RemoteException {
            log("testSendBeanIn-->sendBean: " + sendBean.toString());
        }

        @Override
        public void testSendBeanOut(SendBean sendBean) throws RemoteException {
            log("testSendBeanOut-->sendBean: " + sendBean.toString());
        }

        @Override
        public void testSendBeanInout(SendBean sendBean) throws RemoteException {
            log("testSendBeanInout-->sendBean: " + sendBean.toString());
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }
}

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
package cc.catface.aidls.login;
import cc.catface.aidls.login.ILoginSendCallback;
import cc.catface.aidls.login.ILoginLoginCallback;
import cc.catface.aidls.login.SendBean;

interface ILogin {
    void send(String phone, ILoginSendCallback callback);

    void login(String phone, String code, ILoginLoginCallback callback);

    boolean isLogin(boolean isLogin);


    /* 非基本数据类型的参数需要加tag
            [in-数据由客户进程流向服务进程/out-数据由服务进程流向客户进程/inout-数据在客户和服务进程双向流通]

       基本类型的tag均是in */
    void testSendBeanIn(in SendBean sendBean);

    void testSendBeanOut(out SendBean sendBean);

    void testSendBeanInout(inout SendBean sendBean);
}

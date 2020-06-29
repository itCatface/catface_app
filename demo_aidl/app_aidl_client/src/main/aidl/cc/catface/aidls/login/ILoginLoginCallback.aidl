/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
package cc.catface.aidls.login;
import cc.catface.aidls.login.LoginBean;

interface ILoginLoginCallback {

    void onSuccess(in LoginBean loginBean);

    void onFailure(String error);
}

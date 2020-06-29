/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
package cc.catface.aidls.login;
import cc.catface.aidls.login.SendBean;

interface ILoginSendCallback {

    void onSuccess(in SendBean sendBean);

    void onFailure(String error);
}

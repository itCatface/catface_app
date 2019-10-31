package cc.catface.start.login.model;

import cc.catface.base.utils.java.TNumber;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class LoginModelImp implements LoginModel {

    @Override public void request(Callback callback) {
        if (null == callback) return;
        if (TNumber.getRandom(0, 1) == 1) {
            callback.onSuccess("得到随机数1-->成功");
        } else {
            callback.onError("得到随机数0-->失败");
        }
    }
}

package cc.catface.start.login.vp;

import android.content.Context;

import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.system.Timer.TTimer;
import cc.catface.base.utils.java.TNumber;
import cc.catface.start.login.model.LoginModel;
import cc.catface.start.login.model.LoginModelImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class LoginPresenterImp extends LightPresenter<LoginVP.LoginView> implements LoginVP.LoginPresenter {

    public LoginPresenterImp(LoginVP.LoginView view, Context context) {
        super(view, context);
    }

    private final LoginModelImp mModel = new LoginModelImp();

    @Override public void startRequest() {
        getView().requesting();


        TTimer.timeFinished(TNumber.getRandom(1, 2) * 1_000, () -> mModel.request(new LoginModel.Callback() {
            @Override public void onSuccess(String result) {
                getView().requestSuccess(result);
            }

            @Override public void onError(String error) {
                getView().requestError(error);
            }
        }));
    }
}

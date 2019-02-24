package cc.catface.module_start.login.presenter;

import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.utils.android.Timer.TTimer;
import cc.catface.base.utils.java.TNumber;
import cc.catface.module_start.login.model.LoginModel;
import cc.catface.module_start.login.model.LoginModelImp;
import cc.catface.module_start.login.view.LoginView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class LoginPresenterImp extends MvpPresenter<LoginView> implements LoginPresenter {
    private final LoginModelImp mModel = new LoginModelImp();

    @Override public void startRequest() {
        mView.requesting();


        TTimer.timeFinished(TNumber.getRandom(1, 2) * 1_000, () -> mModel.request(new LoginModel.Callback() {
            @Override public void onSuccess(String result) {
                mView.requestSuccess(result);
            }

            @Override public void onError(String error) {
                mView.requestError(error);
            }
        }));
    }
}

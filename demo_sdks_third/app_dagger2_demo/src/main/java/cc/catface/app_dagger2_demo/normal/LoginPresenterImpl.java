package cc.catface.app_dagger2_demo.normal;

import android.widget.Toast;

import cc.catface.app_dagger2_demo.User;

public class LoginPresenterImpl implements LoginI.LoginPresenter {

    private LoginI.LoginView mView;

    public LoginPresenterImpl(LoginI.LoginView view) {
        this.mView = view;
    }

    public void login(User user) {
        Toast.makeText(mView.getContext(), "login -- user:" + user.toString(), Toast.LENGTH_SHORT).show();
    }


}

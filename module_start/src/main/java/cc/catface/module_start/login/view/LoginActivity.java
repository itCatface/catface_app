package cc.catface.module_start.login.view;

import cc.catface.module_start.R;
import cc.catface.module_start.login.presenter.LoginPresenterImp;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsAppCompatActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(LoginPresenterImp.class)
public class LoginActivity extends AbsAppCompatActivity<LoginView, LoginPresenterImp> implements LoginView {

    @Override public int layoutId() {
        return R.layout.start_activity_login;
    }

    @Override public void create() {

    }
}

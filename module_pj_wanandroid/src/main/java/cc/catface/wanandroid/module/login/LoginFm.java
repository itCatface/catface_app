package cc.catface.wanandroid.module.login;

import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentLoginBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(LoginPresenterImpl.class)
public class LoginFm extends MvpFragment<LoginView, LoginPresenterImpl, WanandroidFragmentLoginBinding> implements LoginView {
    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_login;
    }

    @Override public void viewCreated() {

    }
}

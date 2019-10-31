package cc.catface.wanandroid.module.login.view;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentLoginBinding;
import cc.catface.wanandroid.module.login.vp.LoginPresenterImpl;
import cc.catface.wanandroid.module.login.vp.LoginVP;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class LoginFm extends LightFm<LoginPresenterImpl, WanandroidFragmentLoginBinding> implements LoginVP.LoginView {

    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_login;
    }

}

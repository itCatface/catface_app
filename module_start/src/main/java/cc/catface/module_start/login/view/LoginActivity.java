package cc.catface.module_start.login.view;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpActivity;
import cc.catface.module_start.R;
import cc.catface.module_start.databinding.StartActivityLoginBinding;
import cc.catface.module_start.login.presenter.LoginPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 *
 * @desc 超简单的mvp+DataBinding操作案例
 */
@Route(path = Const.AROUTER.start_login)
@CreatePresenter(LoginPresenterImp.class)
public class LoginActivity extends MvpActivity<LoginView, LoginPresenterImp, StartActivityLoginBinding> implements LoginView {
    @Override public int layoutId() {
        return R.layout.start_activity_login;
    }

    @Override public void create() {
        mBinding.btRequest.setOnClickListener(v -> mPresenter.startRequest());
    }

    @Override public void requesting() {
        mBinding.tvMsg.setText("正在请求中，请稍后...");
    }

    @Override public void requestSuccess(String result) {
        mBinding.tvMsg.setText(result);
    }

    @Override public void requestError(String error) {
        mBinding.tvMsg.setText(error);
    }
}

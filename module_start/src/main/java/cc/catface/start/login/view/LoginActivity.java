package cc.catface.start.login.view;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.start.R;
import cc.catface.start.databinding.StartActivityLoginBinding;
import cc.catface.start.login.vp.LoginPresenterImp;
import cc.catface.start.login.vp.LoginVP;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 *
 * @desc 超简单的mvp+DataBinding操作案例
 */
@Route(path = Const.ARouter.start_login) public class LoginActivity extends LightAct<LoginPresenterImp, StartActivityLoginBinding> implements LoginVP.LoginView {

    @Override public int layoutId() {
        return R.layout.start_activity_login;
    }

    @Override protected void initPresenter() {
        mPresenter = new LoginPresenterImp(this, this);
    }

    @Override protected void created() {
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

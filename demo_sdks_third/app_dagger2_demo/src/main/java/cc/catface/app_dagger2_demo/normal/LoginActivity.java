package cc.catface.app_dagger2_demo.normal;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cc.catface.app_dagger2_demo.R;
import cc.catface.app_dagger2_demo.User;

public class LoginActivity extends AppCompatActivity implements LoginI.LoginView {

    private LoginPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenterImpl(this);
        findViewById(R.id.btLogin).setOnClickListener(v -> mPresenter.login(new User()));
    }

    @Override
    public Context getContext() {
        return this;
    }
}

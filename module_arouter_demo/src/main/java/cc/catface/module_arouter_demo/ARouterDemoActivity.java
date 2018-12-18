package cc.catface.module_arouter_demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.catface.app_base.Const;

@Route(path = Const.ROUTE.test_arouter)
public class ARouterDemoActivity extends Activity {

    @Autowired public String value;

//    @OnClick({R2.id.bt_press, R2.id.bt_back}) void event(View view) {
    @OnClick({R.id.bt_press, R.id.bt_back}) void event(View view) {
        if (R.id.bt_press == view.getId()) {
            Toast.makeText(this, "接收主模块传值：" + value, Toast.LENGTH_SHORT).show();
        } else if (R.id.bt_back == view.getId()) {
            finish();
        }
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arouter_activity_arouter_demo);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }
}

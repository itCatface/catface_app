package cc.catface.module_arouter_demo;

import android.app.Activity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.ButterKnife;
import cc.catface.app_base.Const;
import cc.catface.module_arouter_demo.databinding.ModuleArouterActivityArouterBinding;

@Route(path = Const.AROUTER.test_arouter)
public class ARouterDemoActivity extends Activity implements View.OnClickListener {
    private ModuleArouterActivityArouterBinding mBinding;

    @Autowired public String value;

    @Override public void onClick(View view) {
        if (R.id.bt_press == view.getId()) {
            Toast.makeText(this, "接收主模块传值：" + value, Toast.LENGTH_SHORT).show();
        } else if (R.id.bt_back == view.getId()) {
            finish();
        }
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.module_arouter_activity_arouter);
        mBinding.btPress.setOnClickListener(this);
        mBinding.btBack.setOnClickListener(this);
        ARouter.getInstance().inject(this);

        ButterKnife.bind(this);
    }

//    @BindView(R.id.bt_test_butter_knife) Button bt_test_butter_knife;
//
//    @OnClick(R.id.bt_test_butter_knife) void event() {
//        bt_test_butter_knife.setText("...");
//        Toast.makeText(this, "bt_test_butter_knife已点击", Toast.LENGTH_SHORT).show();
//    }
}

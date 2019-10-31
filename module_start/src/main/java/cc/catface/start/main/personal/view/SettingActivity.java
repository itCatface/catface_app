package cc.catface.start.main.personal.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.start.R;
import cc.catface.start.databinding.StartActivitySettingBinding;

import static android.graphics.Color.parseColor;

public class SettingActivity extends LightAct<LightPresenter, StartActivitySettingBinding> {

    @Override public int layoutId() {
        return R.layout.start_activity_setting;
    }

    @Override protected void initView() {
        mBinding.sbCache.setBigCircleModel(parseColor("#cccccc"), parseColor("#FF4040"), parseColor("#00ffffff"), parseColor("#ffffff"), parseColor("#ffffff"));

        initToolBar();
    }


    /** tool bar */
    private void initToolBar() {
        Toolbar toolbar = mBinding.iTbStart.tbTitle;
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (null != bar) {
            bar.setDisplayShowHomeEnabled(true);
            bar.setTitle("系统设置");
        }
        toolbar.setNavigationIcon(R.mipmap.flaticon_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}

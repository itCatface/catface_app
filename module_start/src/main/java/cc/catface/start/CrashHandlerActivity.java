package cc.catface.start;

import android.content.Intent;
import android.text.method.ScrollingMovementMethod;

import cc.catface.base.R;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.databinding.CrashActivityCrashHandlerBinding;
import cc.catface.start.main.mvp.view.MainActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class CrashHandlerActivity extends LightAct<LightPresenter, CrashActivityCrashHandlerBinding> {

    @Override public int layoutId() {
        return R.layout.crash_activity_crash_handler;
    }

    @Override protected void created() {
        mBinding.tvInfo.setMovementMethod(ScrollingMovementMethod.getInstance());
        mBinding.tvInfo.setText(getIntent().getStringExtra("info"));
        mBinding.btOption.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }
}

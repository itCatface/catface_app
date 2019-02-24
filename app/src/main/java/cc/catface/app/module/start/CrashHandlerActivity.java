package cc.catface.app.module.start;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import cc.catface.base.R;
import cc.catface.base.databinding.CrashActivityCrashHandlerBinding;
import cc.catface.module_start.main.mvp.view.MainActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class CrashHandlerActivity extends AppCompatActivity {
    private CrashActivityCrashHandlerBinding mBinding;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.crash_activity_crash_handler);

        mBinding.tvInfo.setMovementMethod(ScrollingMovementMethod.getInstance());
        mBinding.tvInfo.setText(getIntent().getStringExtra("info"));
        mBinding.btOption.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }
}

package cc.catface.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import viewmodel.AccountLiveData;
import viewmodel.TestVMActivity;

public class IndexActivity extends AppCompatActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        findViewById(R.id.bt2Animation).setOnClickListener(v->startActivity(new Intent(this, MainActivity.class)));

        findViewById(R.id.bt2TestVM).setOnClickListener(v->startActivity(new Intent(this, TestVMActivity.class)));

        new CountDownTimer(100000, 500) {
            @Override public void onTick(long millisUntilFinished) {
                Log.d("catface", getClass().getName()+"-->setting");
                AccountLiveData.getInstance().set(System.currentTimeMillis() + "---index");
            }

            @Override public void onFinish() {

            }
        }.start();
    }

    @Override protected void onResume() {
        super.onResume();


    }
}

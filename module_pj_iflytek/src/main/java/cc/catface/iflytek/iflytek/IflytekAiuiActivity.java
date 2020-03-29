package cc.catface.iflytek.iflytek;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.method.ScrollingMovementMethod;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import cc.catface.ctool.system.TLog;
import cc.catface.iflytek.R;
import cc.catface.iflytek.databinding.ApisIflytekActivityAiuiBinding;
import cc.catface.iflytek.domain.AiuiIntentData;
import cc.catface.iflytek.mail.MailTest;
import cc.catface.iflytek.service.AIUIService;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class IflytekAiuiActivity extends AppCompatActivity implements AIUIService.RecognizeCallback {
    private ApisIflytekActivityAiuiBinding mBinding;

    private ServiceConnection conn = new ServiceConnection() {
        @Override public void onServiceConnected(ComponentName name, IBinder service) {
            AIUIService.Binder binder = (AIUIService.Binder) service;
            binder.getServer().setCallback(IflytekAiuiActivity.this);
        }

        @Override public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.apis_iflytek_activity_aiui);
        mBinding.tvResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        mBinding.btStartAiui.setOnClickListener(view -> {
            startService(new Intent(IflytekAiuiActivity.this, AIUIService.class));
            IflytekAiuiActivity.this.bindService(new Intent(IflytekAiuiActivity.this, AIUIService.class), conn, Context.BIND_AUTO_CREATE);
        });

        mBinding.btClear.setOnClickListener(v -> {
            mBinding.tvResult.setText("---");
        });


        mBinding.btSendMail.setOnClickListener(v -> {
            MailTest.sendMailByJavaMail("客户端发来日志", "测试客户端发来日志...");
        });

    }


    @Override public void isVoiceOn(boolean isOn) {
        mBinding.btClear.setText("当前aiui状态：" + isOn);
    }

    /**
     * server's callback
     */


    @Override public void onState(String state) {
        TLog.d("onState====================>" + state);
    }

    @Override public void onIntent(AiuiIntentData intent, String sentence) {
        TLog.d("onIntent: " + intent.toString());
        mBinding.tvResult.setText("onIntent：" + intent.toString() + " \r\n  " + sentence);
    }

    @Override public void onNoIntent(String sentence) {
        TLog.d("onNoIntent: " + sentence);
        mBinding.tvResult.setText("onNoIntent: " + sentence);
    }

    @Override public void onException(String exception) {
        TLog.d("onException: " + exception);
        mBinding.tvResult.setText("onException: " + exception);
    }
}

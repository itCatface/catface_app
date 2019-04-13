package cc.catface.iflytek;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.iflytek.service.AIUIServer;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class AiuiActivity extends AppCompatActivity implements AIUIServer.RecognizeCallback {


    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AIUIServer.Binder binder = (AIUIServer.Binder) service;
            binder.getServer().setCallback(AiuiActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_aiui);

        findViewById(R.id.bt_start_aiui).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startService(new Intent(AiuiActivity.this, AIUIServer.class));
                AiuiActivity.this.bindService(new Intent(AiuiActivity.this, AIUIServer.class), conn, Context.BIND_AUTO_CREATE);
            }
        });


    }


    /** server's callback */
    @Override public void result(String content, String understanding) {
        TLog.d("语义结果：" + content + " || " + understanding);
    }

    @Override public void state(String state) {
        TLog.d("语义状态：" + state);
    }
}

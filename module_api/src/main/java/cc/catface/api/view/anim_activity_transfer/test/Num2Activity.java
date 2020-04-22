package cc.catface.api.view.anim_activity_transfer.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cc.catface.api.R;
import cc.catface.api.view.anim_activity_transfer.activity.TransferMainActivity;

public class Num2Activity extends Activity {
    SildingFinishLayout sildingFinishLayout = null;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_main_test2);

        sildingFinishLayout = (SildingFinishLayout) findViewById(R.id.num2);
        sildingFinishLayout
                .setOnSildingFinishListener(new SildingFinishLayout.OnSildingFinishListener() {

                    @Override
                    public void onSildingFinish(int type) {
                        if (type == 1)
                            Num2Activity.this.finish();
                        if (type == 2) {
                            Intent fromSpiltOuttoPostIntent = new Intent();
                            fromSpiltOuttoPostIntent.setClass(Num2Activity.this,
                                    TransferMainActivity.class);
                            startActivity(fromSpiltOuttoPostIntent);

                        }

                    }
                });
        sildingFinishLayout.setSlidingDirection(3);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (flag)
            sildingFinishLayout.scrollOrigin(true);
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        flag = true;
    }

}
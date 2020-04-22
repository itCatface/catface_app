package cc.catface.api.view.anim_activity_transfer.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cc.catface.api.R;


public class MainActivity extends Activity {
    SildingFinishLayout sildingFinishLayout = null;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_main_test);

        sildingFinishLayout = (SildingFinishLayout) findViewById(R.id.num1);
        sildingFinishLayout
                .setOnSildingFinishListener(new SildingFinishLayout.OnSildingFinishListener() {

                    @Override
                    public void onSildingFinish(int type) {
                        if (type == 2) {
                            Intent fromSpiltOuttoPostIntent = new Intent();
                            fromSpiltOuttoPostIntent.setClass(MainActivity.this,
                                    Num2Activity.class);
                            startActivity(fromSpiltOuttoPostIntent);

                        }

                    }
                });
        //设置仅仅能向左滑动打开新的activity
        sildingFinishLayout.setSlidingDirection(2);
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //当从新显示当前页面的时候把sildingFinishLayout滚回到屏幕可视范围，之前已经滚出了屏幕
        if (flag)
            sildingFinishLayout.scrollOrigin(true);//将原来私有的滚会初始位置的方法设为public
    }


    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        flag = true;
    }


}
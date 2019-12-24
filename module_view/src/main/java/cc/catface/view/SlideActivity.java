package cc.catface.view;

import android.os.Bundle;
import android.transition.Slide;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 滑动 效果
 */
public class SlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        //进入退出效果 注意这里 创建的效果对象是 Slide()
        getWindow().setEnterTransition(new Slide().setDuration(2000));
        getWindow().setExitTransition(new Slide().setDuration(2000));
    }
}

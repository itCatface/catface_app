package cc.catface.view;

import android.os.Bundle;
import android.transition.Explode;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 分解（爆炸；推翻）效果
 */
public class ExplodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolve);
        //进入退出效果 注意这里 创建的效果对象是 Explode()
        getWindow().setEnterTransition(new Explode().setDuration(2000));
        getWindow().setExitTransition(new Explode().setDuration(2000));
    }
}

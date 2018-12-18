package cc.catface.api.view.demo04_object;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cc.catface.api.R;

public class ObjectActivity extends AppCompatActivity {

    private TextView tv;
    private PointView pv;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_object);
        tv = (TextView) findViewById(R.id.tv_title);
        pv = (PointView) findViewById(R.id.pv);

        findViewById(R.id.bt_alpha).setOnClickListener(view -> alpha());
        findViewById(R.id.bt_translate).setOnClickListener(view -> translate());
        findViewById(R.id.bt_scale).setOnClickListener(view -> scale());
        findViewById(R.id.bt_rotate).setOnClickListener(view -> rotate());
        findViewById(R.id.bt_set).setOnClickListener(view -> set());
        findViewById(R.id.bt_argbEva).setOnClickListener(view -> argbEva());
    }


    /** 基本动画：当且仅当动画的只有一个过渡值时，系统才会调用对应属性的get函数来得到动画的初始值 */
    private void alpha() {
        ObjectAnimator.ofFloat(tv, "api_alpha", 0f, 1f, 0.5f, 1f).setDuration(2_000).start();
    }

    private void translate() {
        ObjectAnimator.ofFloat(tv, "translationX", 0f, 200f, -150f, 50f, 0f).setDuration(2_000).start();   // translationY
    }

    private void scale() {
        ObjectAnimator.ofFloat(tv, "scaleX", 0f, 2f, 0.5f, 1f).setDuration(2_000).start(); // scaleY
    }

    private void rotate() {
        ObjectAnimator.ofFloat(tv, "rotationY", 0, 180, 0, 180, 0).setDuration(3_000).start();  // rotationX |
    }

    private void set() {
        ObjectAnimator.ofInt(pv, "pointRadius", pv.getMeasuredWidth() / 2 - 20).setDuration(2_000).start();
    }

    private void argbEva() {
        ObjectAnimator animatorTvBg = ObjectAnimator.ofInt(tv, "backgroundColor", 0xffff00ff, 0xff0000ff, 0xff00ff00);
        animatorTvBg.setEvaluator(new ArgbEvaluator());
        animatorTvBg.setDuration(2_000).start();

        ObjectAnimator animatorTvColor = ObjectAnimator.ofInt(tv, "textColor", 0xffffffff, 0xff0000ff, 0xff000000);
        animatorTvColor.setEvaluator(new ArgbEvaluator());
        animatorTvColor.setDuration(2_000).start();
    }
}

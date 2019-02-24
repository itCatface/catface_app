package cc.catface.api.view.demo06_anim_set;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import cc.catface.api.R;

public class AnimSetActivity extends AppCompatActivity {

    private TextView tv;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_anim_set);
        tv = (TextView) findViewById(R.id.tv_title);
        findViewById(R.id.bt_animator1).setOnClickListener(view -> animator1());
    }

    private void animator1() {
        ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.api_animator_anim_set);
        animator.addUpdateListener(valueAnimator -> {
            int value = (int) valueAnimator.getAnimatedValue();
            tv.layout(value, value, tv.getWidth() + value, tv.getHeight() + value);
        });
        animator.start();
    }
}

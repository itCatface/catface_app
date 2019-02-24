package cc.catface.api.view.demo01_astrs;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import cc.catface.api.R;


public class ASTRSActivity extends AppCompatActivity {

    private Button bt;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_astrs);

        bt = (Button) findViewById(R.id.bt);

        initAnim();
    }

    private void initAnim() {
        findViewById(R.id.bt_alpha).setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.api_alpha);
            bt.startAnimation(animation);
        });

        findViewById(R.id.bt_scale).setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.api_scale);
            bt.startAnimation(animation);
        });

        findViewById(R.id.bt_translate).setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.api_translate);
            bt.startAnimation(animation);
        });

        findViewById(R.id.bt_rotate).setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.api_rotate);
            bt.startAnimation(animation);
        });

        findViewById(R.id.bt_set).setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.api_set);
            bt.startAnimation(animation);
        });
    }
}

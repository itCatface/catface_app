package cc.catface.api.view.demo02_interpolator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import cc.catface.api.R;


/**
 * AccelerateDecelerateInterpolator             在动画开始与介绍的地方速率改变比较慢，在中间的时候加速
 * AccelerateInterpolator                       在动画开始的地方速率改变比较慢，然后开始加速
 * AnticipateInterpolator                       开始的时候向后然后向前甩
 * AnticipateOvershootInterpolator              开始的时候向后然后向前甩一定值后返回最后的值
 * BounceInterpolator                           动画结束的时候弹起
 * CycleInterpolator                            动画循环播放特定的次数，速率改变沿着正弦曲线
 * DecelerateInterpolator                       在动画开始的地方快然后慢
 * LinearInterpolator                           以常量速率改变
 * OvershootInterpolator                        向前甩一定值后再回到原来位置
 */
public class InterpolatorActivity extends AppCompatActivity {

    private Button bt;
    private Spinner spinner;
    private ArrayAdapter mAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_interpolator);

        bt = (Button) findViewById(R.id.bt);
        spinner = (Spinner) findViewById(R.id.s);
        mAdapter = ArrayAdapter.createFromResource(this, R.array.api_view_interpolator, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mAdapter);
        spinner.setOnItemSelectedListener(new SpinnerItemClickListener());


        initAnim();
    }

    private void initAnim() {
        findViewById(R.id.bt_alpha).setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.api_alpha);
            animation.setInterpolator(mInterpolator);
            bt.startAnimation(animation);
        });

        findViewById(R.id.bt_scale).setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.api_scale);
            animation.setInterpolator(mInterpolator);
            bt.startAnimation(animation);
        });

        findViewById(R.id.bt_translate).setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.api_translate);
            animation.setInterpolator(mInterpolator);
            bt.startAnimation(animation);
        });

        findViewById(R.id.bt_rotate).setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.api_rotate);
            animation.setInterpolator(mInterpolator);
            bt.startAnimation(animation);
        });

        findViewById(R.id.bt_set).setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.api_set);
            animation.setInterpolator(mInterpolator);
            bt.startAnimation(animation);
        });
    }

    Interpolator mInterpolator = new OvershootInterpolator();

    private class SpinnerItemClickListener implements AdapterView.OnItemSelectedListener {

        @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String item = "" + mAdapter.getItem(i);
            switch (item) {
                case "AccelerateDecelerate":
                    mInterpolator = new AccelerateDecelerateInterpolator();
                    break;
                case "Accelerate":
                    mInterpolator = new AccelerateInterpolator();
                    break;
                case "Anticipate":
                    mInterpolator = new AnticipateInterpolator();
                    break;
                case "AnticipateOvershoot":
                    mInterpolator = new AnticipateOvershootInterpolator();
                    break;
                case "Bounce":
                    mInterpolator = new BounceInterpolator();
                    break;
                case "Cycle":
                    mInterpolator = new CycleInterpolator(0.5f);
                    break;
                case "Decelerate":
                    mInterpolator = new DecelerateInterpolator();
                    break;
                case "Linear":
                    mInterpolator = new LinearInterpolator();
                    break;
                case "Overshoot":
                    mInterpolator = new OvershootInterpolator();
                    break;
            }
        }

        @Override public void onNothingSelected(AdapterView<?> adapterView) { }
    }
}

package cc.catface.api.view.demo05_propertyvaluesholder_keyframe;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import cc.catface.api.R;

public class KeyframeActivity extends AppCompatActivity {

    private TextView tv;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_keyframe);
        tv = (TextView) findViewById(R.id.tv_title);

        findViewById(R.id.bt_ofFloat_ofInt).setOnClickListener(view -> ofFloat_ofInt());
        findViewById(R.id.bt_ofObject).setOnClickListener(view -> ofObject());
        findViewById(R.id.bt_keyframe).setOnClickListener(view -> keyframe());
        findViewById(R.id.bt_keyframe2).setOnClickListener(view -> keyframe2());
        //        findViewById(R.id.bt_set).setOnClickListener(view -> api_set());
        //        findViewById(R.id.bt_argbEva).setOnClickListener(view -> argbEva());
    }

    /** PropertyValuesHolder */
    /* ofFloat & ofInt */
    private void ofFloat_ofInt() {
        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("rotation", 60f, -60f, 40f, -40f, -20f, 20f, 10f, -10f, 0f);
        PropertyValuesHolder backgroundColorHolder = PropertyValuesHolder.ofInt("backgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tv, rotationHolder, backgroundColorHolder);
        animator.setDuration(3_000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    /* ofObject */
    private void ofObject() {
        PropertyValuesHolder charTextHolder = PropertyValuesHolder.ofObject("charText", new CharEvaluator(), 'A', 'Z');
        ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tv, charTextHolder);
        animator.setDuration(3_000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    private class CharEvaluator implements TypeEvaluator<Character> {

        @Override public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startInt = (int) startValue;
            int endInt = (int) endValue;
            int curInt = (int) (startInt + fraction * (endInt - startInt));
            return (char) curInt;
        }
    }


    /**
     * Keyframe
     *
     * 如果去掉第0帧，将以第一个关键帧为起始位置
     * 如果去掉结束帧，将以最后一个关键帧为结束位置
     * 使用Keyframe来构建动画，至少要有两个或两个以上帧
     */
    private void keyframe() {
        Keyframe keyframe1 = Keyframe.ofFloat(0f, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe keyframe3 = Keyframe.ofFloat(1f, 0);
        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofKeyframe("rotation", keyframe1, keyframe2, keyframe3);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tv, rotationHolder);
        animator.setDuration(2_000);
        animator.start();
    }

    private void keyframe2() {
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe frame2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe frame4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe frame5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe frame6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe frame7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe frame8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe frame9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe frame10 = Keyframe.ofFloat(1, 0);
        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10);
        ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tv, rotationHolder);
        animator.setDuration(1_000);
        animator.start();
    }
}

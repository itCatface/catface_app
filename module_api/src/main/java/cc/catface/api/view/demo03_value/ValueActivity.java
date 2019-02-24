package cc.catface.api.view.demo03_value;

import android.animation.ArgbEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import cc.catface.api.R;
import cc.catface.api.view.demo03_value.ofObject_circle.PointView;

public class ValueActivity extends AppCompatActivity {

    private TextView tv, tv_ch;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_value);

        tv = (TextView) findViewById(R.id.tv_title);
        tv_ch = (TextView) findViewById(R.id.tv_ch);

        findViewById(R.id.bt_start_easy).setOnClickListener(v -> anim());
        findViewById(R.id.bt_start_easy_interpolator).setOnClickListener(v -> animWithInterpolator());
        findViewById(R.id.bt_start_easy_evaluator1).setOnClickListener(v -> animWithEvaluator1());
        findViewById(R.id.bt_start_easy_evaluator2).setOnClickListener(v -> animWithEvaluator2());
        findViewById(R.id.bt_start_easy_evaluator3).setOnClickListener(v -> animWithEvaluator3());
        findViewById(R.id.bt_start_easy_ofObject).setOnClickListener(v -> animWithOfObject());
        findViewById(R.id.pv).setOnClickListener(v -> animPointView());
    }


    private float[] points = {200f, 600f, 350f};

    /** ValueAnimator入门使用 */
    private void anim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(points);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(animation -> {
            Float valueFloat = (Float) animation.getAnimatedValue();    // 获取当前动画运动点的值
            int valueInt = valueFloat.intValue();
            Log.d("root", "value-->" + valueFloat);
            tv.layout(valueInt, valueInt, tv.getWidth() + valueInt, tv.getHeight() + valueInt);
        });
        valueAnimator.start();
    }


    /** ValueAnimator之插值器 */
    private void animWithInterpolator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(points);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(animation -> {
            Float valueFloat = (Float) animation.getAnimatedValue();    // 获取当前动画运动点的值
            int valueInt = valueFloat.intValue();
            Log.d("root", "value-->" + valueFloat);
            tv.layout(valueInt, valueInt, tv.getWidth() + valueInt, tv.getHeight() + valueInt);
        });
        valueAnimator.setInterpolator(new CustomInterpolator());
        valueAnimator.start();
    }

    public class CustomInterpolator implements Interpolator {

        @Override public float getInterpolation(float input) {
            Log.d("root", "input is: " + input);
            return 1 - input;
        }
    }

    /** ValueAnimator之Evaluator1 */
    private void animWithEvaluator1() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(points);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(animation -> {
            Float valueFloat = (Float) animation.getAnimatedValue();    // 获取当前动画运动点的值
            int valueInt = valueFloat.intValue();
            Log.d("root", "addUpdateListener-->value: " + valueFloat);
            tv.layout(valueInt, valueInt, tv.getWidth() + valueInt, tv.getHeight() + valueInt);
        });
        valueAnimator.setEvaluator(new CustomEvaluator());
        valueAnimator.start();
    }


    public class CustomEvaluator implements TypeEvaluator<Float> {

        @Override public Float evaluate(float fraction, Float startValue, Float endValue) {
            Log.d("root", "CustomEvaluator-->evaluate: " + (100 + startValue + fraction * (endValue - startValue)));
            return (float) (40 + startValue + fraction * (endValue - startValue));
        }
    }

    /** ValueAnimator之Evaluator2 */
    private void animWithEvaluator2() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(points);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(animation -> {
            Float valueFloat = (Float) animation.getAnimatedValue();    // 获取当前动画运动点的值
            int valueInt = valueFloat.intValue();
            Log.d("root", "addUpdateListener-->value: " + valueFloat);
            tv.layout(valueInt, valueInt, tv.getWidth() + valueInt, tv.getHeight() + valueInt);
        });
        valueAnimator.setEvaluator(new ReverseEvaluator());
        valueAnimator.start();
    }


    public class ReverseEvaluator implements TypeEvaluator<Float> {

        @Override public Float evaluate(float fraction, Float startValue, Float endValue) {
            Log.d("root", "CustomEvaluator-->evaluate: " + (endValue - fraction * (endValue - startValue)));
            return (float) (endValue - fraction * (endValue - startValue));
        }
    }

    /** ValueAnimator之Evaluator3 */
    private void animWithEvaluator3() {
        ValueAnimator valueAnimatorBG = ValueAnimator.ofInt(0xffff0000, 0xff0000ff);
        ValueAnimator valueAnimatorTV = ValueAnimator.ofInt(0xff000000, 0xffffffff);
        valueAnimatorBG.setDuration(3000);
        valueAnimatorBG.addUpdateListener(animation -> {
            int valueInt = (int) animation.getAnimatedValue();
            tv.setBackgroundColor(valueInt);
        });
        valueAnimatorBG.setEvaluator(new ArgbEvaluator());    // 颜色渐变
        valueAnimatorBG.start();

        valueAnimatorTV.setDuration(3000);
        valueAnimatorTV.addUpdateListener(animation -> {
            int valueInt = (int) animation.getAnimatedValue();
            tv.setTextColor(valueInt);
        });
        valueAnimatorTV.setEvaluator(new ArgbEvaluator());    // 颜色渐变
        valueAnimatorTV.start();
    }

    /** ofObject案例 */
    private void animWithOfObject() {
        ValueAnimator animator = ValueAnimator.ofObject(new CharEvaluator(), 'A', 'Z');
        animator.setDuration(5_000);
        animator.addUpdateListener(animation -> {
            tv_ch.setText(animation.getAnimatedValue().toString());
        });
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    class CharEvaluator implements TypeEvaluator<Character> {

        @Override public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startValueInt = (int) startValue, endValueInt = (int) endValue;

            return (char) (startValueInt + fraction * (endValueInt - startValueInt));
        }
    }


    /**  */
    private void animPointView() {
        ((PointView) findViewById(R.id.pv)).startAnim();
    }


}

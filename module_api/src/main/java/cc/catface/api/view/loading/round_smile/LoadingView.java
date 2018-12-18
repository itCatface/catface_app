package cc.catface.api.view.loading.round_smile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * 依据开发中文站上的一篇博客   ：http://www.androidchina.net/4108.html#rd?sukey=7f8f3cb2e9b0da450bd23d8e3019db9d58643aad72b43f0d20ef7f6820601fb7317ff68b552dae696058ffce7f77b48f
 * Created by Administrator on 2016/1/21.
 */
public class LoadingView extends View {

    /**
     * 0画圆,1抛出方块,2下落变粗,挤压圆形,3,绘制三叉，圆形恢复,4,绿色勾,5,红色感叹号出现，6,红色感叹号震动
     */
    private int status = 0;

    // 表示当前的绘制任务   默认开始绘制圆弧
    private int startus = drawCircle;

    private static final int drawCircle = 0;
    private static final int drawFlyRect = 1;
    /**
     * 把两个交叉的方块变成球  然后下落到圆心
     */
    private static final int drawBall = 2;
    /**
     * 绘制笑脸的两个眼睛 平移和 放大
     */
    private static final int drawMouthAndEyes = 3;

    /**
     * 最开始绘制的圆环的半径
     */
    private float radius = 0;

    private int strokeWidth = 20;
    private int rectWidth = 12;
    private int mouthWidth = 23;
    /**
     * Recf 用来表示一个矩形区域
     */
    private RectF mRectf = new RectF();    //  中心圆环的矩形区域
    private RectF mRectf_mouth = new RectF();   //   笑脸所在的圆弧绘制所需的矩形


    private final float startAngle = -90;
    private int progress = 0;
    private final int maxProgress = 100;

    /**
     * 绘制圆环的画笔
     */
    private Paint circlePaint;
    private Paint rectPaint;
    private Paint ballPaint;    //   绘制圆球的画笔
    private Paint mouthPaint;  //   绘制圆脸的画笔

    /**
     * 方块飞行轨迹圆弧的结束角度
     */
    private float endAngle;
    /**
     * 当前嘴巴的角度
     */
    private float currentMouthAngle;

    private int ballRadius = 15;    //    设置下落时圆球的半径
    private int bigBallRadius = 30;   //    笑脸两个眼睛的半径
    private float currentAngle = 0f;  // 估值器计算出的当前方块所处角度值
    private float currentPosition = ballRadius * 1.0f;
    private cc.catface.api.view.loading.round_smile.PointAndSizeOfEyes currentPoint;  //  表示当前眼睛平移属性类


    private ValueAnimator animation_fly;    //  方块弧形抛出的估值器
    private ValueAnimator animation_down;  //   圆球落下的估值器
    private ValueAnimator animation_traslate;   //    眼睛平移的属性动画
    private ValueAnimator animation_mouth;     //  绘制嘴巴的动画

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //  圆弧画笔的初始化
        circlePaint = new Paint();
        circlePaint.setColor(0xffff0000);
        circlePaint.setStrokeWidth(strokeWidth);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAntiAlias(true);

        rectPaint = new Paint();
        rectPaint.setColor(0xff0000ff);    //      十六进制的颜色值      这里setColor方法  必须用   ARGB 的表示法
        rectPaint.setStrokeWidth(rectWidth);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setAntiAlias(true);

        ballPaint = new Paint();
        ballPaint.setStyle(Paint.Style.FILL);
        ballPaint.setColor(Color.argb(255, 0, 150, 136));    //  绿色
        ballPaint.setAntiAlias(true);

        mouthPaint = new Paint();
        mouthPaint.setStyle(Paint.Style.STROKE);
        mouthPaint.setStrokeWidth(mouthWidth);
        mouthPaint.setColor(Color.argb(255, 0, 150, 136));   // 绿色
        mouthPaint.setAntiAlias(true);

        endAngle = (float) Math.atan(4f / 3);
        initAnimatorFlyRect();

    }

    /**
     * 两个方块同时飞的动画
     */
    public void initAnimatorFlyRect() {
        animation_fly = ValueAnimator.ofFloat(0f, endAngle);
        animation_fly.setDuration(1000);
        animation_fly.setInterpolator(new DecelerateInterpolator());     //  定义了动画变化的速率
        // AccelerateDecelerateInterpolator表示  在开始和结束的时候减速   中间加速
        // DecelerateInterpolator 表示逐渐减速
        // AccelerateInterpolator  表示一直加速
        // LinearInterpolator    表示匀速

        animation_fly.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentAngle = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animation_fly.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                currentAngle = 0f;
                status = drawBall;
                circlePaint.setColor(Color.argb(255, 0, 150, 136));    //   把画圆的Paint变成 绿色
                postInvalidate();
                post(new Runnable() {
                    @Override
                    public void run() {
                        initAnimationDown();
                        animation_down.start();
                    }
                });
            }
        });
    }

    /**
     * 之所以在方块飞完之后才初始化   是因为圆的半径的计算 是在  onSizeChange()  方法中它是在构造方法前调用
     */
    public void initAnimationDown() {
        animation_down = ValueAnimator.ofFloat(ballRadius, radius * 2 + strokeWidth);
        animation_down.setDuration(600);
        animation_down.setInterpolator(new AccelerateInterpolator());
        animation_down.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPosition = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        animation_down.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                status = drawMouthAndEyes;

                currentPosition = radius * 2 + strokeWidth;
                initAnimatorTraslateBall();
                initAnimatorMouth();
                animation_traslate.start();
                animation_mouth.start();
            }
        });
    }

    /**
     * 初始化平移两个眼睛对应的球的属性动画
     */
    public void initAnimatorTraslateBall() {
        //     Y 轴眼睛的偏移量只有  1/4 radius ，   X轴  1/3 radius
        animation_traslate = ValueAnimator.ofObject(new cc.catface.api.view.loading.round_smile.PointAndSizeEvaluator(), new cc.catface.api.view.loading.round_smile.PointAndSizeOfEyes(currentPosition, currentPosition, ballRadius), new cc.catface.api.view.loading.round_smile.PointAndSizeOfEyes(currentPosition - radius / 3, currentPosition - radius / 4, bigBallRadius));
        animation_traslate.setDuration(1200);
        animation_traslate.setInterpolator(new AccelerateDecelerateInterpolator());
        animation_traslate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (cc.catface.api.view.loading.round_smile.PointAndSizeOfEyes) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        animation_traslate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
    }

    /**
     * 绘制嘴巴的动画
     */
    public void initAnimatorMouth() {


        //  笑脸所在外切矩形的设定
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //
        //   8/3 = 2    整型相除等于2     必须用   8f/3f = 2.667
        //
        //  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        mRectf_mouth.set(new RectF((4f/3f) * radius +  strokeWidth, (4f/3f) * radius +  strokeWidth, (8f/3f) * radius + strokeWidth, (8f/3f) * radius + strokeWidth));

        animation_mouth = ValueAnimator.ofFloat(30 * 1.0f, 110 * 1.0f);    //   这里计算的是 嘴巴逆时针增长的角度   从小到大
        animation_mouth.setDuration(1200);
        animation_mouth.setInterpolator(new DecelerateInterpolator());
        animation_mouth.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentMouthAngle = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animation_mouth.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSpecSize + 10 * strokeWidth, heightSpecSize + 10 * strokeWidth);
    }

    /**
     * 这里的 onLayout 可以不写出来     因为没有对布局进行改变
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //  首先  绘制一个自旋的圆弧    圆弧逐渐变成完整的圆环      具体是调用canvas.drawArc() 绘制圆弧
        //  开始位置   起点  -90 ，终点   -150
        float percent = 1.0f * progress / maxProgress;        //当前进度    0-1f
        // 绘制圆弧  需要一块矩形区域   然后把一个椭圆最大限度的填充进这个矩形    取起点和终点那部分圆弧
        //  false参数表示是否绘制半径     若为true  会从椭圆重心开始绘制两条半径  和起点终点围成一个封闭图形
        //  第一个参数是起始的角度    第二个是开始 顺时针测量的扫描角度
        // 难点就是这个扫描角度的计算    起始-60  是起始和终点差     然后扫描角度最后的值是 ？
        //    圆弧原来是60度  最后重合了  那么就是360度  所以多出了300度    然后乘以百分比就行了
        //   用的是角度制   不是弧度制
        canvas.drawArc(mRectf, startAngle - 270 * percent, -60 - (300 * percent), false, circlePaint);

        switch (status) {
            case drawFlyRect:
                drawFlyRect(canvas);
                break;
            case drawBall:   //  绘制下落的圆球
                canvas.drawCircle(radius * 2 + strokeWidth, currentPosition, ballRadius, ballPaint);   //  X轴坐标不变    Y轴由估值器提供
                break;
            case drawMouthAndEyes:
                canvas.drawCircle(currentPoint.getX(), currentPoint.getY(), currentPoint.getEyeRadius(), ballPaint);  //   绘制左边的眼睛
                canvas.drawCircle(2 * currentPosition - currentPoint.getX(), currentPoint.getY(), currentPoint.getEyeRadius(), ballPaint);  //  绘制右边的眼睛
                canvas.drawArc(mRectf_mouth, 145, -currentMouthAngle, false, mouthPaint);
        }

    }

    /**
     * 当View 大小改变时调用     在onDraw调用之前调用
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        radius = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 4 - strokeWidth;
        //   矩形区域的构建  需要左上   右下的坐标       当让不包括右下的点
        //   设置矩形区域的参数是 左上 和  右下点的坐标
        mRectf.set(new RectF(radius + strokeWidth, radius + strokeWidth, 3 * radius + strokeWidth, 3 * radius + strokeWidth));
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 方块抛出动画  下面动画分析：
     * 假设此时圆心为（0，0）   那方块出发点是（R , 0）    且方块运动到圆形正上方  距离2R   可以看成是一段圆弧
     * mipmap-xhdpi-hdpi 中存放了轨迹分析图
     * (X+R)^2 + （2R）^2 = (X+2R)^2
     * 解得X=R/2
     * OK接下来就需要用到属性动画    ValueAnimator
     * 需要告诉我们在 时间s 内，这个方块所处在的轨迹（圆弧）对应的角度   Math.atan(4f/3)   反正切函数算得
     *
     * @param canvas
     */
    public void drawFlyRect(Canvas canvas) {

        canvas.save();
        canvas.translate(radius / 2 + strokeWidth, 2 * radius + strokeWidth);//将坐标移动到大圆圆心(方块轨迹所在的那个大圆)
        //  两个参数分别是平移的 X 轴距离  和  Y  轴距离
        float bigRadius = 5 * radius / 2;//大圆半径
        //方块起始端坐标     起始点坐标就是currentAngle 对应在圆弧上的点   注意这里我们规定纬度低的为起始端
        float x1 = (float) (bigRadius * Math.cos(currentAngle));        //  圆弧上的点在X轴上的投影长度
        float x11 = (float) (3 * radius - (bigRadius * Math.cos(currentAngle)));
        float y1 = -(float) (bigRadius * Math.sin(currentAngle));       //  圆弧上的点在Y轴上的投影长度
        //方块末端坐标    末端点最难定   它也是圆弧上的一个点    和起始点相连成的直线就是方快
        //  所以起始点 和 末端点 构成的圆弧角度  我们不能用固定的值  毕竟希望这个方块上升时长度在减小
        float huAngle = (float) (0.15 * endAngle - 0.12 * endAngle * (currentAngle / endAngle));
        float x2 = (float) (bigRadius * Math.cos(currentAngle + huAngle));
        float x22 = (float) (3 * radius - (bigRadius * Math.cos(currentAngle + huAngle)));
        float y2 = -(float) (bigRadius * Math.sin(currentAngle + huAngle));
        canvas.drawLine(x1, y1, x2, y2, rectPaint);//小方块，其实是一条直线
        canvas.drawLine(x11, y1, x22, y2, rectPaint);
        canvas.restore();

    }

    /**
     * 外界使用加载动画  必然对应一个设置当前加载进度的方法
     *
     * @param progress 0-100 （int）
     */
    public void setProgress(int progress) {


        if (progress == 0) {
            //   由于ColorEvaluator 中的保存颜色值的静态变量   所有每次重置进度 也需要重置这些变量
            status = 0;  //                         每次重新运行  改变动画的运行标志位
            currentPosition = ballRadius;
            ColorEvaluator.resetColor();
        }
        float fraction = 1.0f * (progress) / 100;    //   范围是 1-(0-1)    之所以 用 “1-” 为了最后的颜色是蓝色  但是又不能 (#ff0000 ,#0000ff)
        String color_str = ColorEvaluator.evaluate(fraction, "#0000ff", "#ff0000");
        int color = Color.parseColor(color_str);
        Log.e("wxy", "asdasdasd   " + fraction);
        circlePaint.setColor(color);
        this.progress = progress;
        postInvalidate();   //   之所有在这里使用postInvalidate  因为我是开了个线程去更新

        if (progress == 100) {   // 开始方块抛出动画
            status = drawFlyRect;    //  先改变运行状态  因为在下面的post线程会调用onDraw
            post(new Runnable() {     //    这里的post方法     其实就是View中专门设计的方法
                //  Causes the Runnable to be added to the message queue.
                //   The runnable will be run on the user interface thread   这两行英文是它的官方解释
                // 这个post产生的Runnable将运行在主线程中         这就解释了 为什么用了它  Mainactivity中就不用Looper.prepare()方法 ，
                //  而且也满足了  这个ValueAnimator必须用在主线程中
                // 从源码中看 View的 post  方法 开启了一个mHandler.post(runnable);
                @Override
                public void run() {
                    animation_fly.start();
                }
            });
        }
    }

    /**
     * 返回当前进度值
     *
     * @return
     */
    public int getProgress() {
        return progress;
    }

}

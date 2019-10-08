package cc.catface.api.view.loading.view;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import cc.catface.api.R;
import cc.catface.ctool.system.TScreen;

public class LoadingView extends View {
    private Paint paint;
    private Context mcontext;

    private int reacWidth;
    private int ovalHeight;   //椭圆高度
    private int ovalHeightTop;  //椭圆距离可动区域的高度
    private int distance;  //可以滑动的位置大小

    private int mheight; //整个view高度
    private int mwidth; //整个view宽度
    private LoadingShape shape;

    private ValueAnimator shapeAnimator;
    private int centery;  //中心点y轴高度  三角形或者方形 或者圆形 y轴上的最高点（最小y值）  如果需要修改，不让 动画每次都撞到控件边界可以修改，动画取值的范围。

    private int minOvalWidth;
    private boolean isUp = true;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        init();
        initAnimator();
    }

    private void initAnimator() {
        shapeAnimator =ValueAnimator.ofInt(distance,0);
        shapeAnimator.setRepeatCount(-1);
        shapeAnimator.setRepeatMode(ValueAnimator.REVERSE);
        shapeAnimator.setDuration(500);
        final TimeInterpolator upInterpolator = new DecelerateInterpolator();   //减速插值器
        final TimeInterpolator downInterpolator = new AccelerateInterpolator();
        shapeAnimator.setInterpolator(upInterpolator);  //由于插值器是根据事件流失的百分比来影响值得，没办法做到快速下降，缓慢上升
        shapeAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isUp = true;
                Log.d("xulc","onStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("xulc","结束了");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                isUp=!isUp;
                if(isUp){       //切换图形
                    shapeAnimator.setInterpolator(upInterpolator);
                    switch(shape){
                        case CIR:
                            shape = LoadingShape.RECT;
                            break;
                        case RECT:
                            shape = LoadingShape.TRI;
                            break;
                        case TRI:
                            shape = LoadingShape.CIR;
                            break;
                    }
                }else{
                    shapeAnimator.setInterpolator(downInterpolator);
                }
            }
        });

        shapeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                centery = (int)animation.getAnimatedValue();
                invalidate();
            }
        });
        shapeAnimator.start();
    }

    @SuppressLint("ResourceType") private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setColor(mcontext.getResources().getColor(R.color.colorAccent));

        reacWidth = TScreen.dp2px(mcontext,20);  //20dp
        ovalHeight = TScreen.dp2px(mcontext,4);  //椭圆高度
        ovalHeightTop = TScreen.dp2px(mcontext,10);  //椭圆距离可动区域的高度
        distance = TScreen.dp2px(mcontext,50);  //可以滑动的位置大小
        minOvalWidth =  reacWidth/4;
        centery = distance;
        shape = LoadingShape.TRI;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = reacWidth;
        int height = reacWidth+distance+ovalHeight+ovalHeightTop;
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mwidth = w;
        mheight = h;
    }

    @SuppressLint("ResourceType") @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) @Override
    protected void onDraw(Canvas canvas) {
        switch(shape){
            case CIR:
                paint.setColor(getResources().getColor(R.color.colorPrimary));
                canvas.drawCircle(mwidth/2,centery+mwidth/2,mwidth/2,paint);
                break;
            case TRI:
                Path triPath = new Path();
                paint.setColor(getResources().getColor(R.color.colorPrimaryDark));
                triPath.moveTo(0,centery+reacWidth);
                triPath.lineTo(reacWidth,centery+reacWidth);
                triPath.lineTo(reacWidth/2,centery);
                triPath.close();
                canvas.drawPath(triPath,paint);
                break;
            case RECT:
                paint.setColor(getResources().getColor(R.color.colorAccent));
                canvas.drawRect(0,centery,mwidth,centery+reacWidth,paint);
                break;
        }
        paint.setColor(getResources().getColor(R.color.colorAccent));
        int startx = (int)(((mwidth - minOvalWidth)/2.0f)*((distance-centery)*1.0f/distance));
        canvas.drawOval(startx,mheight-ovalHeight,mwidth-startx,mheight,paint);  //centery 减小 //向上的过程中缩小
           //减去这个得到实际显示的宽度
        super.onDraw(canvas);
    }

    private enum LoadingShape{
        TRI,RECT,CIR
    }
}
package cc.catface.api.view.loading.round_smile;

/**
 * Created by laowang on 2016/1/26.
 *
 * 自定义了一个TypeEvaluator 去计算眼睛的位置和大小   所以需要用到ValueAnimator.ofObject()  方法
 * 所以需要一个自定义的属性类
 */
public class PointAndSizeOfEyes {

    private float X;
    private float Y;
    private float eyeRadius;

    public PointAndSizeOfEyes(float x, float y, float eyeRadius) {
        X = x;
        Y = y;
        this.eyeRadius = eyeRadius;
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public float getEyeRadius() {
        return eyeRadius;
    }

    public void setEyeRadius(float eyeRadius) {
        this.eyeRadius = eyeRadius;
    }
}

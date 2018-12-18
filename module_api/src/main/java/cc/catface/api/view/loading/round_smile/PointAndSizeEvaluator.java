package cc.catface.api.view.loading.round_smile;

import android.animation.TypeEvaluator;

/**
 * Created by Administrator on 2016/1/26.
 */
public class PointAndSizeEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        cc.catface.api.view.loading.round_smile.PointAndSizeOfEyes point_start = (cc.catface.api.view.loading.round_smile.PointAndSizeOfEyes) startValue;
        cc.catface.api.view.loading.round_smile.PointAndSizeOfEyes point_end = (cc.catface.api.view.loading.round_smile.PointAndSizeOfEyes) endValue;

        //  需要注意的是    平移后的坐标X 还是 Y 都是变小的   所以  endValue < startValue
        return new cc.catface.api.view.loading.round_smile.PointAndSizeOfEyes(point_start.getX() - (point_start.getX() - point_end.getX()) * fraction, point_start.getY() -
                (point_start.getY() - point_end.getY()) * fraction, point_start.getEyeRadius() + fraction * (point_end.getEyeRadius() - point_start.getEyeRadius()));
    }

}

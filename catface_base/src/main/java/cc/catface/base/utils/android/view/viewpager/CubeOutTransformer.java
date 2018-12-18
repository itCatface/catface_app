package cc.catface.base.utils.android.view.viewpager;

import android.view.View;

import cc.catface.base.utils.android.view.viewpager.base.BaseTransformer;

public class CubeOutTransformer extends BaseTransformer {
    @Override protected void onTransform(View view, float position) {
        view.setPivotX(position < 0f ? view.getWidth() : 0f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationY(90f * position);
    }

    @Override public boolean isPagingEnabled() {
        return true;
    }
}
package cc.catface.base.utils.android.view.viewpager;

import android.view.View;

import cc.catface.base.utils.android.view.viewpager.base.BaseTransformer;

public class StackTransformer extends BaseTransformer {
    @Override protected void onTransform(View view, float position) {
        view.setTranslationX(position < 0 ? 0f : -view.getWidth() * position);
    }
}
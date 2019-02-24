package cc.catface.base.utils.android.view.simple;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by wyh
 */

public class BarTextView extends AppCompatTextView {
    public BarTextView(Context context) {
        this(context, null);
    }

    public BarTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    // 强制让TextView具有焦点
    @Override public boolean isFocused() {
        return true;
    }
}

package cc.catface.base.utils.android.view.bg;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TBG {


    public static void setBackgroundAlpha(Context ctx, float alpha) {
        WindowManager.LayoutParams lp = ((Activity) ctx).getWindow().getAttributes();
        lp.alpha = alpha;
        ((Activity) ctx).getWindow().setAttributes(lp);
    }

}

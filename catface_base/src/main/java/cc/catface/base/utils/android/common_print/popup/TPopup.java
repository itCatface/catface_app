package cc.catface.base.utils.android.common_print.popup;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cc.catface.base.R;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.base.utils.android.view.bg.TBG;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TPopup {


    private Context mCtx;
    private PopupWindow mPop;

    public static TPopup get(Context ctx) {
        return new TPopup(ctx);
    }


    private TPopup(Context ctx) {
        mCtx = ctx;
    }


    public void show(View v, String title, String[] items, TListView.Callback callback) {
        if (null != mPop && mPop.isShowing()) return;
        View view = LayoutInflater.from(mCtx).inflate(R.layout.popup_layout_default_view, null);
        mPop = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mPop.setBackgroundDrawable(new BitmapDrawable());
        mPop.setFocusable(true);
        mPop.setOutsideTouchable(true);
        mPop.setAnimationStyle(R.style.popup_style_anim_default);
        mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        TBG.setBackgroundAlpha(mCtx, 0.5f);
        mPop.setOnDismissListener(() -> TBG.setBackgroundAlpha(mCtx, 1));

        /* event */
        ((TextView) view.findViewById(R.id.tv_title)).setText(title);
        ListView lv_popup = (ListView) view.findViewById(R.id.lv_popup);
        TListView.str(mPop, mCtx, lv_popup, items, callback);
        view.findViewById(R.id.tv_cancel).setOnClickListener(tv_cancel -> mPop.dismiss());
    }

    public void dismiss() {
        if (null != mPop && mPop.isShowing()) mPop.dismiss();
    }
}

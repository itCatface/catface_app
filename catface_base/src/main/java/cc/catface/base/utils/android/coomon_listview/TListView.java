package cc.catface.base.utils.android.coomon_listview;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

import cc.catface.base.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TListView {


    /** 纯文本列表 */
    public interface Callback {
        void onClick(int pos);
    }

    public static void str(Context ctx, ListView lv, String[] items, final Callback callback) {
        str(null, ctx, lv, items, callback);
    }

    public static void str(PopupWindow popup, Context ctx, ListView lv, String[] items, final Callback callback) {
        lv.setAdapter(new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, items));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (null != popup && popup.isShowing()) popup.dismiss();
                if (null != callback) callback.onClick(i);
            }
        });
    }


    /** icon+label列表 */
    public static void iconStr(Context ctx, ListView lv, List<Map<String, Object>> list, String[] keys, final Callback callback) {
        lv.setAdapter(new SimpleAdapter(ctx, list, R.layout.listview_layout_item_default_icon_str, new String[]{keys[0], keys[1]}, new int[]{R.id.iv_icon, R.id.tv_label}));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (null != callback) callback.onClick(i);
            }
        });
    }
}

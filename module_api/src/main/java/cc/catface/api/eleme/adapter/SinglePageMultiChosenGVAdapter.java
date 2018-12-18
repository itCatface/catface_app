package cc.catface.api.eleme.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import cc.catface.api.R;
import java.util.List;

import cc.catface.api.eleme.domain.ElemeSinglePageMultiChosenPersonInfo;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SinglePageMultiChosenGVAdapter extends BaseAdapter {

    private Context mCtx;
    private List<ElemeSinglePageMultiChosenPersonInfo> mDatas;

    public SinglePageMultiChosenGVAdapter(Context ctx, List<ElemeSinglePageMultiChosenPersonInfo> datas) {
        mCtx = ctx;
        mDatas = datas;
    }


    @Override public int getCount() {
        return mDatas.size();
    }

    @Override public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override public long getItemId(int i) {
        return i;
    }

    @Override public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.api_item_adapter_gv_single_page_multi_chosen, viewGroup, false);
            holder.cb = (CheckBox) convertView.findViewById(R.id.cb);
            holder.tv_label = (TextView) convertView.findViewById(R.id.tv_label);
            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();


        /* 事件 */
        holder.tv_label.setText(mDatas.get(i).getText());
        if (mDatas.get(i).isChosen()) {
            holder.cb.setChecked(true);
            holder.tv_label.setTextColor(Color.parseColor("#0989F9"));
        } else {
            holder.cb.setChecked(false);
            holder.tv_label.setTextColor(Color.parseColor("#666666"));
        }

        return convertView;
    }


    private static class ViewHolder {
        private CheckBox cb;
        private TextView tv_label;
    }
}

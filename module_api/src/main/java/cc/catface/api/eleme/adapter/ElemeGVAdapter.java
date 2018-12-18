package cc.catface.api.eleme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import cc.catface.api.R;
import cc.catface.api.eleme.domain.ElemeMainBean;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ElemeGVAdapter extends BaseAdapter {

    private Context mCtx;
    private List<ElemeMainBean> mDatas;
    private int mIndex;
    private int mMaxSize;

    public ElemeGVAdapter(Context ctx, List<ElemeMainBean> datas, int index, int maxPages) {
        mCtx = ctx;
        mDatas = datas;
        mIndex = index;
        mMaxSize = maxPages;
    }


    @Override public int getCount() {
        return mDatas.size() > (mIndex + 1) * mMaxSize ? mMaxSize : mDatas.size() - mIndex * mMaxSize;
    }

    @Override public Object getItem(int i) {
        return mDatas.get(i + mIndex * mMaxSize);
    }

    @Override public long getItemId(int i) {
        return i + mIndex * mMaxSize;
    }

    @Override public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.api_item_adapter_gv_eleme_main_pages, viewGroup, false);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();

        int pos = i + mIndex * mMaxSize;
        holder.iv.setImageResource(pos % 2 == 0 ? R.mipmap.ic_launcher : R.mipmap.ic_launcher_round);
        holder.tv.setText(mDatas.get(pos).getLabel());

        return convertView;
    }


    private static class ViewHolder {
        public ImageView iv;
        private TextView tv;
    }
}

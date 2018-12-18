package cc.catface.api.eleme.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cc.catface.api.R;
import java.util.List;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TextGVAdapter extends BaseAdapter {

    private Context mCtx;
    private List<String> mDatas;
    private int mIndex;
    private int mMaxSize;

    public TextGVAdapter(Context ctx, List<String> datas, int index, int maxPages) {
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
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.api_item_adapter_gv_text_main_pages, viewGroup, false);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();


        /* 事件 */
        int realPosition = i + mCurrentIndex * mMaxSize;
        holder.tv.setText(mDatas.get(realPosition));
        if (!mIsSelected) holder.tv.setTextColor(Color.parseColor("#666666"));
        else {
            if (realPosition == mPosition) {
                holder.tv.setTextColor(Color.parseColor("#0989F9"));
            } else {
                holder.tv.setTextColor(Color.parseColor("#666666"));
            }
        }


        return convertView;
    }


    private static class ViewHolder {
        private TextView tv;
    }


    /*  */
    private int mCurrentIndex, mPosition;
    private boolean mIsSelected;

    public void setSelectedPosition(int currentIndex, int position, boolean isSelected) {
        mCurrentIndex = currentIndex;
        mPosition = position;
        mIsSelected = isSelected;
    }
}

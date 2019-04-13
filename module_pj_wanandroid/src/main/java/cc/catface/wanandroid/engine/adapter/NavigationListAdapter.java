package cc.catface.wanandroid.engine.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.engine.domain.NavigationData;
import cc.catface.wanandroid.engine.domain.SubscriptionsListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class NavigationListAdapter extends RecyclerView.Adapter<NavigationListAdapter.Holder> {

    private NavigationData.Data mData;
    private int mId;

    public NavigationListAdapter(NavigationData.Data data,int id ) {
        this.mData = data;
        this.mId = id;

    }

    @NonNull @Override public NavigationListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wanandroid_adapter_item_navigation_list, parent, false));
    }

    @SuppressLint("SetTextI18n") @Override public void onBindViewHolder(@NonNull NavigationListAdapter.Holder holder, int position) {
        TLog.d("na->adapter: " + mId + " || " + mData.getName());
        holder.tvTitle.setText(mData.getArticles().get(position).getTitle());
        holder.tvDate.setText(mData.getArticles().get(position).getNiceDate());
    }

    @Override public int getItemCount() {
        return mData.getArticles().size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDate;

        Holder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}

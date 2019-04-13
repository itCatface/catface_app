package cc.catface.wanandroid.engine.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cc.catface.base.GlideApp;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.engine.domain.ProjectsListData;
import cc.catface.wanandroid.engine.domain.SubscriptionsListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SubscriptionsListAdapter extends RecyclerView.Adapter<SubscriptionsListAdapter.Holder> {

    private Context mCtx;
    private SubscriptionsListData mData = new SubscriptionsListData();

    public SubscriptionsListAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public void setData(SubscriptionsListData data) {
        this.mData = data;
    }

    @NonNull @Override public SubscriptionsListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wanandroid_adapter_item_subscriptions_list, parent, false));
    }

    @SuppressLint("SetTextI18n") @Override public void onBindViewHolder(@NonNull SubscriptionsListAdapter.Holder holder, int position) {
        SubscriptionsListData.Datas datas = mData.getData().getDatas().get(position);
        holder.tvAuthor.setText(datas.getAuthor());
        holder.tvChapter.setText(datas.getSuperChapterName() + "/" + datas.getChapterName());
        holder.tvTitle.setText(datas.getTitle());
        holder.tvDate.setText(datas.getNiceDate());
    }

    @Override public int getItemCount() {
        return mData.getData().getDatas().size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvAuthor, tvChapter, tvTitle, tvDate;

        Holder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            tvChapter = (TextView) itemView.findViewById(R.id.tv_chapter);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}

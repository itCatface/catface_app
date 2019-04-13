package cc.catface.wanandroid.engine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.engine.domain.KnowledgeData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.ViewHolder> {

    private KnowledgeData mData = new KnowledgeData();

    public void setData(KnowledgeData data) {
        this.mData = data;
    }

    @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wanandroid_adapter_item_knowledge, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvKnowledgeTitle.setText(mData.getData().get(position).getName());
        StringBuilder sb = new StringBuilder();
        List<KnowledgeData.Data.Children> children = mData.getData().get(position).getChildren();
        for (KnowledgeData.Data.Children data : children) {
            sb.append(data.getName()).append("   ");
        }
        holder.tvKnowledgeColumns.setText(sb);
    }

    @Override public int getItemCount() {
        return mData.getData().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKnowledgeTitle, tvKnowledgeColumns;
        ImageView ivGo;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKnowledgeTitle = (TextView) itemView.findViewById(R.id.tv_knowledge_title);
            tvKnowledgeColumns = (TextView) itemView.findViewById(R.id.tv_knowledge_columns);
            ivGo = (ImageView) itemView.findViewById(R.id.iv_go);
        }
    }
}
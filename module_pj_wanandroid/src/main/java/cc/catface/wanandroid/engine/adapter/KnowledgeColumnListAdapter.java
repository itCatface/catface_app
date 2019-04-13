package cc.catface.wanandroid.engine.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.engine.domain.KnowledgeColumnListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class KnowledgeColumnListAdapter extends RecyclerView.Adapter<KnowledgeColumnListAdapter.Holder> {

    private KnowledgeColumnListData mData = new KnowledgeColumnListData();

    public void setData(KnowledgeColumnListData data) {
        this.mData = data;
    }

    @NonNull @Override public KnowledgeColumnListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wanandroid_adapter_item_knoledge_column_list, parent, false));
    }

    @SuppressLint("SetTextI18n") @Override public void onBindViewHolder(@NonNull KnowledgeColumnListAdapter.Holder holder, int position) {
        KnowledgeColumnListData.Data.Datas articleInfo = mData.getData().getDatas().get(position);
        holder.tvAuthor.setText(articleInfo.getAuthor());
        holder.tvChapter.setText(articleInfo.getSuperChapterName() + "/" + articleInfo.getChapterName());
        holder.tvDesc.setText(articleInfo.getTitle());
        holder.tvDate.setText(articleInfo.getNiceDate());
    }

    @Override public int getItemCount() {
        return mData.getData().getDatas().size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        private TextView tvAuthor, tvChapter, tvDesc, tvDate;

        Holder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            tvChapter = (TextView) itemView.findViewById(R.id.tv_chapter);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}

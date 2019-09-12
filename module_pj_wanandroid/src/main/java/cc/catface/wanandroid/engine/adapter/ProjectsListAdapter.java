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

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ProjectsListAdapter extends RecyclerView.Adapter<ProjectsListAdapter.Holder> {

    private Context mCtx;
    private ProjectsListData mData = new ProjectsListData();

    public ProjectsListAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public void setData(ProjectsListData data) {
        this.mData = data;
    }

    @NonNull @Override public ProjectsListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wanandroid_adapter_item_projects_list, parent, false));
    }

    @SuppressLint("SetTextI18n") @Override public void onBindViewHolder(@NonNull ProjectsListAdapter.Holder holder, int position) {
        ProjectsListData.Data.Datas datas = mData.getData().getDatas().get(position);
        GlideApp.with(mCtx).load(datas.getEnvelopePic()).into(holder.ivPic);
        holder.tvTitle.setText(datas.getTitle());
        holder.tvDesc.setText(datas.getDesc());
        holder.tvDate.setText(datas.getNiceDate());
        holder.tvAuthor.setText(datas.getAuthor());
    }

    @Override public int getItemCount() {
        return mData.getData().getDatas().size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private ImageView ivPic;
        private TextView tvTitle, tvDesc, tvDate, tvAuthor;

        Holder(@NonNull View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            tvTitle.setSelected(true);
        }
    }
}

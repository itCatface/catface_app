package cc.catface.wanandroid.engine.adapter;

import java.util.List;

import cc.catface.base.GlideApp;
import cc.catface.ctool.view.recyclerview.AdapterList;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidAdapterItemProjectsListBinding;
import cc.catface.wanandroid.engine.domain.ProjectsListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ProjectsListAdapter extends AdapterList<ProjectsListData.Data.Datas, WanandroidAdapterItemProjectsListBinding> {

    public ProjectsListAdapter(List<ProjectsListData.Data.Datas> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.wanandroid_adapter_item_projects_list;
    }

    @Override public void onBindHolder(WanandroidAdapterItemProjectsListBinding binding, int position) {
        ProjectsListData.Data.Datas datas = getDatas().get(position);
        GlideApp.with(binding.getRoot().getContext()).load(datas.getEnvelopePic()).into(binding.ivPic);
        binding.tvTitle.setText(datas.getTitle());
        binding.tvDesc.setText(datas.getDesc());
        binding.tvDate.setText(datas.getNiceDate());
        binding.tvAuthor.setText(datas.getAuthor());
    }
}
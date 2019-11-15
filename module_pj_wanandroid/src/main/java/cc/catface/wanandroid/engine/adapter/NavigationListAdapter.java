package cc.catface.wanandroid.engine.adapter;

import java.util.List;

import cc.catface.ctool.view.recyclerview.AdapterList;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidAdapterItemNavigationListBinding;
import cc.catface.wanandroid.engine.domain.NavigationData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class NavigationListAdapter extends AdapterList<NavigationData.Articles, WanandroidAdapterItemNavigationListBinding> {

    public NavigationListAdapter(List<NavigationData.Articles> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.wanandroid_adapter_item_navigation_list;
    }

    @Override public void onBindHolder(WanandroidAdapterItemNavigationListBinding binding, int position) {
        NavigationData.Articles article = getDatas().get(position);
        binding.tvTitle.setText(article.getTitle());
        binding.tvDate.setText(article.getNiceDate());
    }
}

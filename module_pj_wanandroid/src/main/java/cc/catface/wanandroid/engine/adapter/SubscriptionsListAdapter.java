package cc.catface.wanandroid.engine.adapter;

import android.annotation.SuppressLint;

import java.util.List;

import cc.catface.ctool.view.recyclerview.AdapterList;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidAdapterItemSubscriptionsListBinding;
import cc.catface.wanandroid.engine.domain.SubscriptionsListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SubscriptionsListAdapter extends AdapterList<SubscriptionsListData.Datas, WanandroidAdapterItemSubscriptionsListBinding> {

    public SubscriptionsListAdapter(List<SubscriptionsListData.Datas> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.wanandroid_adapter_item_subscriptions_list;
    }

    @SuppressLint("SetTextI18n") @Override public void onBindHolder(WanandroidAdapterItemSubscriptionsListBinding binding, int position) {
        SubscriptionsListData.Datas data = getDatas().get(position);
        binding.tvAuthor.setText(data.getAuthor());
        binding.tvChapter.setText(data.getSuperChapterName() + "/" + data.getChapterName());
        binding.tvTitle.setText(data.getTitle());
        binding.tvDate.setText(data.getNiceDate());
    }
}
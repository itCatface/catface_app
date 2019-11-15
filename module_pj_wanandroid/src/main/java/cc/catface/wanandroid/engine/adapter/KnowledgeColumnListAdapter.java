package cc.catface.wanandroid.engine.adapter;

import android.annotation.SuppressLint;

import java.util.List;

import cc.catface.ctool.view.recyclerview.AdapterList;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidAdapterItemKnoledgeColumnListBinding;
import cc.catface.wanandroid.engine.domain.KnowledgeColumnListData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class KnowledgeColumnListAdapter extends AdapterList<KnowledgeColumnListData.Data.Datas, WanandroidAdapterItemKnoledgeColumnListBinding> {

    public KnowledgeColumnListAdapter(List<KnowledgeColumnListData.Data.Datas> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.wanandroid_adapter_item_knoledge_column_list;
    }

    @SuppressLint("SetTextI18n") @Override public void onBindHolder(WanandroidAdapterItemKnoledgeColumnListBinding binding, int position) {
        KnowledgeColumnListData.Data.Datas articleInfo = getDatas().get(position);
        binding.tvAuthor.setText(articleInfo.getAuthor());
        binding.tvChapter.setText(articleInfo.getSuperChapterName() + "/" + articleInfo.getChapterName());
        binding.tvDesc.setText(articleInfo.getTitle());
        binding.tvDate.setText(articleInfo.getNiceDate());
    }
}

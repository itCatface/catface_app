package cc.catface.wanandroid.engine.adapter;

import java.util.List;

import cc.catface.ctool.view.recyclerview.AdapterList;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidAdapterItemKnowledgeBinding;
import cc.catface.wanandroid.engine.domain.KnowledgeData;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class KnowledgeAdapter extends AdapterList<KnowledgeData.Data, WanandroidAdapterItemKnowledgeBinding> {

    public KnowledgeAdapter(List<KnowledgeData.Data> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.wanandroid_adapter_item_knowledge;
    }

    @Override public void onBindHolder(WanandroidAdapterItemKnowledgeBinding binding, int position) {
        KnowledgeData.Data data = getDatas().get(position);
        binding.tvKnowledgeTitle.setText(data.getName());
        StringBuilder sb = new StringBuilder();
        List<KnowledgeData.Data.Children> children = data.getChildren();
        for (KnowledgeData.Data.Children child : children) {
            sb.append(child.getName()).append("   ");
        }
        binding.tvKnowledgeColumns.setText(sb);
    }
}
package cc.catface.api.view.adapter;

import java.util.List;

import cc.catface.api.R;
import cc.catface.app_base.databinding.CommonItemSingleTextviewBinding;
import cc.catface.ctool.view.recyclerview.AdapterList;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ViewNavigationAdapter extends AdapterList<String, CommonItemSingleTextviewBinding> {

    public ViewNavigationAdapter(List<String> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.common_item_single_textview;
    }

    @Override public void onBindHolder(CommonItemSingleTextviewBinding binding, int position) {
        binding.tvItem.setText(getDatas().get(position));
    }
}

package cc.catface.api.viewpager2;

import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiItemViewPager2Binding;
import cc.catface.ctool.view.recyclerview.AdapterList;

public class ViewPager2Adapter extends AdapterList<Integer, ApiItemViewPager2Binding> {
    public ViewPager2Adapter(List<Integer> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.api_item_view_pager2;
    }

    @Override public void onBindHolder(ApiItemViewPager2Binding binding, int position) {
        position %= getDatas().size();
        binding.tvTitle.setText("item " + position);
        binding.rlContainer.setBackgroundResource(getDatas().get(position));
    }

    @Override public int getItemCount() {
        return Integer.MAX_VALUE;
    }
}
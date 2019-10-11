package cc.catface.api.viewpager2;

import java.util.ArrayList;
import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiItemViewPager2Binding;
import cc.catface.ctool.view.recyclerview.CustomBindingAdapter;

public class ViewPager2Adapter extends CustomBindingAdapter<ApiItemViewPager2Binding> {
    private List<Integer> colors = new ArrayList<>();

    {
        colors.add(android.R.color.holo_red_dark);
        colors.add(android.R.color.holo_purple);
        colors.add(android.R.color.holo_blue_dark);
        colors.add(android.R.color.holo_green_light);
    }

    @Override public int layoutId() {
        return R.layout.api_item_view_pager2;
    }

    @Override public void onBindHolder(ApiItemViewPager2Binding binding, int position) {
        binding.tvTitle.setText("item " + position);
        binding.rlContainer.setBackgroundResource(colors.get(position));
    }

    @Override public int getItemCount() {
        return colors.size();
    }
}
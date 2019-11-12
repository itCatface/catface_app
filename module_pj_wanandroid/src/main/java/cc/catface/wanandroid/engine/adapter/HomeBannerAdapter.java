package cc.catface.wanandroid.engine.adapter;

import java.util.List;

import cc.catface.base.GlideApp;
import cc.catface.ctool.view.recyclerview.AdapterList;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidItemHomeBannerBinding;
import cc.catface.wanandroid.engine.domain.Banner;
import cc.catface.wanandroid.module.web.WebActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class HomeBannerAdapter extends AdapterList<Banner.Data, WanandroidItemHomeBannerBinding> {
    public HomeBannerAdapter(List<Banner.Data> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.wanandroid_item_home_banner;
    }

    @Override public void onBindHolder(WanandroidItemHomeBannerBinding binding, int position) {
        Banner.Data data = getDatas().get(position % getDatas().size());
        GlideApp.with(binding.getRoot()).load(data.getImagePath()).into(binding.iv);
        binding.iv.setOnClickListener(v -> WebActivity.jump(binding.getRoot().getContext(), data.getUrl()));
    }

    @Override public int getItemCount() {
        return Integer.MAX_VALUE;
    }
}

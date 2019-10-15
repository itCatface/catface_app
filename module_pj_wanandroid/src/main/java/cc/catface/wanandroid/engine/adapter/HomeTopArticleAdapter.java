package cc.catface.wanandroid.engine.adapter;

import java.util.List;

import cc.catface.ctool.view.recyclerview.ListBindingAdapter;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidItemHomeTopArticleBinding;
import cc.catface.wanandroid.engine.domain.TopArticle;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class HomeTopArticleAdapter extends ListBindingAdapter<TopArticle.Data, WanandroidItemHomeTopArticleBinding> {
    public HomeTopArticleAdapter(List<TopArticle.Data> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.wanandroid_item_home_top_article;
    }

    @Override public void onBindHolder(WanandroidItemHomeTopArticleBinding binding, int position) {
        TopArticle.Data data = getDatas().get(position);
        binding.tvAuthor.setText(data.getAuthor());
        binding.tvSuperChapterName.setText(data.getSuperChapterName());
        binding.tvChapterName.setText(data.getChapterName());
        binding.tvTitle.setText(data.getTitle());
    }
}

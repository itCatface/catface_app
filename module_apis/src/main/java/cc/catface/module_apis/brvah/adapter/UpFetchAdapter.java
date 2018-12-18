package cc.catface.module_apis.brvah.adapter;

import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.domain.Movie;
import cc.catface.module_apis.brvah.engine.BaseDataBindingAdapter;
import cc.catface.module_apis.databinding.BrvahItemDbMovieBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class UpFetchAdapter extends BaseDataBindingAdapter<Movie, BrvahItemDbMovieBinding> {
    public UpFetchAdapter() {
        super(R.layout.brvah_item__db__movie, null);
    }

    @Override protected void convert(BrvahItemDbMovieBinding binding, Movie item) {
        binding.setMovie(item);
    }
}


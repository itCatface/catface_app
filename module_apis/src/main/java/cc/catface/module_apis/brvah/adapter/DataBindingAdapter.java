package cc.catface.module_apis.brvah.adapter;


import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cc.catface.module_apis.BR;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.domain.Movie;
import cc.catface.module_apis.brvah.domain.MoviePresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DataBindingAdapter extends BaseQuickAdapter<Movie, DataBindingAdapter.MovieViewHolder> {

    private MoviePresenter mPresenter;

    public DataBindingAdapter(int layoutResId, List<Movie> data) {
        super(layoutResId, data);

        mPresenter = new MoviePresenter();
    }

    @Override protected void convert(MovieViewHolder helper, Movie item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.movie, item);
        binding.setVariable(BR.presenter, mPresenter);
        binding.executePendingBindings();
        switch (helper.getLayoutPosition() % 2) {
            case 0:
                helper.setImageResource(R.id.iv, R.mipmap.brvah_m_img1);
                break;
            case 1:
                helper.setImageResource(R.id.iv, R.mipmap.brvah_m_img2);
                break;

        }
    }

    @Override protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    public static class MovieViewHolder extends BaseViewHolder {

        public MovieViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }
}

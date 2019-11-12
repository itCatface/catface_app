package cc.catface.showapi.joke.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.catface.ctool.system.TScreen;
import cc.catface.ctool.view.recyclerview.AdapterList;
import cc.catface.showapi.R;
import cc.catface.showapi.databinding.ShowapiItemYyJoke3413Binding;
import cc.catface.showapi.joke.domain.YYJoke341_3;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class YYJoke341_3Adapter extends AdapterList<YYJoke341_3.Showapi_res_body.Contentlist, ShowapiItemYyJoke3413Binding> {

    public YYJoke341_3Adapter(List<YYJoke341_3.Showapi_res_body.Contentlist> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.showapi_item_yy_joke_341_3;
    }

    private Map<Integer, Integer> mHeight = new HashMap<>();

    @Override public void onBindHolder(ShowapiItemYyJoke3413Binding binding, int position) {
        YYJoke341_3.Showapi_res_body.Contentlist data = getDatas().get(position);
        Glide.with(binding.giv).asGif().transition(new DrawableTransitionOptions().crossFade(800)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)).apply(RequestOptions.timeoutOf(3000)).load(data.getImg()).listener(new RequestListener<GifDrawable>() {
            @Override public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                int ivWidth = TScreen.getScreenWidth();
                int ivHeight = ivWidth / resource.getMinimumWidth() * resource.getMinimumHeight();
                mHeight.put(position, ivHeight);

                binding.tvGif.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = binding.giv.getLayoutParams();
                layoutParams.width = ivWidth;
                layoutParams.height = ivHeight;
                binding.giv.setLayoutParams(layoutParams);
                binding.tvGif.setText(data.getTitle());
                return false;
            }
        }).into(binding.giv);


        if (mHeight.containsKey(position)) {
            ViewGroup.LayoutParams layoutParams = binding.giv.getLayoutParams();
            layoutParams.height = mHeight.get(position);
            binding.giv.setLayoutParams(layoutParams);
            binding.tvGif.setText(data.getTitle());
        }
    }
}
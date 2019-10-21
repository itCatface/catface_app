package cc.catface.showapi.joke.adapter;

import android.graphics.Bitmap;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import cc.catface.ctool.system.TScreen;
import cc.catface.ctool.view.recyclerview.ListBindingAdapter;
import cc.catface.showapi.R;
import cc.catface.showapi.databinding.ShowapiItemYyJoke3412Binding;
import cc.catface.showapi.joke.domain.YYJoke341_2;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class YYJoke341_2Adapter extends ListBindingAdapter<YYJoke341_2.Showapi_res_body.Contentlist, ShowapiItemYyJoke3412Binding> {

    public YYJoke341_2Adapter(List<YYJoke341_2.Showapi_res_body.Contentlist> datas) {
        super(datas);
    }

    @Override public int layoutId() {
        return R.layout.showapi_item_yy_joke_341_2;
    }

    @Override public void onBindHolder(ShowapiItemYyJoke3412Binding binding, int position) {
        YYJoke341_2.Showapi_res_body.Contentlist data = getDatas().get(position);

        binding.tvJokePic.setText(data.getTitle());


        //        GlideApp.with(holder.iv_joke_pic).load(mDatas.get(i).getImg()).into(holder.iv_joke_pic);


        Glide.with(binding.ivJokePic).asBitmap().load(data.getImg()).transition(BitmapTransitionOptions.withCrossFade(300)).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher)).apply(RequestOptions.errorOf(R.mipmap.ic_launcher_round)).listener(new RequestListener<Bitmap>() {
            @Override public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                //                int ivWidth = TScreen.getScreenWidth(holder.iv_joke_pic.getContext()) - holder.iv_joke_pic.getContext().getResources().getDimensionPixelSize(R.dimen.dp_1) * 4;
                //                int ivHeight = ivWidth / resource.getWidth() * resource.getHeight();
                //
                //                arr.put(i, ivHeight);
                //
                //                ViewGroup.LayoutParams layoutParams = holder.iv_joke_pic.getLayoutParams();
                //                layoutParams.width = ivWidth;
                //                layoutParams.height = ivHeight;
                //                holder.iv_joke_pic.setLayoutParams(layoutParams);

                ViewGroup.LayoutParams layoutParams = binding.ivJokePic.getLayoutParams();
                int itemWidth = (TScreen.getScreenWidth() - 8 * 3) / 2;
                int itemHeight = (int) (resource.getHeight() * (itemWidth + 0.0f) / resource.getWidth());
                layoutParams.width = (int) itemWidth;
                layoutParams.height = (int) itemHeight;
                binding.ivJokePic.setLayoutParams(layoutParams);


                return false;
            }
        }).into(binding.ivJokePic);
    }
}
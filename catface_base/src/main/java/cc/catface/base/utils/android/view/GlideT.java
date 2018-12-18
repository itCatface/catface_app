package cc.catface.base.utils.android.view;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestOptions;



/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class GlideT {

    public static void load(ImageView iv, String url) {
        Glide.with(iv).asBitmap().transition(BitmapTransitionOptions.withCrossFade(300)).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).load(url).into(iv);
//        Glide.with(iv).asBitmap().transition(BitmapTransitionOptions.withCrossFade(300)).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher)).apply(RequestOptions.errorOf(R.drawable.iv_err_place)).load(url).into(iv);

    }

}

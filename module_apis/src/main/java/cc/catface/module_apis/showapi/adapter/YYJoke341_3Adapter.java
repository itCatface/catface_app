package cc.catface.module_apis.showapi.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import cc.catface.base.utils.android.TScreen;
import cc.catface.module_apis.R;
import cc.catface.module_apis.showapi.domain.YYJoke341_3;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class YYJoke341_3Adapter extends RecyclerView.Adapter<YYJoke341_3Adapter.Holder> {

    private List<YYJoke341_3.Showapi_res_body.Contentlist> mDatas;

    public YYJoke341_3Adapter(List<YYJoke341_3.Showapi_res_body.Contentlist> datas) {
        this.mDatas = datas;
    }


    static class Holder extends RecyclerView.ViewHolder {
        GifImageView giv;
        TextView tv_gif;
        ProgressBar pb;

        Holder(@NonNull View itemView) {
            super(itemView);
            giv = (GifImageView) itemView.findViewById(R.id.giv);
            tv_gif = (TextView) itemView.findViewById(R.id.tv_gif);
            pb = (ProgressBar) itemView.findViewById(R.id.pb);
        }
    }

    @NonNull @Override public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.showapi_item_yy_joke_341_3, viewGroup, false));
    }


    private Map<Integer, Integer> mHeight = new HashMap<>();

    @SuppressLint("CheckResult") @Override public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") int i) {
        Glide.with(holder.giv).asGif().transition(new DrawableTransitionOptions().crossFade(800)).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)).apply(RequestOptions.timeoutOf(3000)).load(mDatas.get(i).getImg()).listener(new RequestListener<GifDrawable>() {
            @Override public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                int ivWidth = TScreen.getScreenWidth(holder.giv.getContext());
                int ivHeight = ivWidth / resource.getMinimumWidth() * resource.getMinimumHeight();
                mHeight.put(i, ivHeight);

                holder.tv_gif.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = holder.giv.getLayoutParams();
                layoutParams.width = ivWidth;
                layoutParams.height = ivHeight;
                holder.giv.setLayoutParams(layoutParams);
                holder.tv_gif.setText(mDatas.get(i).getTitle());
                return false;
            }
        }).into(holder.giv);


        if (mHeight.containsKey(i)) {
            ViewGroup.LayoutParams layoutParams = holder.giv.getLayoutParams();
            layoutParams.height = mHeight.get(i);
            holder.giv.setLayoutParams(layoutParams);
            holder.tv_gif.setText(mDatas.get(i).getTitle());
        }
    }

    @Override public int getItemCount() {
        return mDatas.size();
    }
}

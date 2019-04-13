package cc.catface.showapi.joke.adapter;

import android.graphics.Bitmap;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import cc.catface.base.utils.android.TScreen;
import cc.catface.showapi.R;
import cc.catface.showapi.joke.domain.YYJoke341_2;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class YYJoke341_2Adapter extends RecyclerView.Adapter<YYJoke341_2Adapter.Holder> {

    private List<YYJoke341_2.Showapi_res_body.Contentlist> mDatas;

    public YYJoke341_2Adapter(List<YYJoke341_2.Showapi_res_body.Contentlist> datas) {
        this.mDatas = datas;
    }


    static class Holder extends RecyclerView.ViewHolder {
        TextView tv_joke_pic;
        ImageView iv_joke_pic;

        Holder(@NonNull View itemView) {
            super(itemView);
            tv_joke_pic = (TextView) itemView.findViewById(R.id.tv_joke_pic);
            iv_joke_pic = (ImageView) itemView.findViewById(R.id.iv_joke_pic);
        }
    }

    @NonNull @Override public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.showapi_item_yy_joke_341_2, viewGroup, false));
    }


    private SparseIntArray arr = new SparseIntArray();

    @Override public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.tv_joke_pic.setText(mDatas.get(i).getTitle());


        //        GlideApp.with(holder.iv_joke_pic).load(mDatas.get(i).getImg()).into(holder.iv_joke_pic);


        Glide.with(holder.iv_joke_pic).asBitmap().load(mDatas.get(i).getImg()).transition(BitmapTransitionOptions.withCrossFade(300)).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
        ).apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher)).apply(RequestOptions.errorOf(R.mipmap.ic_launcher_round)).listener(new RequestListener<Bitmap>() {
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

                ViewGroup.LayoutParams layoutParams = holder.iv_joke_pic.getLayoutParams();
                int itemWidth = (TScreen.getScreenWidth(holder.iv_joke_pic.getContext()) - 8 * 3) / 2;
                int itemHeight = (int) (resource.getHeight() * (itemWidth + 0.0f) / resource.getWidth());
                layoutParams.width = (int) itemWidth;
                layoutParams.height = (int) itemHeight;
                holder.iv_joke_pic.setLayoutParams(layoutParams);


                return false;
            }
        }).into(holder.iv_joke_pic);

    }

    @Override public int getItemCount() {
        return mDatas.size();
    }
}

package cc.catface.base.utils.android.view.recyclerview.banner.adapter;

/**
 * Created by test on 2017/11/23.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import cc.catface.base.utils.android.view.recyclerview.banner.RecyclerViewBannerBase;


/**
 * RecyclerView适配器
 */
public class RecyclerAdapterNormal extends BaseBannerAdapter<RecyclerAdapterNormal.NormalHolder> {

    private RecyclerViewBannerBase.OnBannerItemClickListener onBannerItemClickListener;

    public RecyclerAdapterNormal(Context context, List<String> urlList, RecyclerViewBannerBase.OnBannerItemClickListener onBannerItemClickListener) {
        super(context, urlList);
        this.onBannerItemClickListener = onBannerItemClickListener;
    }

    @Override protected RecyclerAdapterNormal.NormalHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new NormalHolder(new ImageView(context));
    }

    @Override public void bindCustomViewHolder(NormalHolder holder, final int position) {
        if (urlList == null || urlList.isEmpty()) return;
        String url = urlList.get(position % urlList.size());
        ImageView img = (ImageView) holder.itemView;
        Glide.with(context).load(url).into(img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (onBannerItemClickListener != null) {
                    onBannerItemClickListener.onItemClick(position % urlList.size());
                }
            }
        });
    }

    class NormalHolder extends RecyclerView.ViewHolder {
        ImageView bannerItem;

        NormalHolder(View itemView) {
            super(itemView);
            bannerItem = (ImageView) itemView;
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            bannerItem.setLayoutParams(params);
            bannerItem.setScaleType(ImageView.ScaleType.FIT_XY);

        }
    }

}

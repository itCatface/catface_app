package cc.catface.api.toutiao.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import cc.catface.api.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cc.catface.api.toutiao.domain.ToutiaoCommonBean;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.android.view.GlideT;


public class ToutiaoAdapter extends RecyclerView.Adapter {

    public static final int TYPE_TOP_TEXT = 101;                        // 置顶
    public static final int TYPE_TEXT_IMAGE1_RIGHT = 102;               // 文+右边一张图
    public static final int TYPE_TEXT_IMAGE1_BOTTOM = 1002;             // 文+右边一张图
    public static final int TYPE_TEXT_IMAGE3 = 103;                     // 文+底部三张图
    public static final int TYPE_TEXT_VIDEO_RIGHT = 104;                // 文+右边一视频
    public static final int TYPE_TEXT_VIDEO_BOTTOM = 105;               // 文+底部一视频
    public static final int TYPE_AD_IMAGE1_BOTTOM = 106;                // 广告+底部一张图
    public static final int TYPE_AD_IMAGE1_BOTTOM_DOWNLOAD = 108;       // 广告+底部一张图+下载按钮
    public static final int TYPE_MORE_SPLENDID = 109;                   // 更多精彩视频


    private Context mCtx;
    private List<ToutiaoCommonBean> mData;

    public ToutiaoAdapter(Context ctx, List<ToutiaoCommonBean> data) {
        mCtx = ctx;
        this.mData = data;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TOP_TEXT:
                return new TopTextHolder(View.inflate(parent.getContext(), R.layout.api_item_toutiao_top_text, null));

            case TYPE_TEXT_IMAGE1_RIGHT:
                return new TextImage1RightHolder(View.inflate(parent.getContext(), R.layout.api_item_toutiao_text_image1_right, null));

            case TYPE_TEXT_IMAGE1_BOTTOM:
                return new TextImage1BottomHolder(View.inflate(parent.getContext(), R.layout.api_item_toutiao_text_image1_bottom, null));

            case TYPE_TEXT_IMAGE3:
                return new TextImage3Holder(View.inflate(parent.getContext(), R.layout.api_item_toutiao_text_image3, null));

            case TYPE_TEXT_VIDEO_RIGHT:
                return new TextVideo1Right(View.inflate(parent.getContext(), R.layout.api_item_toutiao_text_video1_right, null));

            case TYPE_TEXT_VIDEO_BOTTOM:
                return new TextVideo1Bottom(View.inflate(parent.getContext(), R.layout.api_item_toutiao_text_video1_bottom, null));

            case TYPE_AD_IMAGE1_BOTTOM:
                return new AdImage1Bottom(View.inflate(parent.getContext(), R.layout.api_item_toutiao_ad_image1_bottom, null));

            case TYPE_AD_IMAGE1_BOTTOM_DOWNLOAD:
                return new AdImage1Download(View.inflate(parent.getContext(), R.layout.api_item_toutiao_ad_image1_bottom_download, null));

            case TYPE_MORE_SPLENDID:
                return new MoreSplendid(View.inflate(parent.getContext(), R.layout.api_item_toutiao_more_splendid, null));

            default:
                return new TopTextHolder(View.inflate(parent.getContext(), R.layout.api_item_toutiao_top_text, null));
        }
    }

    @SuppressLint("SetTextI18n") @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ToutiaoCommonBean data = mData.get(position);
        if (holder instanceof TopTextHolder) {
            animWithEvaluator(((TopTextHolder) holder).tv_commentTimes);

            ((TopTextHolder) holder).tv_title.setText(data.getTopText().getTitle());
            ((TopTextHolder) holder).tv_text.setText(data.getTopText().getText());
            ((TopTextHolder) holder).tv_mediaSrc.setText(data.getTopText().getMediaSrc());
            ((TopTextHolder) holder).tv_commentTimes.setText(data.getTopText().getCommentTimes() + "评论");
            ((TopTextHolder) holder).tv_time.setText(data.getTopText().getTime());
        } else if (holder instanceof TextImage1RightHolder) {
            ((TextImage1RightHolder) holder).tv_title.setText(data.getTextImage1().getTitle());
            GlideT.load(((TextImage1RightHolder) holder).iv, data.getTextImage1().getImageUrl());
            ((TextImage1RightHolder) holder).tv_mediaSrc.setText(data.getTextImage1().getMediaSrc());
            ((TextImage1RightHolder) holder).tv_commentTimes.setText(data.getTextImage1().getCommentTimes() + "评论");
            ((TextImage1RightHolder) holder).tv_time.setText(data.getTextImage1().getTime());
        } else if (holder instanceof TextImage1BottomHolder) {
            ((TextImage1BottomHolder) holder).tv_title.setText(data.getTextImage1().getTitle());
            GlideT.load(((TextImage1BottomHolder) holder).iv, data.getTextImage1().getImageUrl());
            ((TextImage1BottomHolder) holder).tv_mediaSrc.setText(data.getTextImage1().getMediaSrc());
            ((TextImage1BottomHolder) holder).tv_commentTimes.setText(data.getTextImage1().getCommentTimes() + "评论");
            ((TextImage1BottomHolder) holder).tv_time.setText(data.getTextImage1().getTime());
        } else if (holder instanceof TextImage3Holder) {
            ((TextImage3Holder) holder).tv_title.setText(data.getTextImage3().getTitle());
            GlideT.load(((TextImage3Holder) holder).iv_1, data.getTextImage3().getUrl1());
            GlideT.load(((TextImage3Holder) holder).iv_2, data.getTextImage3().getUrl2());
            GlideT.load(((TextImage3Holder) holder).iv_3, data.getTextImage3().getUrl3());
            ((TextImage3Holder) holder).tv_mediaSrc.setText(data.getTextImage3().getMediaSrc());
            ((TextImage3Holder) holder).tv_commentTimes.setText(data.getTextImage3().getCommentTimes() + "评论");
            ((TextImage3Holder) holder).tv_time.setText(data.getTextImage3().getTime());
        } else if (holder instanceof TextVideo1Right) {
            ((TextVideo1Right) holder).tv_title.setText(data.getTextVideo1().getTitle());
            // TODO 加载视屏
            ((TextVideo1Right) holder).tv_mediaSrc.setText(data.getTextVideo1().getMediaSrc());
            ((TextVideo1Right) holder).tv_commentTimes.setText(data.getTextVideo1().getCommentTimes() + "评论");
            ((TextVideo1Right) holder).tv_time.setText(data.getTextVideo1().getTime());
        } else if (holder instanceof TextVideo1Bottom) {
            ((TextVideo1Bottom) holder).tv_title.setText(data.getTextVideo1().getTitle());
            // TODO 加载视屏
            ((TextVideo1Bottom) holder).tv_mediaSrc.setText(data.getTextVideo1().getMediaSrc());
            ((TextVideo1Bottom) holder).tv_commentTimes.setText(data.getTextVideo1().getCommentTimes() + "评论");
            ((TextVideo1Bottom) holder).tv_time.setText(data.getTextVideo1().getTime());
        } else if (holder instanceof AdImage1Bottom) {
            ((AdImage1Bottom) holder).tv_title.setText(data.getAdImage1Bottom().getTitle());
            GlideT.load(((AdImage1Bottom) holder).iv, data.getAdImage1Bottom().getImageUrl());
            ((AdImage1Bottom) holder).tv_label.setText(data.getAdImage1Bottom().getLabel());
            ((AdImage1Bottom) holder).tv_time.setText(data.getAdImage1Bottom().getTime());
            ((AdImage1Bottom) holder).tv_detail.setOnClickListener(view -> TToast.get(mCtx).showBShortView(data.getAdImage1Bottom().getLabel() + "，用过都说好！", TToast.B_SUCCESS));
        } else if (holder instanceof AdImage1Download) {
            ((AdImage1Download) holder).tv_title.setText(data.getAdImage1Bottom().getTitle());
            GlideT.load(((AdImage1Download) holder).iv, data.getAdImage1Bottom().getImageUrl());
            ((AdImage1Download) holder).tv_label.setText(data.getAdImage1Bottom().getLabel());
            ((AdImage1Download) holder).tv_time.setText(data.getAdImage1Bottom().getTime());
            ((AdImage1Download) holder).tv_download.setOnClickListener(view -> TToast.get(mCtx).showBShortView(data.getAdImage1Bottom().getLabel() + "，下载中...", TToast.B_SUCCESS));
        } else if (holder instanceof MoreSplendid) {
            ((MoreSplendid) holder).tv_more.setOnClickListener(view -> TToast.get(mCtx).showBShortView("查看更多小视频...", TToast.B_SUCCESS));

            List<View> mImageViews = new ArrayList<>();
            for (int i = 0; i < data.getMoreSplendid().getUrls().length; i++) {
                ImageView iv = new ImageView(((MoreSplendid) holder).vp_more.getContext());
                mImageViews.add(iv);
                GlideT.load(iv, data.getMoreSplendid().getUrls()[new Random().nextInt(data.getMoreSplendid().getUrls().length)]);
            }
            ((MoreSplendid) holder).vp_more.setClipToPadding(false);
            ((MoreSplendid) holder).vp_more.setPadding(40, 0, 40, 0);
            ((MoreSplendid) holder).vp_more.setPageMargin(20);
            ((MoreSplendid) holder).vp_more.setOffscreenPageLimit(3);
            ((MoreSplendid) holder).vp_more.setAdapter(new MoreSplendidViewPagerAdapter(mImageViews));
            ((MoreSplendid) holder).vp_more.setCurrentItem(1);
        }
    }

    @Override public int getItemCount() {
        return null == mData ? 0 : mData.size();
    }

    //根据条件返回条目的类型
    @Override public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    /**
     * 创建三种ViewHolder
     */
    private class TopTextHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_text, tv_mediaSrc, tv_commentTimes, tv_time;

        TopTextHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
            tv_mediaSrc = (TextView) itemView.findViewById(R.id.tv_mediaSrc);
            tv_commentTimes = (TextView) itemView.findViewById(R.id.tv_commentTimes);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    private void animWithEvaluator(TextView tv) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0xffff0000, 0xff0000ff);
        valueAnimator.setDuration(1_500);
        valueAnimator.addUpdateListener(animation -> {
            int valueInt = (int) animation.getAnimatedValue();
            tv.setTextColor(valueInt);
        });
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.start();
    }


    private class TextImage1RightHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_mediaSrc, tv_commentTimes, tv_time;
        private ImageView iv;

        TextImage1RightHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            tv_mediaSrc = (TextView) itemView.findViewById(R.id.tv_mediaSrc);
            tv_commentTimes = (TextView) itemView.findViewById(R.id.tv_commentTimes);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }


    private class TextImage1BottomHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_mediaSrc, tv_commentTimes, tv_time;
        private ImageView iv;

        TextImage1BottomHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            tv_mediaSrc = (TextView) itemView.findViewById(R.id.tv_mediaSrc);
            tv_commentTimes = (TextView) itemView.findViewById(R.id.tv_commentTimes);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }


    private class TextImage3Holder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_mediaSrc, tv_commentTimes, tv_time;
        private ImageView iv_1, iv_2, iv_3;

        TextImage3Holder(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_mediaSrc = (TextView) itemView.findViewById(R.id.tv_mediaSrc);
            tv_commentTimes = (TextView) itemView.findViewById(R.id.tv_commentTimes);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            iv_1 = (ImageView) itemView.findViewById(R.id.iv_title_1);
            iv_2 = (ImageView) itemView.findViewById(R.id.iv_2);
            iv_3 = (ImageView) itemView.findViewById(R.id.iv_3);
        }
    }


    private class TextVideo1Right extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_mediaSrc, tv_commentTimes, tv_time;
        private VideoView vv;

        TextVideo1Right(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            vv = (VideoView) itemView.findViewById(R.id.vv);
            tv_mediaSrc = (TextView) itemView.findViewById(R.id.tv_mediaSrc);
            tv_commentTimes = (TextView) itemView.findViewById(R.id.tv_commentTimes);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }


    private class TextVideo1Bottom extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_mediaSrc, tv_commentTimes, tv_time;
        private VideoView vv;

        TextVideo1Bottom(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            vv = (VideoView) itemView.findViewById(R.id.vv);
            tv_mediaSrc = (TextView) itemView.findViewById(R.id.tv_mediaSrc);
            tv_commentTimes = (TextView) itemView.findViewById(R.id.tv_commentTimes);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    private class AdImage1Bottom extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_label, tv_detail, tv_time;
        private ImageView iv;

        AdImage1Bottom(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_label = (TextView) itemView.findViewById(R.id.tv_label);
            tv_detail = (TextView) itemView.findViewById(R.id.tv_detail);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    private class AdImage1Download extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_label, tv_download, tv_time;
        private ImageView iv;

        AdImage1Download(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_label = (TextView) itemView.findViewById(R.id.tv_label);
            tv_download = (TextView) itemView.findViewById(R.id.tv_download);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    private class MoreSplendid extends RecyclerView.ViewHolder {
        private TextView tv_more;
        private ViewPager vp_more;

        MoreSplendid(@NonNull View itemView) {
            super(itemView);
            tv_more = (TextView) itemView.findViewById(R.id.tv_more);
            vp_more = (ViewPager) itemView.findViewById(R.id.vp_more);
        }
    }
}
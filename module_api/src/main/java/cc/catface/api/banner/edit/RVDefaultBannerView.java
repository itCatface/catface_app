package cc.catface.api.banner.edit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import cc.catface.api.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class RVDefaultBannerView extends FrameLayout {


    /** 轮播控制 */
    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                playerCtrl(false);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                playerCtrl(true);
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    private final int MSG_AUTO_PLAY = -0x99;
    private final int AUTO_PLAY_DURATION = 4_000;
    private int mCurrentIndex;
    @SuppressLint("HandlerLeak") private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            if (msg.what == MSG_AUTO_PLAY) {
                mRV.smoothScrollToPosition(++mCurrentIndex);
                playerCtrl(true);
            }
        }
    };


    /** 初始化和播放 */
    public RVDefaultBannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRV;
    private RecyclerView.Adapter mAdapter;
    private List<String> mDatas = new ArrayList<>();

    public void init(List<String> datas, OnItemClickListener listener) {
        mDatas = datas;
        mAdapter = new RVBannerAdapter(mDatas, listener);
        mLayoutManager = new LinearLayoutManager(getContext());
        initRV();
    }

    public void play() {
        /* 标题 & 指示器 */
        withTitle();
        withIndicators();
        /* 给RV设置适配器并开始轮播 */
        mRV.setAdapter(mAdapter);
        mCurrentIndex = mDatas.size() * 10_000;
        mRV.scrollToPosition(mCurrentIndex);
        playerCtrl(true);
    }

    private void initRV() {
        mRV = new RecyclerView(getContext());
        new PagerSnapHelper().attachToRecyclerView(mRV);
        mLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mRV.setLayoutManager(mLayoutManager);
        mRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) {
                    mCurrentIndex = mLayoutManager.findFirstVisibleItemPosition();
                }
                tv_title.setText("当前位置：" + mCurrentIndex % mDatas.size());
                updateIndicatorsStatus(mCurrentIndex % mDatas.size());
            }
        });
        addView(mRV, new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }


    private void playerCtrl(boolean isStartPlay) {
        if (isStartPlay)
            mHandler.sendEmptyMessageDelayed(MSG_AUTO_PLAY, AUTO_PLAY_DURATION);
        else
            mHandler.removeMessages(MSG_AUTO_PLAY);
    }


    /** 事件接口&适配器 */
    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemLongClick(int position);
    }

    /* 普通全屏广告页适配器 */
    private class RVBannerAdapter extends RecyclerView.Adapter<RVBannerAdapter.ViewHolder> {
        private List<String> mDatas;
        private OnItemClickListener mListener;

        RVBannerAdapter(List<String> datas, OnItemClickListener listener) {
            mListener = listener;
            mDatas = datas;
        }

        @NonNull @Override public RVBannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RVBannerAdapter.ViewHolder(new ImageView(parent.getContext()));
        }

        @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int realPosition = position % mDatas.size();

            // 可按需替换使用你项目规定的图片加载框架
            Glide.with(getContext()).load(mDatas.get(realPosition)).into(holder.iv);

            if (null != mListener) {
                holder.iv.setOnClickListener(v -> mListener.onItemClick(realPosition));
                holder.iv.setOnLongClickListener(v -> {
                    mListener.onItemLongClick(realPosition);
                    return true;
                });
            }
        }

        @Override public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView iv;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                iv = (ImageView) itemView;
                RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                iv.setLayoutParams(params);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }
    }


    /** 标题 & 指示器 */
    private TextView tv_title;

    private void withTitle() {
        tv_title = new TextView(getContext());
        tv_title.setBackgroundColor(Color.parseColor("#55333333"));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(30));
        layoutParams.gravity = Gravity.BOTTOM | Gravity.START;
        tv_title.setPadding(dp2px(10), 0, 0, 0);
        tv_title.setGravity(Gravity.CENTER_VERTICAL);
        tv_title.setTextColor(Color.parseColor("#ffffffff"));
        addView(tv_title, new LayoutParams(layoutParams));
    }


    private LinearLayout mIndicatorContainer;

    private void withIndicators() {
        mIndicatorContainer = new LinearLayout(getContext());
        mIndicatorContainer.setOrientation(LinearLayout.HORIZONTAL);
        FrameLayout.LayoutParams containerParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dp2px(30));
        containerParams.gravity = Gravity.BOTTOM | Gravity.END;
        containerParams.setMargins(0, 0, dp2px(10), 0);

        /* 初始化所有banner图及对应指示器 */
        View v_point;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < mDatas.size(); i++) {
            v_point = new View(getContext());
            layoutParams = new LinearLayout.LayoutParams(35, 35);
            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            if (0 != i) layoutParams.leftMargin = 30;
            v_point.setBackgroundResource(R.drawable.shape_package_indicator_normal);
            mIndicatorContainer.addView(v_point, layoutParams);
        }
        addView(mIndicatorContainer, containerParams);
    }

    private void updateIndicatorsStatus(int position) {
        int childCount = mIndicatorContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            mIndicatorContainer.getChildAt(i).setBackgroundResource(i == position ? R.drawable.shape_package_indicator_fouse : R.drawable.shape_package_indicator_normal);
        }
    }

    private int dp2px(float dpValue) {
        return (int) (dpValue * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }


    /** 公共设置 */
    public void setLayoutManager(int orientation) {
        mLayoutManager.setOrientation(orientation);
        mRV.setLayoutManager(mLayoutManager);
    }

    public void showTitle(boolean isShowTitle) {
        tv_title.setVisibility(isShowTitle ? View.VISIBLE : View.GONE);
    }


    public void showIndicator(boolean isShowIndicator) {
        mIndicatorContainer.setVisibility(isShowIndicator ? View.VISIBLE : View.GONE);
    }
}

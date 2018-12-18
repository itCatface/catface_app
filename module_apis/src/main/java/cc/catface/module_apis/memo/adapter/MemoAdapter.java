package cc.catface.module_apis.memo.adapter;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cc.catface.app_base.ARouterApp;
import cc.catface.app_base.greendao.Memo;
import cc.catface.app_base.greendao.domain.greendao_gen.MemoDao;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.android.view.recyclerview.drag.DragItemCallback;
import cc.catface.base.utils.android.view.recyclerview.swipe.SwipeItemLayout;
import cc.catface.base.utils.android.view.recyclerview.swipe.SwipeItemListener;
import cc.catface.module_apis.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.Holder> implements DragItemCallback.DragItemAdapter {

    private MemoDao mDao = ARouterApp.getDaoSession().getMemoDao();

    private List<Memo> mDatas;
    private RecyclerView mRv;
    private LinearLayoutManager mLayoutManager;
    private SwipeItemListener mListener;


    public MemoAdapter(List<Memo> datas, RecyclerView rv, LinearLayoutManager layoutManager, SwipeItemListener listener) {
        super();
        this.mDatas = datas;
        this.mRv = rv;
        this.mLayoutManager = layoutManager;
        this.mListener = listener;
        init();
    }

    private void init() {
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                TVibrator.cancel(mRv.getContext());
            }

            @Override public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mFirstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
                mLastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();

                if (mLayoutManager.findLastVisibleItemPosition() == mDatas.size() - 1) TToast.get(mRv.getContext()).showShortNormal("已到最深处๑乛◡乛๑");
            }
        });
    }

    class Holder extends RecyclerView.ViewHolder {
        RelativeLayout rl_memo;
        TextView tv_date, tv_content;
        SwipeItemLayout sil;
        TextView tv_left_1, tv_left_2, tv_right_menu;

        Holder(@NonNull View itemView) {
            super(itemView);
            rl_memo = (RelativeLayout) itemView.findViewById(R.id.rl_memo);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            sil = (SwipeItemLayout) itemView.findViewById(R.id.sil);
            tv_left_1 = (TextView) itemView.findViewById(R.id.tv_left_1);
            tv_left_2 = (TextView) itemView.findViewById(R.id.tv_left_2);
            tv_right_menu = (TextView) itemView.findViewById(R.id.tv_right_menu);
        }
    }


    /** 适配器方法 */
    @NonNull @Override public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.apis_item_memo, null));
    }


    private int mFirstVisibleItemPosition = 0, mLastVisibleItemPosition = 10;

    @Override public void onBindViewHolder(@NonNull Holder holder, int i) {
        if (holder.getAdapterPosition() > mLastVisibleItemPosition) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(holder.rl_memo, "scaleX", 0f, 1f);
            holder.rl_memo.setPivotX(0f);
            animator.start();
        } else if (holder.getAdapterPosition() < mFirstVisibleItemPosition) {
            ObjectAnimator.ofFloat(holder.rl_memo, "alpha", 0f, 1f).start();
        }


        if (1 == mDatas.get(i).getStars()) holder.rl_memo.setBackgroundColor(ARouterApp.getContext().getResources().getColor(R.color.pmQuality1));
        else if (2 == mDatas.get(i).getStars()) holder.rl_memo.setBackgroundColor(ARouterApp.getContext().getResources().getColor(R.color.pmQuality2));
        else if (3 == mDatas.get(i).getStars()) holder.rl_memo.setBackgroundColor(ARouterApp.getContext().getResources().getColor(R.color.pmQuality3));
        else if (4 == mDatas.get(i).getStars()) holder.rl_memo.setBackgroundColor(ARouterApp.getContext().getResources().getColor(R.color.pmQuality4));
        else if (5 == mDatas.get(i).getStars()) holder.rl_memo.setBackgroundColor(ARouterApp.getContext().getResources().getColor(R.color.pmQuality5));
        else holder.rl_memo.setBackgroundColor(ARouterApp.getContext().getResources().getColor(R.color.white));


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(mDatas.get(i).getDate());
        try {
            Date date = simpleDateFormat.parse(format);
            holder.tv_date.setText(date.getMonth() + "月" + date.getDay() + "日");
            holder.tv_content.setText(mDatas.get(i).getContent());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        swipe(holder, i);
    }

    @Override public int getItemCount() {
        return mDatas.size();
    }

    /** 逻辑 */
    private void top(int fromPosition) {
        mDatas.add(0, mDatas.remove(fromPosition));
        notifyItemMoved(fromPosition, 0);
        notifyItemRangeChanged(0, mDatas.size());
    }

    private void deleteItem(int position) {
        Memo memo = mDatas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, mDatas.size());
        mDao.delete(memo);
    }


    /** 拖动监听 */
    @Override public void onMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mDatas, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i < toPosition + 1; i++) {
                Collections.swap(mDatas, i, i - 1);
            }
            Collections.reverse(mDatas);
        }

        notifyItemMoved(fromPosition, toPosition);
    }

    @Override public void onFinishDrag() {
        notifyItemRangeChanged(0, mDatas.size());
    }

    @Override public void onSwiped(int position, boolean toLeft) {
        if (toLeft) deleteItem(position);
        else top(position);
    }


    /** swipe & click */
    private void swipe(Holder holder, int position) {
        if (0 == holder.getLayoutPosition() % 2) holder.sil.setSwipeEnable(false);

        holder.tv_left_1.setOnClickListener(view -> {
            top(position);
        });

        holder.tv_left_2.setOnClickListener(view -> {
//            TVibrator.vibrate(mRv.getContext(), 100);
            notifyItemRangeChanged(0, mDatas.size());

            holder.tv_content.setFocusable(true);
            holder.tv_content.setFocusableInTouchMode(true);
        });

        holder.tv_right_menu.setOnClickListener(view -> {
            deleteItem(position);
        });

        holder.rl_memo.setOnClickListener(view -> {
            mListener.onItemClick(holder, position, holder.tv_content.getText().toString());
        });
    }


}
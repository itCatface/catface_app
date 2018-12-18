package cc.catface.module_apis.brvah.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.core_framework.base_normal.NormalBaseFragmentID;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.DragSwipeAdapter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_DragSwipeFm extends NormalBaseFragmentID {

    private static final String TAG = BRVAH_DragSwipeFm.class.getSimpleName();
    private List<String> mData;
    private DragSwipeAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;

    @Override public int layoutId() {
        return R.layout.brvah_fm_drag_swipe;
    }

  private RecyclerView rv_list;

    @Override public void ids(View v) {
        rv_list = (RecyclerView) v.findViewById(R.id.rv_list);
    }

    @Override public void createView() {
        rv_list.setLayoutManager(new LinearLayoutManager(mActivity));
        mData = generateData(50);
        OnItemDragListener listener = new OnItemDragListener() {
            @Override public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "drag start");
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
                //                holder.setTextColor(R.id.tv, Color.WHITE);
            }

            @Override public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
                Log.d(TAG, "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
            }

            @Override public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "drag end");
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
                //                holder.setTextColor(R.id.tv, Color.BLACK);
            }
        };
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(20);
        paint.setColor(Color.BLACK);
        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "view swiped start: " + pos);
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
                //                holder.setTextColor(R.id.tv, Color.WHITE);
            }

            @Override public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "View reset: " + pos);
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
                //                holder.setTextColor(R.id.tv, Color.BLACK);
            }

            @Override public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "View Swiped: " + pos);
            }

            @Override public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                canvas.drawColor(ContextCompat.getColor(mActivity, R.color.color_light_blue));
                //                canvas.drawText("Just some text", 0, 40, paint);
            }
        };

        mAdapter = new DragSwipeAdapter(mData);
        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(rv_list);

        //mItemDragAndSwipeCallback.setDragMoveFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);
        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
        mAdapter.enableSwipeItem();
        mAdapter.setOnItemSwipeListener(onItemSwipeListener);
        mAdapter.enableDragItem(mItemTouchHelper);
        mAdapter.setOnItemDragListener(listener);
        //        rv_list.addItemDecoration(new GridItemDecoration(this ,R.drawable.list_divider));

        rv_list.setAdapter(mAdapter);
        //        rv_list.addOnItemTouchListener(new OnItemClickListener() {
        //            @Override
        //            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
        //                ToastUtils.showShortToast("点击了" + position);
        //            }
        //        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TToast.get(mActivity).showBShortView("点击了：" + position, TToast.B_INFO);
            }
        });
    }

    private List<String> generateData(int size) {
        ArrayList<String> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add("item " + i);
        }
        return data;
    }
}

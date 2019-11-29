package cc.catface.ctool.view.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenboCui on 2018/5/2.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerHolder> {
    public Context context;
    public int layoutRes;
    public List<T> items;

    public BaseRecyclerAdapter(Context context,@LayoutRes int layoutRes){
      this.context = context;
      this.layoutRes = layoutRes;
      items = new ArrayList<T>();
    }

    public BaseRecyclerAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<T> data) {
        this.context = context;
        this.items = data == null ? new ArrayList<T>() : data;
        if (layoutResId != 0) {
            this.layoutRes = layoutResId;
        }
    }

    @NonNull @Override
    public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseRecyclerHolder(LayoutInflater.from(context).inflate(layoutRes,null));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerHolder holder, int position) {
        convert(holder,position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setData(List<T> items) {
        this.items = items == null ? new ArrayList<T>() : items;
        notifyDataSetChanged();
    }

    public void addData(@IntRange(from = 0) int position, @NonNull T data) {
        items.add(position, data);
        notifyItemInserted(position);
        compatibilityDataSizeChanged(1);
    }
    public void addData( @NonNull T data) {
        items.add(data);
        notifyItemInserted(items.size());
    }


    public void remove(int position) {
        items.remove(position);
        int internalPosition = position;
        notifyItemRemoved(internalPosition);
        notifyItemRangeChanged(internalPosition, items.size() - internalPosition);
    }

    private void compatibilityDataSizeChanged(int size) {
        final int dataSize = items == null ? 0 : items.size();
        if (dataSize == size) {
            notifyDataSetChanged();
        }
    }

    /**
     * 需要重写的方法
     * @param holder
     * @param position
     */
    public abstract void convert(BaseRecyclerHolder holder, int position);
}

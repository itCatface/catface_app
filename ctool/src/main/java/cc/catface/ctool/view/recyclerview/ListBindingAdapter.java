package cc.catface.ctool.view.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class ListBindingAdapter<M, B extends ViewDataBinding> extends RecyclerView.Adapter<ListBindingAdapter.ViewHolder<B>> {

    private List<M> mDatas = new ArrayList<>();

    public ListBindingAdapter(List<M> datas) {
        if (null != datas) {
            this.mDatas = datas;
        }
    }

    public List<M> getDatas() {
        return this.mDatas;
    }


    @NonNull @Override public ViewHolder<B> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId(), parent, false);
        ViewHolder<B> holder = new ViewHolder<>(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override public void onBindViewHolder(@NonNull ViewHolder<B> holder, int position) {
        onBindHolder(holder.getBinding(), position);
    }

    @Override public int getItemCount() {
        return mDatas.size();
    }


    static class ViewHolder<B> extends RecyclerView.ViewHolder {
        private B binding;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        B getBinding() {
            return binding;
        }

        void setBinding(B binding) {
            this.binding = binding;
        }
    }


    public abstract int layoutId();

    public abstract void onBindHolder(B binding, int position);
}
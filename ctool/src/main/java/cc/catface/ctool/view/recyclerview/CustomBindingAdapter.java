package cc.catface.ctool.view.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class CustomBindingAdapter<B extends ViewDataBinding> extends RecyclerView.Adapter<CustomBindingAdapter.ViewHolder<B>> {

    @NonNull @Override public ViewHolder<B> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId(), parent, false);
        ViewHolder<B> holder = new ViewHolder<>(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override public void onBindViewHolder(@NonNull ViewHolder<B> holder, int position) {
        onBindHolder(holder.getBinding(), position);
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
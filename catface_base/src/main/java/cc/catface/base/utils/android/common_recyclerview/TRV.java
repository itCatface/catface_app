package cc.catface.base.utils.android.common_recyclerview;

import android.content.Context;

import androidx.core.content.ContextCompat;

import java.util.Objects;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cc.catface.base.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TRV {

    public static void initDefaultRV(Context ctx, RecyclerView... rvs) {
        for (RecyclerView rv : rvs) {
            if (null == rv) return;
            rv.setLayoutManager(new LinearLayoutManager(ctx));
            DividerItemDecoration decoration = new DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL);
            decoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(ctx, R.drawable.shape_rv_devider_colors)));
            rv.addItemDecoration(decoration);
        }
    }
}

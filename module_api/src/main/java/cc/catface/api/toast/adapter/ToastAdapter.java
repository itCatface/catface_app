package cc.catface.api.toast.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cc.catface.api.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ToastAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ToastAdapter(@Nullable List<String> data) {
        super(R.layout.common_item_single_textview, data);
    }

    @Override protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item, item);
    }
}

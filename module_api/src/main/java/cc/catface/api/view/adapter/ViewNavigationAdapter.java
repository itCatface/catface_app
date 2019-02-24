package cc.catface.api.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;
import cc.catface.api.R;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ViewNavigationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ViewNavigationAdapter(@Nullable List<String> data) {
        super(R.layout.common_item_single_textview, data);
    }

    @Override protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item, item);
    }
}

package cc.catface.module_apis.brvah.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.domain.QuickBean;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class QuickAdapter extends BaseQuickAdapter<QuickBean, BaseViewHolder> {


    public QuickAdapter(int layoutResId, @Nullable List<QuickBean> data) {
        super(layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder helper, QuickBean item) {
        helper.setText(R.id.tv_label, item.getLabel());
        helper.setImageBitmap(R.id.iv_icon, item.getIcon());
    }
}

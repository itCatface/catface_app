package cc.catface.module_apis.brvah.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cc.catface.module_apis.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DragSwipeAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {
    public DragSwipeAdapter(List data) {
        super(R.layout.brvah_item_draggable_view, data);
    }

    @Override protected void convert(BaseViewHolder helper, String item) {
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setImageResource(R.id.iv_head, R.mipmap.brvah_head_img0);
                break;
            case 1:
                helper.setImageResource(R.id.iv_head, R.mipmap.brvah_head_img1);
                break;
            case 2:
                helper.setImageResource(R.id.iv_head, R.mipmap.brvah_head_img2);
                break;
        }
        helper.setText(R.id.tv, item);
    }
}

package cc.catface.module_apis.brvah.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.domain.MultipleItem;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class MultiQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MultiQuickAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.TEXT, R.layout.brvah_item_text_view);
        addItemType(MultipleItem.IMG, R.layout.brvah_item_image_view);
        addItemType(MultipleItem.IMG_TEXT, R.layout.brvah_item_img_text_view);
    }

    @Override protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TEXT:
                helper.setText(R.id.tv_title, item.getContent());
                break;
            case MultipleItem.IMG:
                helper.setImageResource(R.id.iv, R.mipmap.brvah_click_head_img_0);
                break;
            case MultipleItem.IMG_TEXT:
                switch (helper.getLayoutPosition() % 2) {
                    case 0:
                        helper.setImageResource(R.id.iv, R.mipmap.brvah_animation_img1);
                        break;
                    case 1:
                        helper.setImageResource(R.id.iv, R.mipmap.brvah_animation_img2);
                        break;
                }
                break;
        }
    }
}

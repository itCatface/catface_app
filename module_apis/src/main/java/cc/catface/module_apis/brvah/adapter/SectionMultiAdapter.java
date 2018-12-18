package cc.catface.module_apis.brvah.adapter;

import com.chad.library.adapter.base.BaseSectionMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.domain.MultipleItem;
import cc.catface.module_apis.brvah.domain.SectionMultipleItem;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SectionMultiAdapter extends BaseSectionMultiItemQuickAdapter<SectionMultipleItem, BaseViewHolder> {

    public SectionMultiAdapter(int sectionHeadResId, List<SectionMultipleItem> data) {
        super(sectionHeadResId, data);
        addItemType(SectionMultipleItem.TEXT, R.layout.brvah_item_text_view);
        addItemType(SectionMultipleItem.IMG_TEXT, R.layout.brvah_item_img_text_view);
    }

    @Override protected void convertHead(BaseViewHolder helper, SectionMultipleItem item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override protected void convert(BaseViewHolder helper, SectionMultipleItem item) {
        helper.addOnClickListener(R.id.card_view);
        switch (helper.getItemViewType()) {
            case MultipleItem.TEXT:
                helper.setText(R.id.tv_title, item.getVideo().getName());
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

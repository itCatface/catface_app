package cc.catface.module_apis.brvah.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.domain.MySection;
import cc.catface.module_apis.brvah.domain.Video;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SectionAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {

    public SectionAdapter(List<MySection> data) {
        super(R.layout.brvah_item_section_content, R.layout.brvah_item_def_section_head, data);
    }

    @Override protected void convertHead(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override protected void convert(BaseViewHolder helper, MySection item) {
        Video video = (Video) item.t;
        switch (helper.getLayoutPosition() % 2) {
            case 0:
                helper.setImageResource(R.id.iv, R.mipmap.brvah_m_img1);
                break;
            case 1:
                helper.setImageResource(R.id.iv, R.mipmap.brvah_m_img2);
                break;
        }
        helper.setText(R.id.tv, video.getName());
    }
}

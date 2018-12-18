package cc.catface.module_apis.brvah.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.domain.Status;
import cc.catface.module_apis.brvah.engine.DataServer;

public class HeadFootAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {

    public HeadFootAdapter(int dataSize) {
        super(R.layout.brvah_item_head_foot, DataServer.getSampleData(dataSize));
    }

    @Override protected void convert(BaseViewHolder helper, Status item) {
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setImageResource(R.id.iv, R.mipmap.brvah_animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.iv, R.mipmap.brvah_animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.iv, R.mipmap.brvah_animation_img3);
                break;
        }
    }
}

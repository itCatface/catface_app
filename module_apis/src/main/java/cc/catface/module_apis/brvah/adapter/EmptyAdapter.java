package cc.catface.module_apis.brvah.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;

import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.domain.Status;
import cc.catface.module_apis.brvah.engine.DataServer;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class EmptyAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {

    public EmptyAdapter(int dataSize) {
        super(R.layout.brvah_item_empty, DataServer.getSampleData(dataSize));
    }

    @Override protected void convert(BaseViewHolder helper, Status item) {
        helper.addOnClickListener(R.id.tv_title);
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setImageResource(R.id.iv_icon, R.mipmap.brvah_animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.iv_icon, R.mipmap.brvah_animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.iv_icon, R.mipmap.brvah_animation_img3);
                break;
        }
        helper.setText(R.id.tv_title, "Hoteis in Rio de Janeiro");
        helper.setText(R.id.tv_content, "O ever youthful,O ever weeping");
        helper.setText(R.id.tv_date, new Date().toLocaleString());
    }
}

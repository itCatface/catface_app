package cc.catface.module_apis.brvah.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;
import java.util.Random;

import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.domain.Status;
import cc.catface.module_apis.brvah.engine.DataServer;

public class AnimationAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public AnimationAdapter() {
        super(R.layout.brvah_item_animation, DataServer.getSampleData(100));
    }

    @Override protected void convert(BaseViewHolder helper, Status item) {
        helper.addOnClickListener(R.id.iv_icon).addOnClickListener(R.id.tv_title).addOnClickListener(R.id.tv_content);
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
        helper.setText(R.id.tv_title, "The Walking Dead...");
        helper.setText(R.id.tv_date, new Date().toLocaleString());

        String msg = "\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
        helper.setText(R.id.tv_content, new Random().nextInt(100) + "|" + msg);
    }
}

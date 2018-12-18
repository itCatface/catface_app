package cc.catface.module_apis.brvah.adapter.provider;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;

import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.MultiRVAdapter;
import cc.catface.module_apis.brvah.domain.NormalMultipleEntity;

public class ImgItemProvider extends BaseItemProvider<NormalMultipleEntity, BaseViewHolder> {

    @Override public int viewType() {
        return MultiRVAdapter.TYPE_IMG;
    }

    @Override public int layout() {
        return R.layout.brvah_item_image_view;
    }

    @Override public void convert(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        if (position % 2 == 0) {
            helper.setImageResource(R.id.iv, R.mipmap.brvah_click_head_img_0);
        } else {
            helper.setImageResource(R.id.iv, R.mipmap.brvah_click_head_img_1);
        }
    }

    @Override public void onClick(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
    }

    @Override public boolean onLongClick(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}

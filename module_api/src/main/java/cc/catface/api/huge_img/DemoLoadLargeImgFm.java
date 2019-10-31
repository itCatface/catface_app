package cc.catface.api.huge_img;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityLoadHugeImgBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoLoadLargeImgFm extends LightFm<LightPresenter, ApiActivityLoadHugeImgBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_load_huge_img;
    }

    @Override protected void initView() {
        mBinding.tvCurrentValidMemory.setText((Runtime.getRuntime().maxMemory() / 1024 / 1024) + "m");
    }
}

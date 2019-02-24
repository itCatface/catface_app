package cc.catface.api.huge_img;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityLoadHugeImgBinding;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.AROUTER.api_loadHugeImg)
public class LoadHugeImgActivity extends NormalActivity<ApiActivityLoadHugeImgBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_load_huge_img;
    }

    @Override public void create() {
        title();
        initView();
    }


    private void title() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.AROUTER.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
    }


    private void initView() {
        mBinding.tvCurrentValidMemory.setText((Runtime.getRuntime().maxMemory() / 1024 / 1024) + "m");
    }
}

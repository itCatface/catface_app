package cc.catface.module_apis;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.fragment.app.Fragment;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.module_apis.databinding.ApisActivityApisHolderBinding;
import cc.catface.module_apis.half_scroll.HalfScrollFm;
import cc.catface.module_apis.test_retrofit.ApisTestRetrofitFm;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.apis_activity_holder) public class ApisHolderActivity extends NormalActivity<ApisActivityApisHolderBinding> {
    @Override public int layoutId() {
        return R.layout.apis_activity_apis_holder;
    }

    @Override public void create() {
        int fm_id = getIntent().getIntExtra(Const.ARouter.fm_id_key, -1);
        switch (fm_id) {
            case Const.ARouter.fm_id_apis_half_scroll:
                replace(new HalfScrollFm());
                break;
            case Const.ARouter.fm_id_apis_test_retrofit:
                replace(new ApisTestRetrofitFm());
                break;
        }
    }

    public void replace(Fragment fm) {
        replaceFragment(R.id.fl_apis, fm);
    }
}

package cc.catface.api;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.databinding.PagePureListviewBinding;
import cc.catface.base.utils.android.coomon_listview.TListView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.api_fm)
public class ApiFm extends LightFm<LightPresenter, PagePureListviewBinding> {

    @Override
    public int layoutId() {
        return R.layout.page_pure_listview;
    }

    @Override
    protected void initView() {
        List<String> apiList = Const.ARouter.apiFmApiList();
        TListView.str(mActivity, mBinding.lvList, apiList.toArray(new String[apiList.size()]), pos -> {
            ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, apiList.get(pos)).navigation();
        });
    }
}

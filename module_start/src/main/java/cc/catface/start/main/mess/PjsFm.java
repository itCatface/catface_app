package cc.catface.start.main.mess;

import com.alibaba.android.arouter.launcher.ARouter;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.databinding.PagePureListviewBinding;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.start.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class PjsFm extends LightFm<LightPresenter, PagePureListviewBinding> {

    @Override public int layoutId() {
        return R.layout.page_pure_listview;
    }

    private final String PJS_IFLYTEK = "讯飞开放平台示例";
    private final String PJS_SHOWAPI = "易源接口测试";
    private final String PJS_WANANDROID = "wanandroid复刻";
    private final String PJS_WECHAT = "微信界面+环信IM";
    private final String PJS_NEWS = "mvp+databinding超简单使用案例";

    private String[] mItems = {PJS_IFLYTEK, PJS_SHOWAPI, PJS_WANANDROID, PJS_WECHAT, PJS_NEWS};


    @Override protected void initView() {
        TListView.str(mActivity, mBinding.lvList, mItems, pos -> {
            switch (mItems[pos]) {
                case PJS_IFLYTEK:
                    ARouter.getInstance().build(Const.ARouter.pj_iflytek_main).navigation();
                    break;
                case PJS_SHOWAPI:
                    ARouter.getInstance().build(Const.ARouter.pj_showapi_main).navigation();
                    break;
                case PJS_WANANDROID:
                    ARouter.getInstance().build(Const.ARouter.pj_wanandroid_main).navigation();
                    break;
                case PJS_WECHAT:
                    ARouter.getInstance().build(Const.ARouter.pj_wechat_main).navigation();
                    break;
                case PJS_NEWS:
                    ARouter.getInstance().build(Const.ARouter.start_login).navigation();
                    break;
            }
        });
    }
}

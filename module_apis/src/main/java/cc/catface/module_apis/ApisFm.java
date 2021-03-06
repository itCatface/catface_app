package cc.catface.module_apis;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.databinding.PagePureListviewBinding;
import cc.catface.base.utils.android.coomon_listview.TListView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.apis_fm)
public class ApisFm extends LightFm<LightPresenter, PagePureListviewBinding> {

    @Override
    public int layoutId() {
        return R.layout.page_pure_listview;
    }

    /* 纯文本 */
    private final String PDF_READER = "demo-pdf组件使用";
    private final String BRVAH = "demo-BRVAH组件使用";
    private final String SPACE = "";
    private final String DEMO_FLOW_LAYOUT = "demo-流式布局组件使用";
    private final String DEMO_STICKY_LIST = "demo-列表粘性头部控件使用";
    private final String DEMO_NANOHTTPD_SERVER = "demo-nano httpd使用";
    private final String DEMO_HALF_SCROLL = "demo-half scroll示例";
    private final String DEMO_MEMO = "demo-green dao使用";
    private final String LOAD_IMG = "demo-各图片加载框架使用";
    private final String DEMO_LOTTIE = "demo-动画框架lottie示例";
    private final String TEST_RETROFIT = "test-测试retrofit工具类";
    private String[] mItems = {PDF_READER, BRVAH, SPACE, DEMO_FLOW_LAYOUT, DEMO_STICKY_LIST, DEMO_NANOHTTPD_SERVER, DEMO_HALF_SCROLL, DEMO_MEMO, LOAD_IMG, DEMO_LOTTIE, TEST_RETROFIT};

    /* icon+label */ private List<Map<String, Object>> list = new ArrayList<>();
    private Map<String, Object> map;
    private String[] keys = {"icon", "label"};

    @Override
    protected void initView() {
        for (String mItem : mItems) {
            map = new HashMap<>();
            map.put(keys[0], R.mipmap.ic_launcher_round);
            map.put(keys[1], mItem);
            list.add(map);
        }

        //        TListView.iconStr(mActivity, lv_pages, list, keys, pos -> {
        TListView.str(mActivity, mBinding.lvList, mItems, pos -> {
            switch (mItems[pos]) {
                case PDF_READER:
                    ARouter.getInstance().build(Const.ARouter.apis_pdf).withString(Const.ARouter.DEFAULT_STRING_KEY, Const.ARouter.getDefaultIntentStringValue(PDF_READER)).navigation();
                    break;
                case BRVAH:
                    ARouter.getInstance().build(Const.ARouter.apis_brvah).withString(Const.ARouter.DEFAULT_STRING_KEY, Const.ARouter.getDefaultIntentStringValue(BRVAH)).navigation();
                    break;
                case SPACE:
                    // ((MainActivity) mActivity).process("clicked...");
                    break;
                case DEMO_FLOW_LAYOUT:
                    ARouter.getInstance().build(Const.ARouter.apis_flow_layout).withString(Const.ARouter.DEFAULT_STRING_KEY, Const.ARouter.getDefaultIntentStringValue(DEMO_FLOW_LAYOUT)).navigation();
                    break;
                case DEMO_STICKY_LIST:
                    ARouter.getInstance().build(Const.ARouter.apis_sticky_list).withString(Const.ARouter.DEFAULT_STRING_KEY, Const.ARouter.getDefaultIntentStringValue(DEMO_STICKY_LIST)).navigation();
                    break;
                case DEMO_NANOHTTPD_SERVER:
                    ARouter.getInstance().build(Const.ARouter.apis_nano).withString(Const.ARouter.DEFAULT_STRING_KEY, Const.ARouter.getDefaultIntentStringValue(DEMO_NANOHTTPD_SERVER)).navigation();
                    break;
                case DEMO_HALF_SCROLL:
                    ARouter.getInstance().build(Const.ARouter.apis_activity_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_apis_half_scroll).navigation();
                    break;
                case DEMO_MEMO:
                    ARouter.getInstance().build(Const.ARouter.apis_memo).withString(Const.ARouter.DEFAULT_STRING_KEY, Const.ARouter.getDefaultIntentStringValue(DEMO_MEMO)).navigation();
                    break;
                case LOAD_IMG:
                    ARouter.getInstance().build(Const.ARouter.apis_loadImg).withString(Const.ARouter.DEFAULT_STRING_KEY, Const.ARouter.getDefaultIntentStringValue(LOAD_IMG)).navigation();
                    break;
                case DEMO_LOTTIE:
                    ARouter.getInstance().build(Const.ARouter.apis_activity_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_apis_lottie).navigation();
                    break;
                case TEST_RETROFIT:
                    ARouter.getInstance().build(Const.ARouter.apis_activity_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_apis_test_retrofit).navigation();
                    break;
            }
        });
    }
}

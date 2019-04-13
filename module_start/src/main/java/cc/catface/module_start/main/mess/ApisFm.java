package cc.catface.module_start.main.mess;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.databinding.PagePureListviewBinding;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.module_start.R;
import cc.catface.module_start.main.mvp.view.MainActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ApisFm extends NormalFragment<PagePureListviewBinding> {

    @Override public int layoutId() {
        return R.layout.page_pure_listview;
    }

    /* 纯文本 */
    private final String PDF_READER = "demo-pdf组件使用";
    private final String BRVAH = "demo-BRVAH组件使用";
    private final String SPACE = "";
    private final String DEMO_FLOW_LAYOUT = "demo-流式布局组件使用";
    private final String DEMO_STICKY_LIST = "demo-列表粘性头部控件使用";
    private final String DEMO_NANOHTTPD_SERVER = "demo-nano httpd使用";
    private final String DEMO_IFLYTEK = "demo-讯飞sdk使用";
    private final String DEMO_MEMO = "demo-green dao使用";
    private final String LOAD_IMG = "demo-各图片加载框架使用";
    private final String TEST_RETROFIT = "test-测试retrofit工具类";
    private String[] mItems = {PDF_READER, BRVAH, SPACE, DEMO_FLOW_LAYOUT, DEMO_STICKY_LIST, DEMO_NANOHTTPD_SERVER, DEMO_IFLYTEK, DEMO_MEMO, LOAD_IMG, TEST_RETROFIT};

    /* icon+label */ private List<Map<String, Object>> list = new ArrayList<>();
    private Map<String, Object> map;
    private String[] keys = {"icon", "label"};

    @Override public void createView() {
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
                    ((MainActivity) mActivity).process("clicked...");
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
                case DEMO_IFLYTEK:
                    ARouter.getInstance().build(Const.ARouter.apis_iflytek).withString(Const.ARouter.DEFAULT_STRING_KEY, Const.ARouter.getDefaultIntentStringValue(DEMO_IFLYTEK)).navigation();
                    break;
                case DEMO_MEMO:
                    ARouter.getInstance().build(Const.ARouter.apis_memo).withString(Const.ARouter.DEFAULT_STRING_KEY, Const.ARouter.getDefaultIntentStringValue(DEMO_MEMO)).navigation();
                    break;
                case LOAD_IMG:
                    ARouter.getInstance().build(Const.ARouter.apis_loadImg).withString(Const.ARouter.DEFAULT_STRING_KEY, Const.ARouter.getDefaultIntentStringValue(LOAD_IMG)).navigation();
                    break;
                case TEST_RETROFIT:
                    ARouter.getInstance().build(Const.ARouter.apis_activity_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_apis_test_retrofit).navigation();
                    break;
            }
        });
    }
}

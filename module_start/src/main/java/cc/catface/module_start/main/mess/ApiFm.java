package cc.catface.module_start.main.mess;

import com.alibaba.android.arouter.launcher.ARouter;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.databinding.PagePureListviewBinding;
import cc.catface.base.utils.android.common_print.popup.TPopup;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.module_start.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ApiFm extends NormalFragment<PagePureListviewBinding> {

    @Override public int layoutId() {
        return R.layout.page_pure_listview;
    }

    private final String DEMO_FRAME = "demo-框架-android基础开发框架示例";
    private final String DEMO_FONT = "demo-系统-字体示例";
    private final String DEMO_TOAST = "demo-系统-toast示例";
    private final String DEMO_DIALOG = "demo-系统-dialog示例";
    private final String DEMO_POP = "demo-系统-popup示例";
    private final String DEMO_VIEW = "demo-系统-view&animator示例";
    private final String DEMO_HARDWARE = "demo-系统-硬件示例";
    private final String DEMO_MENU_OF_ELEME = "demo-功能-饿了么菜单";
    private final String DEMO_TOUTIAO = "demo-系统控件-原生多条目RecyclerView示例";
    private final String DEMO_BANNER = "demo-功能-广告轮播图";
    private final String DEMO_LOAD_BIG_IMG = "demo-功能-加载大图片";
    private final String DEMO_APP_INFO = "demo系统-系统info展示示例";
    private final String DEMO_MULTI_FINGER = "demo系统-多点触控示例";
    private final String DEMO_TEXT_VIEW = "demo-系统控件-TextView效果示例";
    private final String DEMO_IV_SCALETYPE = "demo-系统控件-ImageView的scaleType属性示例";
    private final String DEMO_CONSTRAINT_LAYOUT = "demo-系统-ConstraintLayout示例";
    private final String DEMO_DATA_BINDING = "demo-系统-data_binding示例";
    private final String DEMO_ROOM = "demo-系统-room示例";
    private final String DEMO_CRASH = "demo-系统-crash处理示例";

    private String[] mItems = {DEMO_FRAME, DEMO_FONT, DEMO_TOAST, DEMO_DIALOG, DEMO_POP, DEMO_VIEW, DEMO_HARDWARE, DEMO_MENU_OF_ELEME, DEMO_TOUTIAO, DEMO_BANNER, DEMO_LOAD_BIG_IMG, DEMO_APP_INFO, DEMO_MULTI_FINGER, DEMO_TEXT_VIEW,
            DEMO_IV_SCALETYPE, DEMO_CONSTRAINT_LAYOUT, DEMO_DATA_BINDING, DEMO_ROOM, DEMO_CRASH};


    @Override public void createView() {
        TListView.str(mActivity, mBinding.lvList, mItems, pos -> {
            switch(mItems[pos]) {
                case DEMO_FRAME:
                    ARouter.getInstance().build(Const.AROUTER.api_frame).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_FRAME)).navigation();
                    break;
                case DEMO_FONT:
                    ARouter.getInstance().build(Const.AROUTER.api_font).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_FONT)).navigation();
                    break;
                case DEMO_TOAST:
                    ARouter.getInstance().build(Const.AROUTER.api_toast).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_TOAST)).navigation();
                    break;
                case DEMO_DIALOG:
                    ARouter.getInstance().build(Const.AROUTER.api_dialog).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_DIALOG)).navigation();
                    break;
                case DEMO_POP:
                    ARouter.getInstance().build(Const.AROUTER.api_popup).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_POP)).navigation();
                    break;
                case DEMO_VIEW:
                    ARouter.getInstance().build(Const.AROUTER.api_navigation).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_VIEW)).navigation();
                    break;
                case DEMO_HARDWARE:
                    event(DEMO_HARDWARE);
                    break;
                case DEMO_MENU_OF_ELEME:
                    ARouter.getInstance().build(Const.AROUTER.api_eleme).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_MENU_OF_ELEME)).navigation();
                    break;
                case DEMO_TOUTIAO:
                    ARouter.getInstance().build(Const.AROUTER.api_toutiao).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_TOUTIAO)).navigation();
                    break;
                case DEMO_BANNER:
                    ARouter.getInstance().build(Const.AROUTER.api_banner).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_BANNER)).navigation();
                    break;
                case DEMO_LOAD_BIG_IMG:
                    ARouter.getInstance().build(Const.AROUTER.api_loadHugeImg).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_LOAD_BIG_IMG)).navigation();
                    break;
                case DEMO_APP_INFO:
                    ARouter.getInstance().build(Const.AROUTER.api_appInfo).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_APP_INFO)).navigation();
                    break;
                case DEMO_MULTI_FINGER:
                    ARouter.getInstance().build(Const.AROUTER.api_multi_finger).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_MULTI_FINGER)).navigation();
                    break;
                case DEMO_TEXT_VIEW:
                    ARouter.getInstance().build(Const.AROUTER.api_test_text_view).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_TEXT_VIEW)).navigation();
                    break;
                case DEMO_IV_SCALETYPE:
                    ARouter.getInstance().build(Const.AROUTER.api_attrs_ivScaleType).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_IV_SCALETYPE)).navigation();
                    break;
                case DEMO_CONSTRAINT_LAYOUT:
                    ARouter.getInstance().build(Const.AROUTER.api_test_constraint_layout).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_CONSTRAINT_LAYOUT)).navigation();
                    break;
                case DEMO_DATA_BINDING:
                    ARouter.getInstance().build(Const.AROUTER.api_databinding).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_DATA_BINDING)).navigation();
                    break;
                case DEMO_ROOM:
                    ARouter.getInstance().build(Const.AROUTER.api_room).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_ROOM)).navigation();
                    break;
                case DEMO_CRASH:
                    ARouter.getInstance().build(Const.AROUTER.api_crash).withString(Const.AROUTER.DEFAULT_STRING_KEY, Const.AROUTER.getDefaultIntentStringValue(DEMO_CRASH)).navigation();
                    break;
            }
        });
    }

    private void event(String type) {
        switch(type) {
            case DEMO_HARDWARE:
                String[] items = getResources().getStringArray(R.array.mess_hardware_list);
                TPopup.get(mActivity).show(mBinding.lvList, "请选择具体类型", items, pos -> {
                    if(items[pos].equals(getResources().getString(R.string.mess_hardware_list_camera))) {
                        ARouter.getInstance().build(Const.AROUTER.api_camera).navigation();
                    }
                });
                break;
        }
    }
}

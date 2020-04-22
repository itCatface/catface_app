package cc.catface.start.main.mvp.view;

import com.alibaba.android.arouter.launcher.ARouter;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.databinding.PagePureListviewBinding;
import cc.catface.base.utils.android.common_print.popup.TPopup;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.start.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ApiFm extends LightFm<LightPresenter, PagePureListviewBinding> {

    @Override public int layoutId() {
        return R.layout.page_pure_listview;
    }

    private final String DEMO_SMALL_FUNC = "demo-小功能示例";
    private final String DEMO_FRAME = "demo-框架-基础框架示例";
    private final String DEMO_FONT = "demo-系统-字体示例";
    private final String DEMO_TOAST = "demo-系统-toast示例";
    private final String DEMO_DIALOG = "demo-系统-dialog示例";
    private final String DEMO_POP = "demo-系统-popup示例";
    private final String DEMO_VIEW = "demo-系统-view&animator示例";
    private final String DEMO_HARDWARE = "demo-系统-硬件示例";
    private final String DEMO_MENU_OF_ELEME = "demo-功能-饿了么菜单";
    private final String DEMO_RECYCLERVIEW = "demo-RecyclerView示例";
    private final String DEMO_LOAD_BIG_IMG = "demo-功能-加载大图片";
    private final String DEMO_APP_INFO = "demo系统-系统信息";
    private final String DEMO_MULTI_FINGER = "demo系统-多点触控示例";
    private final String DEMO_TEXT_VIEW = "demo-系统控件-TextView效果示例";
    private final String DEMO_IV_SCALETYPE = "demo-系统控件-IV-scaleType属性示例";
    private final String DEMO_CONSTRAINT_LAYOUT = "demo-系统-ConstraintLayout示例";
    private final String DEMO_DATA_BINDING = "demo-系统-data_binding示例";
    private final String DEMO_ROOM = "demo-系统-room示例";
    private final String DEMO_CRASH = "demo-系统-crash处理示例";
    private final String DEMO_TOOLBAR = "demo-系统-toolbar使用示例";
    private final String DEMO_VIEWPAGER2 = "demo-viewpager2示例";
    private final String DEMO_CLIPBOARD = "demo-clipboard示例";

    private String[] mItems = {DEMO_SMALL_FUNC, DEMO_FRAME, DEMO_FONT, DEMO_TOAST, DEMO_DIALOG, DEMO_POP, DEMO_VIEW, DEMO_HARDWARE, DEMO_RECYCLERVIEW, DEMO_MENU_OF_ELEME, DEMO_LOAD_BIG_IMG, DEMO_APP_INFO, DEMO_MULTI_FINGER, DEMO_TEXT_VIEW, DEMO_IV_SCALETYPE, DEMO_CONSTRAINT_LAYOUT, DEMO_DATA_BINDING, DEMO_ROOM, DEMO_CRASH, DEMO_TOOLBAR, DEMO_VIEWPAGER2, DEMO_CLIPBOARD};


    @Override protected void initView() {
        TListView.str(mActivity, mBinding.lvList, mItems, pos -> {
            switch (mItems[pos]) {
                case DEMO_SMALL_FUNC:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_small_func).navigation();
                    break;
                case DEMO_FRAME:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_frame).navigation();
                    break;
                case DEMO_FONT:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_font_type).navigation();
                    break;
                case DEMO_TOAST:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_toast).navigation();
                    break;
                case DEMO_DIALOG:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_dialog).navigation();
                    break;
                case DEMO_POP:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_popup).navigation();
                    break;
                case DEMO_VIEW:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_view_anim).navigation();
                    break;
                case DEMO_HARDWARE:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_hardware).navigation();
                    break;
                case DEMO_MENU_OF_ELEME:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_eleme).navigation();
                    break;
                case DEMO_RECYCLERVIEW:
                    event(DEMO_RECYCLERVIEW);
                    break;
                case DEMO_LOAD_BIG_IMG:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_load_large_image).navigation();
                    break;
                case DEMO_APP_INFO:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_system_info).navigation();
                    break;
                case DEMO_MULTI_FINGER:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_multi_touch).navigation();
                    break;
                case DEMO_TEXT_VIEW:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_textview_serial).navigation();
                    break;
                case DEMO_IV_SCALETYPE:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_imageview_serial).navigation();
                    break;
                case DEMO_CONSTRAINT_LAYOUT:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_constraintlayout).navigation();
                    break;
                case DEMO_DATA_BINDING:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_data_binding).navigation();
                    break;
                case DEMO_ROOM:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_room).navigation();
                    break;
                case DEMO_CRASH:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_crash).navigation();
                    break;
                case DEMO_TOOLBAR:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_toolbar).navigation();
                    break;
                case DEMO_VIEWPAGER2:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_viewpager2).navigation();
                    break;
                case DEMO_CLIPBOARD:
                    ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_api_clipboard).navigation();
                    break;
            }
        });
    }

    private void event(String type) {
        switch (type) {
            case DEMO_RECYCLERVIEW:
                String[] items_rv = getResources().getStringArray(R.array.api_rv_list);
                TPopup.get(mActivity).show(mBinding.lvList, "RecyclerView功能", items_rv, pos -> {
                    if (items_rv[pos].equals(getResources().getString(R.string.api_rv_list_toutiao))) {
                        ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_rv_api_toutiao).navigation();
                    } else if (items_rv[pos].equals(getResources().getString(R.string.api_rv_list_banner))) {
                        ARouter.getInstance().build(Const.ARouter.api_holder).withString(Const.ARouter.fm_id_key, Const.ARouter.fm_id_rv_api_banner).navigation();
                    }
                });
                break;
        }
    }
}

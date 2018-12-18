package cc.catface.app.module.mess;

import android.widget.ListView;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import cc.catface.app.R;
import cc.catface.base.core_framework.base_normal.NormalBaseFragment;
import cc.catface.base.utils.android.common_print.popup.TPopup;
import cc.catface.base.utils.android.coomon_listview.TListView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ApiFm extends NormalBaseFragment {


    @Override public int layoutId() {
        return R.layout.fragment_just_with_listview;
    }

    @BindView(R.id.lv_list) ListView lv_pages;

    private final String DEMO_TOAST = "demo-toast[系统土司]";
    private final String DEMO_DIALOG = "demo-dialog[系统弹窗]";
    private final String DEMO_VIEW = "demo-view[系统view动画]";
    private final String DEMO_HARDWARE = "demo-hardware[硬件]";
    private final String DEMO_MENU_OF_ELEME = "demo[菜单]-饿了么";
    private final String DEMO_TOUTIAO = "demo[页面]-原生多条目RecyclerView";
    private final String DEMO_BANNER = "demo[轮播]-广告轮播图";
    private final String DEMO_LOAD_BIG_IMG = "demo[图片]-加载大图片";
    private final String DEMO_IV_SCALETYPE = "demo[图片]-ImageView的scaleType属性";

    private String[] mItems = {DEMO_TOAST, DEMO_DIALOG, DEMO_VIEW, DEMO_HARDWARE, DEMO_MENU_OF_ELEME, DEMO_TOUTIAO, DEMO_BANNER,DEMO_LOAD_BIG_IMG, DEMO_IV_SCALETYPE};


    @Override public void createView() {
        TListView.str(mActivity, lv_pages, mItems, pos -> {
            switch (mItems[pos]) {
                case DEMO_TOAST:
                    ARouter.getInstance().build("/api/toast").navigation();
                    break;
                case DEMO_DIALOG:
                    ARouter.getInstance().build("/api/dialog").navigation();
                    break;
                case DEMO_VIEW:
                    ARouter.getInstance().build("/api/navigation").navigation();
                    break;
                case DEMO_HARDWARE:
                    event(DEMO_HARDWARE);
                    break;

                case DEMO_MENU_OF_ELEME:
                    ARouter.getInstance().build("/api/eleme").navigation();
                    break;
                case DEMO_TOUTIAO:
                    ARouter.getInstance().build("/api/toutiao").navigation();
                    break;
                case DEMO_BANNER:
                    ARouter.getInstance().build("/api/banner").navigation();
                    break;
                case DEMO_LOAD_BIG_IMG:
                    ARouter.getInstance().build("/api/loadHugeImg").navigation();
                    break;
                case DEMO_IV_SCALETYPE:
                    ARouter.getInstance().build("/api/attrs/ivScaleType").navigation();
                    break;
            }
        });
    }

    private void event(String type) {
        switch (type) {
            case DEMO_HARDWARE:
                String[] items = getResources().getStringArray(R.array.mess_hardware_list);
                TPopup.get(mActivity).show(lv_pages, "请选择具体类型", items, pos -> {
                    if (items[pos].equals(getResources().getString(R.string.mess_hardware_list_camera))) {
                        ARouter.getInstance().build("/api/camera").navigation();
                    }
                });
                break;
        }
    }
}

package cc.catface.app.module.mess;

import android.widget.ListView;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cc.catface.app.R;
import cc.catface.app.module.start.main.view.MainActivity;
import cc.catface.base.core_framework.base_normal.NormalBaseFragment;
import cc.catface.base.utils.android.coomon_listview.TListView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ApisFm extends NormalBaseFragment {


    @Override public int layoutId() {
        return R.layout.fragment_just_with_listview;
    }

    @BindView(R.id.lv_list) ListView lv_apis;

    /* 纯文本 */
    private final String PDF_READER = "demo-pdf查看";
    private final String BRVAH = "demo-BRVAH组件使用";
    private final String SPACE = "";
    private final String DEMO_NANOHTTPD_SERVER = "demo-nano httpd服务器";
    private final String DEMO_IFLYTEK = "demo-讯飞语音合成识别";
    private final String DEMO_MEMO = "demo-green dao备忘录";
    private final String DEMO_SHOWAPI = "demo[项目]-showapi易源接口";
    private final String LOAD_IMG = "各图片加载框架";
    private String[] mItems = {PDF_READER, BRVAH, SPACE, DEMO_NANOHTTPD_SERVER, DEMO_IFLYTEK, DEMO_MEMO, DEMO_SHOWAPI, LOAD_IMG};

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
        TListView.str(mActivity, lv_apis, mItems, pos -> {
            switch (mItems[pos]) {
                case PDF_READER:
                    ARouter.getInstance().build("/apis/pdf").navigation();
                    break;
                case BRVAH:
                    ARouter.getInstance().build("/apis/brvah").navigation();
                    break;
                case SPACE:
                    ((MainActivity) mActivity).process("clicked...");
                    break;
                case DEMO_NANOHTTPD_SERVER:
                    ARouter.getInstance().build("/apis/nano").navigation();
                    break;
                case DEMO_IFLYTEK:
                    ARouter.getInstance().build("/apis/iflytek").navigation();
                    break;
                case DEMO_MEMO:
                    ARouter.getInstance().build("/apis/memo").navigation();
                    break;
                case DEMO_SHOWAPI:
                    ARouter.getInstance().build("/apis/showapi").navigation();
                    break;
                case LOAD_IMG:
                    ARouter.getInstance().build("/apis/loadImg").navigation();
                    break;
            }
        });
    }
}

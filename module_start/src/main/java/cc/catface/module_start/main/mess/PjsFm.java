package cc.catface.module_start.main.mess;

import android.view.View;
import android.widget.ListView;

import com.alibaba.android.arouter.launcher.ARouter;

import cc.catface.base.core_framework.base_normal.NormalBaseFragmentID;
import cc.catface.base.utils.android.common_print.popup.TPopup;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.module_start.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class PjsFm extends NormalBaseFragmentID {

    @Override public int layoutId() {
        return R.layout.page_pure_listview;
    }

    private ListView lv_list;

    @Override public void ids(View v) {
        lv_list = (ListView) v.findViewById(R.id.lv_list);
    }

    private final String PJS_NEWS = "project_新闻案例";

    private String[] mItems = {PJS_NEWS};


    @Override public void createView() {
        TListView.str(mActivity, lv_list, mItems, pos -> {
            switch (mItems[pos]) {
                case PJS_NEWS:
                    ARouter.getInstance().build("/api/toast").navigation();
                    break;
            }
        });
    }
}

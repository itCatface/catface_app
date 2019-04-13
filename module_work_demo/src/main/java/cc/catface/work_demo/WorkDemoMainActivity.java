package cc.catface.work_demo;

import android.os.Build;
import android.transition.Slide;
import android.view.Gravity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import androidx.annotation.RequiresApi;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.utils.android.coomon_listview.TListView;
import cc.catface.work_demo.databinding.WorkActivityWorkDemoMainBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.work_demo_main)
public class WorkDemoMainActivity extends NormalActivity<WorkActivityWorkDemoMainBinding> {

    @Override public int layoutId() {
        return R.layout.work_activity_work_demo_main;
    }

    private final String PJS_WORK_DEMO_IFLY_SWIPE_CHANGE_PAGE = "ifly_滑动切换页面";
    private String[] mItems = {PJS_WORK_DEMO_IFLY_SWIPE_CHANGE_PAGE};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) @Override public void create() {
        //左侧滑入
        getWindow().setEnterTransition(new Slide(Gravity.LEFT).setDuration(2000));
        getWindow().setExitTransition(new Slide(Gravity.RIGHT).setDuration(2000));
        TListView.str(this, mBinding.lvWorkDemo, mItems, pos -> {
            switch (mItems[pos]) {
                case PJS_WORK_DEMO_IFLY_SWIPE_CHANGE_PAGE:
                    ARouter.getInstance().build(Const.ARouter.work_demo_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_work_demo_ifly_swipe_change_page).navigation();
                    break;
            }
        });
    }
}

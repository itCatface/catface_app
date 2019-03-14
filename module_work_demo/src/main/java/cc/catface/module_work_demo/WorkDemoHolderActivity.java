package cc.catface.module_work_demo;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.fragment.app.Fragment;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.utils.android.common_intent.TIntent;
import cc.catface.module_work_demo.swipe_change_page.SwipeChangePageActivity;
import cc.catface.module_work_demo.databinding.WorkActivityWorkDemoHolderBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.work_demo_holder)
public class WorkDemoHolderActivity extends NormalActivity<WorkActivityWorkDemoHolderBinding> {

    @Override public int layoutId() {
        return R.layout.work_activity_work_demo_holder;
    }

    @Override public void create() {
        int fm_id = getIntent().getIntExtra(Const.ARouter.fm_id_key, -1);
        switch (fm_id) {
            case Const.ARouter.fm_id_work_demo_ifly_swipe_change_page:
                TIntent.startActivity(this, SwipeChangePageActivity.class, true);
                break;
        }
    }

    public void replace(Fragment fm) {
        replaceFragment(R.id.fl_work, fm);
    }
}

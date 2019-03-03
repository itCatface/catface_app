package cc.catface.api.room;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityRoomBinding;
import cc.catface.api.room.domain.User;
import cc.catface.api.room.domain.dao.GlobalDatabase;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.base.utils.java.TNumber;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.api_room)
public class DemoRoomFm extends NormalFragment<ApiActivityRoomBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_room;
    }

    @Override protected void initAction() {
        mBinding.btInsert.setOnClickListener(v -> {
            db_user.userDao().insert(new User("张" + TNumber.getRandom(0, 10000), "三" + System.currentTimeMillis(), null));
        });
        mBinding.btDelete.setOnClickListener(v -> {

        });
        mBinding.btUpdate.setOnClickListener(v -> {

        });
        mBinding.btQueryAll.setOnClickListener(v -> {
            List<User> all = db_user.userDao().getAll();
            TLog.d("root", "所有user: " + all);
        });
        mBinding.btQueryOdd.setOnClickListener(v -> {

        });
    }

    GlobalDatabase db_user;

    @Override public void createView() {
        new Thread(new Runnable() {
            @Override public void run() {
                db_user = GlobalDatabase.instance();
            }
        }).start();

    }

}

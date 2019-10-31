package cc.catface.api.databinding;

import android.os.SystemClock;
import android.view.View;

import java.util.Random;

import cc.catface.api.R;
import cc.catface.app_base.TestDataSource;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.common_print.toast.TToast;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoDataBindingFm extends LightFm<LightPresenter, ApiActivityDataBindingBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_data_binding;
    }

    @Override protected void initView() {
        /* 测试基本的DataBinding数据绑定效果 */
        testChangeUser();
    }


    private User mUser;

    private void testChangeUser() {
        mUser = new User(true, "艾希", false, 18);
        mUser.description.set("时间戳：" + System.currentTimeMillis());
        mBinding.setUser(mUser);

        mBinding.btThreadUpdate.setOnClickListener(v -> new Thread(() -> {
            SystemClock.sleep(500);
            mUser.setName("thread");
            mUser.description.set("时间戳：" + System.currentTimeMillis());
        }).start());
    }


    public void click(View view) {
        TToast.get(mActivity).showBShortView("[普通处理]onClick=@{click}: " + System.currentTimeMillis(), TToast.B_SUCCESS);
    }


    public void tbUpdateData() {
        mUser.setChecked(new Random().nextInt(2) == 1);
        mUser.setName(TestDataSource.words[new Random().nextInt(TestDataSource.words.length)]);
        mUser.setMale(new Random().nextInt(2) == 1);
        mUser.setAge(new Random().nextInt(99));
        mUser.description.set("时间戳：" + System.currentTimeMillis());
    }
}

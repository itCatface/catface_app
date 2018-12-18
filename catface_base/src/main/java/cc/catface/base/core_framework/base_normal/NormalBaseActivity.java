package cc.catface.base.core_framework.base_normal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.ButterKnife;
import cc.catface.base.utils.android.common_print.toast.TToast;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class NormalBaseActivity extends Activity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        create();
    }


    public abstract int layoutId();

    public abstract void create();


    @Override protected void onStop() {
        super.onStop();
        TToast.get(this).clearToast();
    }
}

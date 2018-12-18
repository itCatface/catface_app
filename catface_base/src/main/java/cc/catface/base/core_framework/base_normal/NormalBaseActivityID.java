package cc.catface.base.core_framework.base_normal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.ButterKnife;
import cc.catface.base.utils.android.common_print.toast.TToast;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class NormalBaseActivityID extends FragmentActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        ids();
        create();
    }

    public abstract void ids();

    public abstract int layoutId();

    public abstract void create();


    @Override protected void onStop() {
        super.onStop();
        TToast.get(this).clearToast();
    }
}

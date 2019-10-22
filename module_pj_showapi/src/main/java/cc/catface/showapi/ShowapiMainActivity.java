package cc.catface.showapi;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.ctool.view.activity.TActivity;
import cc.catface.showapi.databinding.ShowapiActivityShowapiMainBinding;
import cc.catface.showapi.joke.mvp.view.ShowAPIActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.pj_showapi_main)
public class ShowapiMainActivity extends NormalActivity<ShowapiActivityShowapiMainBinding> {
    @Override public int layoutId() {
        return R.layout.showapi_activity_showapi_main;
    }

    @Override protected void initAction() {
        mBinding.btToJoke.setOnClickListener(v -> TActivity.startActivity(this, ShowAPIActivity.class));
    }

    @Override public void create() {

    }
}

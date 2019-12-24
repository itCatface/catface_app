package cc.catface.showapi;

import android.app.ActivityOptions;
import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.showapi.databinding.ShowapiActivityShowapiMainBinding;
import cc.catface.showapi.joke.mvp.view.JokeActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.pj_showapi_main) public class ShowapiMainActivity extends LightAct<LightPresenter, ShowapiActivityShowapiMainBinding> {

    @Override public int layoutId() {
        return R.layout.showapi_activity_showapi_main;
    }

    @Override protected void initAction() {
        mBinding.tv2Joke.setOnClickListener(v -> startActivity(new Intent(this, JokeActivity.class), ActivityOptions.makeSceneTransitionAnimation(this, mBinding.tv2Joke, "tnTvTitle").toBundle()));
    }
}

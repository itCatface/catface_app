package cc.catface.wanandroid.module.home;

import com.google.gson.Gson;

import cc.catface.base.core_framework.base_mvp.presenter.MvpPresenter;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.Banner;
import cc.catface.wanandroid.engine.domain.TopArticle;
import cc.catface.wanandroid.engine.domain.WanandroidConst;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class HomePresenterImpl extends MvpPresenter<HomeVP.HomeView> implements HomeVP.HomePresenter {
    @Override public void requestBanner() {
        OkHttpUtil.get(WanandroidConst.url_home_banner, new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                Banner data = new Gson().fromJson(result, Banner.class);
                mView.requestBannerSuccess(data);
            }

            @Override public void onFailure(String error) {
                mView.requestBannerFailure();
            }
        });
    }

    @Override public void requestTopArticle() {
        OkHttpUtil.get(WanandroidConst.url_home_top_article, new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                TopArticle data = new Gson().fromJson(result, TopArticle.class);
                mView.requestTopArticleSuccess(data);
            }

            @Override public void onFailure(String error) {
                mView.requestTopArticleFailure();
            }
        });
    }
}

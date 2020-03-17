package cc.catface.wanandroid.module.home;

import android.content.Context;

import com.google.gson.Gson;

import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.net.okhttp.OkHttpRequestCallback;
import cc.catface.base.utils.android.net.okhttp.OkHttpUtil;
import cc.catface.wanandroid.engine.domain.Banner;
import cc.catface.wanandroid.engine.domain.TopArticle;
import cc.catface.wanandroid.engine.domain.WanandroidConst;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class HomePresenterImpl extends LightPresenter<HomeVP.HomeView> implements HomeVP.HomePresenter {

    HomePresenterImpl(HomeVP.HomeView view, Context context) {
        super(view, context);
    }

    @Override public void requestBanner() {
        OkHttpUtil.get(WanandroidConst.url_home_banner, new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                Banner data = new Gson().fromJson(result, Banner.class);
                Banner.Data myBlogBanner = new Banner.Data();
                myBlogBanner.setImagePath("http://catface.cc/img/favicon.ico");
                myBlogBanner.setUrl(WanandroidConst.url_blog);
                myBlogBanner.setTitle("catface's blog");
                data.getData().add(0, myBlogBanner);
                getView().requestBannerSuccess(data);
            }

            @Override public void onFailure(String error) {
                getView().requestBannerFailure();
            }
        });
    }

    @Override public void requestTopArticle() {
        OkHttpUtil.get(WanandroidConst.url_home_top_article, new OkHttpRequestCallback() {
            @Override public void onSuccess(String result) {
                TopArticle data = new Gson().fromJson(result, TopArticle.class);
                getView().requestTopArticleSuccess(data);
            }

            @Override public void onFailure(String error) {
                getView().requestTopArticleFailure();
            }
        });
    }
}

package cc.catface.wanandroid.module.home;

import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.wanandroid.engine.domain.Banner;
import cc.catface.wanandroid.engine.domain.TopArticle;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface HomeVP {

    interface HomePresenter {
        void requestBanner();

        void requestTopArticle();
    }

    interface HomeView extends LightView {
        void requestBannerSuccess(Banner banner);

        void requestBannerFailure();

        void requestTopArticleSuccess(TopArticle topArticle);

        void requestTopArticleFailure();
    }

}

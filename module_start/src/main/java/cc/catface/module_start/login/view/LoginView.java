package cc.catface.module_start.login.view;

import cc.catface.base.core_framework.base_mvp.view.MvpView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface LoginView extends MvpView {

    void requesting();

    void requestSuccess(String result);

    void requestError(String error);
}

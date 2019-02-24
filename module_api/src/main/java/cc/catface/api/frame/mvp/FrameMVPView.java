package cc.catface.api.frame.mvp;

import cc.catface.base.core_framework.base_mvp.view.MvpView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface FrameMVPView extends MvpView {

    void checkAccountPass();

    void checkAccountNotPass(String error);

    void loginSuccess(String info);

    void loginFailure(String error);

}

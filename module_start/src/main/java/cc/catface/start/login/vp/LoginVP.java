package cc.catface.start.login.vp;

import cc.catface.base.core_framework.light_mvp.LightView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface LoginVP {

    interface LoginPresenter {
        void startRequest();
    }

    interface LoginView extends LightView {
        void requesting();

        void requestSuccess(String result);

        void requestError(String error);
    }


}

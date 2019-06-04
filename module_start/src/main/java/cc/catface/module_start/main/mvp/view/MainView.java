package cc.catface.module_start.main.mvp.view;

import cc.catface.base.core_framework.base_mvp.view.MvpView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface MainView extends MvpView {

    void requestPermissionSuccess();

    void requestPermissionFailure();

}

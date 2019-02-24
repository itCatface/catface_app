package cc.catface.module_start.main.personal.mvp.view;

import cc.catface.base.core_framework.base_mvp.view.MvpView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface PersonalView extends MvpView {

    void loadBaiduSuc(String result);

    void loadBaiduErr(String error);

}

package cc.catface.app.module.personal.view;

import cc.catface.base.core_framework.base_mvp.view.BaseMVPView;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface PersonalView extends BaseMVPView {

    void loadBaiduSuc(String result);

    void loadBaiduErr(String error);

}

package cc.catface.module_start.main.mvp.view.navigation.view;

import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsFragment;
import cc.catface.base.utils.android.common_title.TitleBuilder;
import cc.catface.module_start.R;
import cc.catface.module_start.main.mvp.view.navigation.presenter.FunctionPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(FunctionPresenterImp.class)
public class FunctionFm extends AbsFragment<FunctionView, FunctionPresenterImp> implements FunctionView {
    @Override public int layoutId() {
        return R.layout.start_fm_function;
    }


    @Override public void viewCreated() {
        initTitle();
    }

    private void initTitle() {
        new TitleBuilder(mActivity).setIVLeftRes(R.mipmap.flaticon_back).setTVCenterText("hello world!");
    }
}

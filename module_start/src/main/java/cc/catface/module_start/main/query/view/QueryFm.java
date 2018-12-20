package cc.catface.module_start.main.query.view;

import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsFragment;
import cc.catface.module_start.R;
import cc.catface.module_start.main.query.presenter.QueryPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(QueryPresenterImp.class)
public class QueryFm extends AbsFragment<QueryView,QueryPresenterImp> implements QueryView {
    @Override public int layoutId() {
        return R.layout.fm_third_query;
    }

    @Override public void viewCreated() {

    }
}

package cc.catface.app.module.query.view;

import cc.catface.app.R;
import cc.catface.app.module.query.presenter.QueryPresenterImp;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsFragment;

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

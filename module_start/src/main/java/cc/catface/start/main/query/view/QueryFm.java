package cc.catface.start.main.query.view;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.start.R;
import cc.catface.start.databinding.FmThirdQueryBinding;
import cc.catface.start.main.query.vp.QueryPresenterImp;
import cc.catface.start.main.query.vp.QueryVP;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class QueryFm extends LightFm<QueryPresenterImp, FmThirdQueryBinding> implements QueryVP.QueryView {

    @Override public int layoutId() {
        return R.layout.fm_third_query;
    }

}

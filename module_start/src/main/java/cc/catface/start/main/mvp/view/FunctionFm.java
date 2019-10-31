package cc.catface.start.main.mvp.view;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightView;
import cc.catface.start.R;
import cc.catface.start.databinding.StartFmFunctionBinding;
import cc.catface.start.main.mvp.vp.FunctionPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class FunctionFm extends LightFm<FunctionPresenterImp, StartFmFunctionBinding> implements LightView {

    @Override public int layoutId() {
        return R.layout.start_fm_function;
    }

}

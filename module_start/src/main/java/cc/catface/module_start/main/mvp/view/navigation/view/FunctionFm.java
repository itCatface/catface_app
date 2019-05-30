package cc.catface.module_start.main.mvp.view.navigation.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.module_start.R;
import cc.catface.module_start.databinding.StartFmFunctionBinding;
import cc.catface.module_start.main.mvp.view.navigation.presenter.FunctionPresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(FunctionPresenterImp.class)
public class FunctionFm extends MvpFragment<FunctionView, FunctionPresenterImp, StartFmFunctionBinding> implements FunctionView {
    @Override public int layoutId() {
        return R.layout.start_fm_function;
    }


    @Override public void viewCreated() {

    }
}

package cc.catface.base.core_framework;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BaseFunctionFm extends RxFragment {

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        /*--->支持ToolBar中的Menu*/
        setHasOptionsMenu(true);
        /*<---*/
        super.onCreate(savedInstanceState);
    }
}

package cc.catface.base.core_framework;

import android.os.Message;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.Objects;

import cc.catface.base.utils.android.view.TFontType;
import cc.catface.ctool.system.TWeakHandler;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class BaseFunFm extends RxFragment implements TWeakHandler.MessageListener, View.OnClickListener {

    /** ------->全局开关 */
    private final boolean isSupportMenu = true;
    private final boolean isOpenImmersionBar = true;
    private final boolean isUsingCustomFontType = false;


    /** ------->公共接口处理 */
    /* 全局Handler */
    @Override public void handleMessage(Message msg) { }

    /* 全局控件事件 */
    @Override public void onClick(View view) { }


    /** ------->统一声明onCreateView()中的基本方法 */
    public abstract int layoutId();

    protected void init() {
        if (isSupportMenu) {
            setHasOptionsMenu(true);
        }
        if (isOpenImmersionBar) {
            ImmersionBar.with(this).init();
        }
        if (isUsingCustomFontType) {
            TFontType.replaceFont(Objects.requireNonNull(getActivity()), TFontType.Font.hill_house);
        }
        initView();
        initPresenter();
        initHandler();
        initData();
        initAction();
        created();
    }

    protected void initView() { }

    protected void initPresenter() { }

    protected void initHandler() { }

    protected void initData() { }

    protected void initAction() { }

    protected void created() { }

}

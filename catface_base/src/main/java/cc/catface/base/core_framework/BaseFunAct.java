package cc.catface.base.core_framework;

import android.content.Context;
import android.os.IBinder;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.android.view.TFontType;
import cc.catface.ctool.system.TWeakHandler;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class BaseFunAct extends RxAppCompatActivity implements TWeakHandler.MessageListener, View.OnClickListener {

    /** ------->全局开关 */
    private final boolean isOpenImmersionBar = true;
    private final boolean isUsingCustomFontType = false;

    /** ------->公共接口处理 */
    /* 全局Handler */
    @Override public void handleMessage(Message msg) { }

    /* 全局控件事件 */
    @Override public void onClick(View view) { }


    /** ------->生命周期公共部分 */
    @Override protected void onStop() {
        super.onStop();
        TToast.get(this).clearToast();
    }


    /** ------->点击空白处隐藏键盘 */
    @Override public boolean dispatchTouchEvent(MotionEvent me) {
        if (me.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, me)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(me);
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if ((v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (null != im) im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /** ------->统一声明onCreate()中的基本方法 */
    public abstract int layoutId();

    protected void init() {
        if (isOpenImmersionBar) {
            ImmersionBar.with(this).init();
        }
        if (isUsingCustomFontType) {
            TFontType.replaceFont(this, TFontType.Font.hill_house);
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


    /** ------->全局fragment管理 */
    private FragmentManager mFmManager;

    public FragmentManager getBaseFragmentManager() {   // 获取fragment管理器
        if (mFmManager == null) mFmManager = getSupportFragmentManager();
        return mFmManager;
    }

    public FragmentTransaction getFragmentTransaction() {   // 获取fragment事务管理
        return getBaseFragmentManager().beginTransaction();
    }

    public void replaceFragment(int res, Fragment fragment) {   // 替换一个fragment
        replaceFragment(res, fragment, false);
    }

    public void replaceFragment(int res, Fragment fragment, boolean isAddToBackStack) { // 替换fragment并设置是否加入回退栈
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);   // 添加转场动画(or setCustomAnimations)

        fragmentTransaction.replace(res, fragment);

        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    public void addFragment(int res, Fragment fragment) {   // 添加一个fragment
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.add(res, fragment);
        fragmentTransaction.commit();
    }

    public void addFragment(int res, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.add(res, fragment, tag);
        fragmentTransaction.commit();
    }

    public void removeFragment(Fragment fragment) { // 移除一个fragment
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    public void showFragment(Fragment fragment) {   // 显示一个fragment
        if (fragment.isHidden()) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        }
    }

    public void hideFragment(Fragment fragment) {   // 隐藏一个fragment
        if (!fragment.isHidden()) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.hide(fragment);
            fragmentTransaction.commit();
        }
    }
}

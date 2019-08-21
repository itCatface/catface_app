package cc.catface.wanandroid.module.home;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentHomeBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 * desc 支持滑动隐藏顶部ToolBar
 */
@CreatePresenter(HomePresenterImpl.class) public class HomeFm extends MvpFragment<HomeVP.HomeView, HomePresenterImpl, WanandroidFragmentHomeBinding> implements HomeVP.HomeView {
    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_home;
    }

    @Override public void viewCreated() {
        WebSettings settings = mBinding.wvHome.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadsImagesAutomatically(true);

        mBinding.wvHome.setWebViewClient(new WebViewClient());

        mBinding.wvHome.setWebChromeClient(new WebChromeClient() {
            @Override public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mBinding.pbHome.setVisibility(View.GONE);
                } else {
                    mBinding.pbHome.setVisibility(View.VISIBLE);
                    mBinding.pbHome.setProgress(newProgress);
                }
            }
        });

        mBinding.wvHome.loadUrl("http://catface.cc");


        /* WebView返回上一页 */
        mBinding.wvHome.setOnKeyListener((v, keyCode, event) -> {
            if ((keyCode == KeyEvent.KEYCODE_BACK) && mBinding.wvHome.canGoBack()) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    mBinding.wvHome.goBack();
                }
                return true;
            }
            return false;
        });
    }


    /* 防止WebView内存泄漏 */
    @Override public void onDestroyView() {
        if (mBinding.wvHome != null) {
            ViewParent parent = mBinding.wvHome.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mBinding.wvHome);
            }

            mBinding.wvHome.stopLoading();
            mBinding.wvHome.getSettings().setJavaScriptEnabled(false);
            mBinding.wvHome.clearHistory();
            mBinding.wvHome.clearView();
            mBinding.wvHome.removeAllViews();
            mBinding.wvHome.destroy();

        }
        super.onDestroyView();
    }
}

package cc.catface.wanandroid.module.web;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidActivityWebBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class WebActivity extends LightAct<LightPresenter, WanandroidActivityWebBinding> {
    private String url;

    public static void jump(Context ctx, String url) {
        Intent intent = new Intent(ctx, WebActivity.class);
        intent.putExtra("url", url);
        ctx.startActivity(intent);
    }


    @Override public int layoutId() {
        return R.layout.wanandroid_activity_web;
    }

    @Override protected void created() {
        url = getIntent().getStringExtra("url");
        loadHtml();

    }

    private void loadHtml() {
        mBinding.wv.loadUrl(url);
        WebSettings settings = mBinding.wv.getSettings();
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
        mBinding.wv.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mBinding.wv.setWebChromeClient(new WebChromeClient() {
            @Override public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (100 == newProgress) {
                    mBinding.pbWeb.setVisibility(View.GONE);
                } else {
                    mBinding.pbWeb.setVisibility(View.VISIBLE);
                    mBinding.pbWeb.setProgress(newProgress);
                }

            }
        });
        /* WebView返回上一页 */
        mBinding.wv.setOnKeyListener((v, keyCode, event) -> {
            if ((keyCode == KeyEvent.KEYCODE_BACK) && mBinding.wv.canGoBack()) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    mBinding.wv.goBack();
                }
                return true;
            }
            return false;
        });
    }


    /* 防止WebView内存泄漏 */
    @Override public void onDestroy() {
        if (mBinding.wv != null) {
            ViewParent parent = mBinding.wv.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mBinding.wv);
            }

            mBinding.wv.stopLoading();
            mBinding.wv.getSettings().setJavaScriptEnabled(false);
            mBinding.wv.clearHistory();
            mBinding.wv.clearView();
            mBinding.wv.removeAllViews();
            mBinding.wv.destroy();

        }
        super.onDestroy();
    }
}

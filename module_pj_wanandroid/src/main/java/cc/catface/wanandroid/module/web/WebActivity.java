package cc.catface.wanandroid.module.web;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidActivityWebBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class WebActivity extends NormalActivity<WanandroidActivityWebBinding> {
    private String url;

    public static void jump(Context ctx, String url) {
        Intent intent = new Intent(ctx, WebActivity.class);
        intent.putExtra("url", url);
        ctx.startActivity(intent);
    }


    @Override public int layoutId() {
        return R.layout.wanandroid_activity_web;
    }

    @Override public void create() {
        url = getIntent().getStringExtra("url");
        loadHtml();

    }

    private void loadHtml() {
        mBinding.wv.loadUrl(url);
        mBinding.wv.getSettings().setJavaScriptEnabled(true);
        mBinding.wv.getSettings().setSupportZoom(true);
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
        /*mBinding.wv.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = mBinding.wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);*/
    }
}

package cc.catface.module_start.ad.view;

import android.annotation.SuppressLint;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;

import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpActivity;
import cc.catface.base.utils.android.Timer.CountDownTimer;
import cc.catface.ctool.view.activity.TActivity;
import cc.catface.module_start.R;
import cc.catface.module_start.ad.presenter.AdPresenterImp;
import cc.catface.module_start.databinding.StartActivityAdBinding;
import cc.catface.module_start.main.mvp.view.MainActivity;
//import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(AdPresenterImp.class) public class AdActivity extends MvpActivity<AdView, AdPresenterImp, StartActivityAdBinding> implements AdView, View.OnClickListener {
    @Override public int layoutId() {
        return R.layout.start_activity_ad;
    }

    private String urlOfAd = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535347148144&di=c7f997583729f6372b461fe9c3ad7d97&imgtype=0&src=http%3A%2F%2Fwww.ittime.com" + ".cn%2Fuploadimage%2F5b38cd2fb4620original.jpg";
    private CountDownTimer mCountDownTimer;


    @Override public void onClick(View view) {
        if (R.id.tv_share == view.getId()) {
            showShare();
        } else if (R.id.tv_jump == view.getId()) {
            TActivity.startActivityFinish(AdActivity.this, MainActivity.class);
        }
    }

    @Override protected void initAction() {
        mBinding.tvShare.setOnClickListener(this);
        mBinding.tvJump.setOnClickListener(this);
    }

    @Override public void create() {
        /* 广告图片展示 */
        Glide.with(this).asBitmap().load(urlOfAd).transition(new BitmapTransitionOptions().crossFade(300)).into(mBinding.ivAd);

        /* 跳过广告按钮 */
        mCountDownTimer = new CountDownTimer(11_000, 1_000) {
            @SuppressLint("SetTextI18n") @Override public void onTick(long millisUntilFinished) {
                mBinding.tvJump.setText("跳过(" + ((int) millisUntilFinished / 1_000 - 1) + "s)");
            }

            @Override public void onFinish() {
                TActivity.startActivityFinish(AdActivity.this, MainActivity.class);
            }
        }.start();
    }

    @Override protected void onPause() {
        super.onPause();
        if (null != mCountDownTimer) mCountDownTimer.cancel();
    }

    private void showShare() {
        //        OnekeyShare oks = new OnekeyShare();
        //        //关闭sso授权
        //        oks.disableSSOWhenAuthorize();
        //
        //        // title标题，微信、QQ和QQ空间等平台使用
        //        oks.setTitle("开始分享吧");
        //        // titleUrl QQ和QQ空间跳转链接
        //        oks.setTitleUrl("http://sharesdk.cn");
        //        // text是分享文本，所有平台都需要这个字段
        //        oks.setText("我是分享文本");
        //        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        //        // url在微信、微博，Facebook等平台中使用
        //        oks.setUrl("http://sharesdk.cn");
        //        // comment是我对这条分享的评论，仅在人人网使用
        //        oks.setComment("我是测试评论文本");
        //        // 启动分享GUI
        //        oks.show(this);
    }
}

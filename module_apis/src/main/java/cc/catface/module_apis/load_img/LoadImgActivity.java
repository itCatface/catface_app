package cc.catface.module_apis.load_img;

import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import cc.catface.app_base.Const;
import cc.catface.base.GlideApp;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.utils.android.Timer.TTimer;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisActivityLoadImgBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.apis_loadImg)
public class LoadImgActivity extends NormalActivity<ApisActivityLoadImgBinding> implements View.OnClickListener {
    @Override public int layoutId() {
        return R.layout.apis_activity_load_img;
    }

    private String tvCodeStr;
    private double timeStart, timeEnd;

    @Override protected void initAction() {
        mBinding.btGlide.setOnClickListener(this);
        mBinding.btGlideApp.setOnClickListener(this);
        mBinding.btPicasso.setOnClickListener(this);
        mBinding.btFresco.setOnClickListener(this);
    }

    @Override public void onClick(View view) {
        String url = "http://dpic.tiankong.com/p9/um/QJ6256170643.jpg";
        mBinding.iv.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher_round));
        mBinding.sdv1.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher_round));
        TTimer.timeFinished(1_000, () -> {
            timeStart = SystemClock.currentThreadTimeMillis();
            if(R.id.bt_glide == view.getId()) {
                Glide.with(this).load(url).into(mBinding.iv);
                tvCodeStr = "Glide.with(this).load(url).into(iv)";
            } else if(R.id.bt_glide_app == view.getId()) {
                GlideApp.with(this).load(url).placeholder(R.mipmap.ic_launcher).into(mBinding.iv);
                tvCodeStr = "GlideApp.with(this).load(url).placeholder(R.mipmap.ic_launcher).into(iv)";
            } else if(R.id.bt_picasso == view.getId()) {
                Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).into(mBinding.iv);
                tvCodeStr = "Picasso.get().load(url).into(iv)";
            } else if(R.id.bt_fresco == view.getId()) {
                mBinding.sdv1.setImageURI(url);
                tvCodeStr = "sdv1.setImageURI(url)";
            }
            timeEnd = SystemClock.currentThreadTimeMillis();
            mBinding.tvCode.setText(tvCodeStr + "\n加载时长：" + (timeEnd - timeStart) + " || " + timeStart + "~" + timeEnd);
        });
    }

    @Override public void create() {
        title();
    }

    private void title() {
        mBinding.tfaLoadImg.setTitle(getIntent().getStringExtra(Const.ARouter.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
    }
}






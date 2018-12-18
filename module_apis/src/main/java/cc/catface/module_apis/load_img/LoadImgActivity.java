package cc.catface.module_apis.load_img;

import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import cc.catface.base.GlideApp;
import cc.catface.base.core_framework.base_normal.NormalBaseActivityID;
import cc.catface.base.utils.android.Timer.TTimer;
import cc.catface.base.utils.android.common_title.TitleFontAwesome;
import cc.catface.module_apis.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = "/apis/loadImg")
public class LoadImgActivity extends NormalBaseActivityID implements View.OnClickListener {
    @Override public int layoutId() {
        return R.layout.apis_activity_load_img;
    }

    private TitleFontAwesome tfa_load_img;
    private String tvCodeStr;
    private double timeStart, timeEnd;
    private TextView tv_code;
    private ImageView iv;
    private SimpleDraweeView sdv_1;

    @Override public void ids() {
        tfa_load_img = (TitleFontAwesome) findViewById(R.id.tfa_load_img);
        tv_code = (TextView) findViewById(R.id.tv_code);
        iv = (ImageView) findViewById(R.id.iv);
        sdv_1 = (SimpleDraweeView) findViewById(R.id.sdv_1);
        findViewById(R.id.bt_glide).setOnClickListener(this);
        findViewById(R.id.bt_glide_app).setOnClickListener(this);
        findViewById(R.id.bt_picasso).setOnClickListener(this);
        findViewById(R.id.bt_fresco).setOnClickListener(this);
    }

    @Override public void onClick(View view) {
        String url = "http://dpic.tiankong.com/p9/um/QJ6256170643.jpg";
        iv.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher_round));
        sdv_1.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher_round));
        TTimer.timeFinished(1_000, () -> {
            timeStart = SystemClock.currentThreadTimeMillis();
            if (R.id.bt_glide == view.getId()) {
                Glide.with(this).load(url).into(iv);
                tvCodeStr = "Glide.with(this).load(url).into(iv)";
            } else if (R.id.bt_glide_app == view.getId()) {
                GlideApp.with(this).load(url).placeholder(R.mipmap.ic_launcher).into(iv);
                tvCodeStr = "GlideApp.with(this).load(url).placeholder(R.mipmap.ic_launcher).into(iv)";
            } else if (R.id.bt_picasso == view.getId()) {
                Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).into(iv);
                tvCodeStr = "Picasso.get().load(url).into(iv)";
            } else if (R.id.bt_fresco == view.getId()) {
                sdv_1.setImageURI(url);
                tvCodeStr = "sdv_1.setImageURI(url)";
            }
            timeEnd = SystemClock.currentThreadTimeMillis();
            tv_code.setText(tvCodeStr + "\n加载时长：" + (timeEnd - timeStart) + " || " + timeStart + "~" + timeEnd);
        });
    }

    @Override public void create() {
        initTitle();
    }

    private void initTitle() {
        tfa_load_img.setTitle("图片加载示例");
        tfa_load_img.setIcon1(R.string.fa_chevron_left);
        tfa_load_img.setOnClickListener((TitleFontAwesome.OnClickListener) view -> {
            if (R.id.tv_serial_1 == view.getId()) finish();
        });
    }
}






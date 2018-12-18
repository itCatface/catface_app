package cc.catface.api.huge_img.view;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.api.R;
import cc.catface.api.huge_img.presenter.LoadHugeImgPresenterImp;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsAppCompatActivityID;
import cc.catface.base.utils.android.common_title.TitleFontAwesome;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = "/api/loadHugeImg")
@CreatePresenter(LoadHugeImgPresenterImp.class)
public class LoadHugeImgActivity extends AbsAppCompatActivityID<LoadHugeImgView, LoadHugeImgPresenterImp> implements LoadHugeImgView {
    @Override public int layoutId() {
        return R.layout.api_activity_load_huge_img;
    }

    private TitleFontAwesome tfa_load_huge_img;
    private TextView tv_current_valid_memory;
    private ImageView iv;

    @Override public void ids() {
        tfa_load_huge_img = (TitleFontAwesome) findViewById(R.id.tfa_load_huge_img);
        tv_current_valid_memory = (TextView) findViewById(R.id.tv_current_valid_memory);
        iv = (ImageView) findViewById(R.id.iv);
    }

    @Override public void create() {
        initView();
        initEvent();
    }


    private void initView() {
        tfa_load_huge_img.setTitle("加载大图片");
        tv_current_valid_memory.setText((Runtime.getRuntime().maxMemory() / 1024 / 1024) + "m");
    }

    @SuppressLint("SetTextI18n") private void initEvent() {
        tfa_load_huge_img.setOnClickListener((TitleFontAwesome.OnClickListener) view -> {
            if (R.id.tv_serial_1 == view.getId()) {
                finish();
            }
        });
    }
}

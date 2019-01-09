package cc.catface.api.attrs;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.api.R;
import cc.catface.base.core_framework.base_normal.NormalBaseActivityID;
import cc.catface.base.utils.android.common_title.TitleFontAwesome;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = "/api/attrs/ivScaleType")
public class IVScaleTypeActivity extends NormalBaseActivityID {

    @Override public int layoutId() {
        return R.layout.api_activity_iv_scale_type;
    }

    private TitleFontAwesome tfa;

    @Override public void ids() {
        tfa = (TitleFontAwesome) findViewById(R.id.tfa);
        tfa.setTitle("scaleType属性").setIcon1(R.string.fa_chevron_left).setOnClickListener((TitleFontAwesome.OnClickListener) view -> {
            if (R.id.tv_serial_1 == view.getId()) finish();
        });
    }


    @Override public void create() {

    }
}

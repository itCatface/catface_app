package cc.catface.api.font;

import android.widget.Toast;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityDemoFontBinding;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.dialog.normal.TDialogNormal;
import cc.catface.base.utils.android.view.TFontType;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoFontFm extends NormalFragment<ApiActivityDemoFontBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_demo_font;
    }

    @Override public void createView() {
        initEachText();
    }

    private void initEachText() {
        TFontType.use(mBinding.tv1, TFontType.Font.hill_house, getString(R.string.wzhzjqeds));
        TFontType.use(mBinding.tv2, TFontType.Font.ayzt, getString(R.string.wzhzjqeds));
        TFontType.use(mBinding.tv3, TFontType.Font.cyls, getString(R.string.wzhzjqeds));
        TFontType.use(mBinding.tv4, TFontType.Font.ffts, getString(R.string.wzhzjqeds));
        TFontType.use(mBinding.tv5, TFontType.Font.gbxs, getString(R.string.wzhzjqeds));
        TFontType.use(mBinding.tv6, TFontType.Font.ldhzt, getString(R.string.wzhzjqeds));
        TFontType.use(mBinding.tv7, TFontType.Font.wyzt, getString(R.string.wzhzjqeds));
        TFontType.use(mBinding.tv8, TFontType.Font.qqzt, getString(R.string.wzhzjqeds));
        TFontType.use(mBinding.tv9, TFontType.Font.tkjt, getString(R.string.wzhzjqeds));
    }


    public void tbShowChoseDialog() {
        String[] types = TFontType.Font.getAllTypes().toArray(new String[0]);
        TDialogNormal.get(mActivity).list("选择系统字体", types, chosenPosition -> {
            Toast.makeText(mActivity, "" + types[chosenPosition], Toast.LENGTH_SHORT).show();
            TFontType.replaceFont(mActivity, types[chosenPosition]);
            TFontType.Font.CURRENT_SYSTEM_TYPE = types[chosenPosition];
        });
    }
}


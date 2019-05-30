package cc.catface.api.font;

import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityDemoFontBinding;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.dialog.normal.TDialogNormal;
import cc.catface.base.utils.android.common_title.TitleFontAwesome;
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
        TFontType.use(mBinding.tv1, TFontType.Font.hill_house, "其形也，翩若惊鸿，婉若游龙");
        TFontType.use(mBinding.tv2, TFontType.Font.ayzt, "荣曜秋菊，华茂春松");
        TFontType.use(mBinding.tv3, TFontType.Font.cyls, "髣髴兮若轻云之蔽月，飘飖兮若流风之回雪");
        TFontType.use(mBinding.tv4, TFontType.Font.ffts, "远而望之，皎若太阳升朝霞");
        TFontType.use(mBinding.tv5, TFontType.Font.gbxs, "迫而察之，灼若芙蕖出渌波");
        TFontType.use(mBinding.tv6, TFontType.Font.jfjc, "体迅飞凫，飘忽若神，凌波微步，罗袜生尘");
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


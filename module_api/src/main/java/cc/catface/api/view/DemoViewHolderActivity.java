package cc.catface.api.view;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.fragment.app.Fragment;
import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityDemoViewHolderBinding;
import cc.catface.api.view.demo01_astrs.DemoASTRSFm;
import cc.catface.api.view.demo02_interpolator.DemoInterpolatorFm;
import cc.catface.api.view.demo03_value.DemoValueFm;
import cc.catface.api.view.demo04_object.DemoObjectFm;
import cc.catface.api.view.demo05_propertyvaluesholder_keyframe.DemoKeyframeFm;
import cc.catface.api.view.demo06_anim_set.DemoAnimSetFm;
import cc.catface.api.view.demo101_paint_canvas.DemoCanvasFm;
import cc.catface.api.view.demo102_path_text.DemoTextFm;
import cc.catface.api.view.demo103_range.DemoRangeFm;
import cc.catface.api.view.demo104_bezier.DemoBezierMainFm;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.api_demo_view_holder)
public class DemoViewHolderActivity extends NormalActivity<ApiActivityDemoViewHolderBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_demo_view_holder;
    }

    @Override public void create() {
        int fm_id = getIntent().getIntExtra(Const.ARouter.fm_id_key, -1);
        switch (fm_id) {
            case Const.ARouter.fm_id_view_astr:
                title("astr");
                replace(new DemoASTRSFm());
                break;
            case Const.ARouter.fm_id_view_interpolator:
                title("interpolator");
                replace(new DemoInterpolatorFm());
                break;
            case Const.ARouter.fm_id_view_value:
                title("value");
                replace(new DemoValueFm());
                break;
            case Const.ARouter.fm_id_view_object:
                title("object");
                replace(new DemoObjectFm());
                break;
            case Const.ARouter.fm_id_view_keyframe:
                title("keyframe");
                replace(new DemoKeyframeFm());
                break;
            case Const.ARouter.fm_id_view_anim_set:
                title("anim_set");
                replace(new DemoAnimSetFm());
                break;
            case Const.ARouter.fm_id_view_paint_canvas:
                title("paint & canvas");
                replace(new DemoCanvasFm());
                break;
            case Const.ARouter.fm_id_view_path_text:
                title("path & text");
                replace(new DemoTextFm());
                break;
            case Const.ARouter.fm_id_view_range:
                title("range");
                replace(new DemoRangeFm());
                break;
            case Const.ARouter.fm_id_view_bezier_main:
                title("bezier_main");
                replace(new DemoBezierMainFm());
                break;
        }
    }

    public void title(String title) {
        mBinding.tfaView.setTitle(title).setIcon1(R.string.fa_chevron_left);
    }

    public void replace(Fragment fm) {
        replaceFragment(R.id.fl_view, fm);
    }
}

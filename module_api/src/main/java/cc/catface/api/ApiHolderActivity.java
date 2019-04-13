package cc.catface.api;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.fragment.app.Fragment;
import cc.catface.api.banner.DemoBannerFm;
import cc.catface.api.common.DemoSystemInfoFm;
import cc.catface.api.crash.DemoCrashFm;
import cc.catface.api.databinding.ApiActivityApiHolderBinding;
import cc.catface.api.databinding.DemoDataBindingFm;
import cc.catface.api.dialog.DemoDialogFm;
import cc.catface.api.eleme.DemoElemeFm;
import cc.catface.api.font.DemoFontFm;
import cc.catface.api.frame.DemoFrameFm;
import cc.catface.api.hardware.camera.DemoHardwareFm;
import cc.catface.api.huge_img.DemoLoadLargeImgFm;
import cc.catface.api.multi_finger.DemoMultiTouchFm;
import cc.catface.api.popup.DemoPopupFm;
import cc.catface.api.room.DemoRoomFm;
import cc.catface.api.toast.DemoToastFm;
import cc.catface.api.toolbar.DemoToolBarFm;
import cc.catface.api.toutiao.DemoToutiaoFm;
import cc.catface.api.view.DemoViewNavigationFm;
import cc.catface.api.view.loading.DemoSpinKitFm;
import cc.catface.api.view.loading.round_progress.DemoIflyTingjianViewFm;
import cc.catface.api.view.loading.round_smile.DemoRoundSmileFm;
import cc.catface.api.widget.DemoConstraintLayoutFm;
import cc.catface.api.widget.DemoIVScaleTypeFm;
import cc.catface.api.widget.DemoTextViewFm;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.api_holder)
public class ApiHolderActivity extends NormalActivity<ApiActivityApiHolderBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_api_holder;
    }

    @Override public void create() {
        int fm_id = getIntent().getIntExtra(Const.ARouter.fm_id_key, -1);
        switch (fm_id) {
            case Const.ARouter.fm_id_frame:
                replace(new DemoFrameFm());
                break;
            case Const.ARouter.fm_id_font_type:
                replace(new DemoFontFm());
                break;
            case Const.ARouter.fm_id_toast:
                replace(new DemoToastFm());
                break;
            case Const.ARouter.fm_id_dialog:
                replace(new DemoDialogFm());
                break;
            case Const.ARouter.fm_id_popup:
                replace(new DemoPopupFm());
                break;
            case Const.ARouter.fm_id_view_anim:
                replace(new DemoViewNavigationFm());
                break;
            case Const.ARouter.fm_id_hardware:
                replace(new DemoHardwareFm());
                break;
            case Const.ARouter.fm_id_rv_toutiao:
                replace(new DemoToutiaoFm());
                break;
            case Const.ARouter.fm_id_rv_banner:
                replace(new DemoBannerFm());
                break;
            case Const.ARouter.fm_id_eleme:
                replace(new DemoElemeFm());
                break;
            case Const.ARouter.fm_id_load_large_image:
                replace(new DemoLoadLargeImgFm());
                break;
            case Const.ARouter.fm_id_system_info:
                replace(new DemoSystemInfoFm());
                break;
            case Const.ARouter.fm_id_multi_touch:
                replace(new DemoMultiTouchFm());
                break;
            case Const.ARouter.fm_id_textview_serial:
                replace(new DemoTextViewFm());
                break;
            case Const.ARouter.fm_id_imageview_serial:
                replace(new DemoIVScaleTypeFm());
                break;
            case Const.ARouter.fm_id_constraintlayout:
                replace(new DemoConstraintLayoutFm());
                break;
            case Const.ARouter.fm_id_data_binding:
                replace(new DemoDataBindingFm());
                break;
            case Const.ARouter.fm_id_room:
                replace(new DemoRoomFm());
                break;
            case Const.ARouter.fm_id_crash:
                replace(new DemoCrashFm());
                break;
            case Const.ARouter.fm_id_toolbar:
                replace(new DemoToolBarFm());
                break;

            case Const.ARouter.fm_id_view_loading_spinkit:
                replace(new DemoSpinKitFm());
                break;
            case Const.ARouter.fm_id_view_loading_smile:
                replace(new DemoRoundSmileFm());
                break;
            case Const.ARouter.fm_id_view_loading_round_progress:
                replace(new DemoIflyTingjianViewFm());
                break;
        }
    }

    public void replace(Fragment fm) {
        replaceFragment(R.id.fl_api, fm);
    }
}

package cc.catface.api;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.api.banner.DemoBannerFm;
import cc.catface.api.clipboard.ClipboardFm;
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
import cc.catface.api.properties.DemoPropFm;
import cc.catface.api.room.DemoRoomFm;
import cc.catface.api.smallfunc.DemoSmallFuncFm;
import cc.catface.api.toast.DemoToastFm;
import cc.catface.api.toolbar.DemoToolBarFm;
import cc.catface.api.toutiao.DemoToutiaoFm;
import cc.catface.api.view.DemoViewNavigationFm;
import cc.catface.api.view.loading.DemoSpinKitFm;
import cc.catface.api.view.loading.round_progress.DemoIflyTingjianViewFm;
import cc.catface.api.view.loading.round_smile.DemoRoundSmileFm;
import cc.catface.api.viewpager2.DemoViewPager2Fm;
import cc.catface.api.widget.DemoConstraintLayoutFm;
import cc.catface.api.widget.DemoIVScaleTypeFm;
import cc.catface.api.widget.DemoTextViewFm;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.api_holder) public class ApiHolderActivity extends LightAct<LightPresenter, ApiActivityApiHolderBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_api_holder;
    }

    private String mFmId;
    private DemoSmallFuncFm fmSmallFunc = new DemoSmallFuncFm();
    private DemoFrameFm fmFrame = new DemoFrameFm();
    private DemoFontFm fmFont = new DemoFontFm();
    private DemoToastFm fmToast = new DemoToastFm();
    private DemoPropFm fmProp = new DemoPropFm();
    private DemoDialogFm fmDialog = new DemoDialogFm();
    private DemoPopupFm fmPopup = new DemoPopupFm();
    private DemoViewNavigationFm fmView = new DemoViewNavigationFm();
    private DemoHardwareFm fmHardware = new DemoHardwareFm();
    private DemoToutiaoFm fmToutiao = new DemoToutiaoFm();
    private DemoBannerFm fmBanner = new DemoBannerFm();
    private DemoElemeFm fmEleme = new DemoElemeFm();
    private DemoLoadLargeImgFm fmLargeImg = new DemoLoadLargeImgFm();
    private DemoSystemInfoFm fmSystemInfo = new DemoSystemInfoFm();
    private DemoMultiTouchFm fmMultiTouch = new DemoMultiTouchFm();
    private DemoTextViewFm fmTextView = new DemoTextViewFm();
    private DemoIVScaleTypeFm fmScaleType = new DemoIVScaleTypeFm();
    private DemoConstraintLayoutFm fmConstrainLayout = new DemoConstraintLayoutFm();
    private DemoDataBindingFm fmDataBinding = new DemoDataBindingFm();
    private DemoRoomFm fmRoom = new DemoRoomFm();
    private DemoCrashFm fmCrash = new DemoCrashFm();
    private DemoToolBarFm fmToolBar = new DemoToolBarFm();
    private DemoSpinKitFm fmSpinKit = new DemoSpinKitFm();
    private DemoRoundSmileFm fmRoundSmile = new DemoRoundSmileFm();
    private DemoIflyTingjianViewFm fmTingjian = new DemoIflyTingjianViewFm();
    private DemoViewPager2Fm fmViewPager2 = new DemoViewPager2Fm();
    private ClipboardFm clipboardFm = new ClipboardFm();

    @Override public void created() {
        initToolBar();

        /* fragment替换 */
        mFmId = getIntent().getStringExtra(Const.ARouter.fm_id_key);
        switch (mFmId) {
            case Const.ARouter.fm_id_api_small_func:
                mTitle = "小功能";
                replace(fmSmallFunc);
                break;
            case Const.ARouter.fm_id_api_frame:
                mTitle = "开发框架";
                mNormalTitle = "模式";
                replace(fmFrame);
                break;
            case Const.ARouter.fm_id_api_font_type:
                mTitle = "字体";
                mNormalTitle = "系统";
                replace(fmFont);
                break;
            case Const.ARouter.fm_id_api_toast:
                mTitle = "Toast";
                mNormalTitle = "清除";
                replace(fmToast);
                break;
            case Const.ARouter.fm_id_api_prop:
                mTitle = "properties";
                replace(fmProp);
                break;
            case Const.ARouter.fm_id_api_dialog:
                mTitle = "Dialog";
                replace(fmDialog);
                break;
            case Const.ARouter.fm_id_api_popup:
                mTitle = "Popup";
                replace(fmPopup);
                break;
            case Const.ARouter.fm_id_api_view_anim:
                mTitle = "View + Anim";
                replace(fmView);
                break;
            case Const.ARouter.fm_id_api_hardware:
                mTitle = "硬件";
                replace(fmHardware);
                break;
            case Const.ARouter.fm_id_rv_api_toutiao:
                mTitle = "今日头条";
                replace(fmToutiao);
                break;
            case Const.ARouter.fm_id_rv_api_banner:
                mTitle = "Banner";
                replace(fmBanner);
                break;
            case Const.ARouter.fm_id_api_eleme:
                mTitle = "菜单";
                replace(fmEleme);
                break;
            case Const.ARouter.fm_id_api_load_large_image:
                mTitle = "加载大图片";
                replace(fmLargeImg);
                break;
            case Const.ARouter.fm_id_api_system_info:
                mTitle = "系统信息";
                replace(fmSystemInfo);
                break;
            case Const.ARouter.fm_id_api_multi_touch:
                mTitle = "多点触控";
                replace(fmMultiTouch);
                break;
            case Const.ARouter.fm_id_api_textview_serial:
                mTitle = "文字高亮";
                replace(fmTextView);
                break;
            case Const.ARouter.fm_id_api_imageview_serial:
                mTitle = "scaleType";
                replace(fmScaleType);
                break;
            case Const.ARouter.fm_id_api_constraintlayout:
                mTitle = "ConstraintLayout";
                replace(fmConstrainLayout);
                break;
            case Const.ARouter.fm_id_api_data_binding:
                mTitle = "DataBinding";
                mNormalTitle = "更新";
                replace(fmDataBinding);
                break;
            case Const.ARouter.fm_id_api_room:
                mTitle = "Room";
                replace(fmRoom);
                break;
            case Const.ARouter.fm_id_api_crash:
                mTitle = "Crash";
                replace(fmCrash);
                break;
            case Const.ARouter.fm_id_api_toolbar:
                mTitle = "ToolBar";
                replace(fmToolBar);
                break;

            case Const.ARouter.fm_id_view_loading_spinkit:
                mTitle = "";
                replace(fmSpinKit);
                break;
            case Const.ARouter.fm_id_view_loading_smile:
                mTitle = "";
                replace(fmRoundSmile);
                break;
            case Const.ARouter.fm_id_view_loading_round_progress:
                mTitle = "";
                replace(fmTingjian);
                break;

            case Const.ARouter.fm_id_api_viewpager2:
                mTitle = "viewpager2";
                replace(fmViewPager2);
                break;
            case Const.ARouter.fm_id_api_clipboard:
                mTitle = "clipboard";
                replace(clipboardFm);
                break;
        }

        /* toolbar支持 */
        updateToolBar();
    }

    private ActionBar mBar;

    private void initToolBar() {
        Toolbar toolbar = mBinding.iApiHolder.tbTitle;
        setSupportActionBar(toolbar);
        mBar = getSupportActionBar();
        if (null != mBar) mBar.setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.flaticon_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private String mTitle = "", mNormalTitle = "";

    private void updateToolBar() {
        if (null != mBar) {
            mBar.setTitle(mTitle);
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_menu, menu);
        menu.findItem(R.id.menu_search).setVisible(false);
        menu.findItem(R.id.menu_normal).setVisible(!TextUtils.isEmpty(mNormalTitle.trim()));
        menu.findItem(R.id.menu_plus_1).setVisible(false);
        menu.findItem(R.id.menu_plus_2).setVisible(false);

        menu.findItem(R.id.menu_normal).setTitle(mNormalTitle);
        menu.findItem(R.id.menu_normal).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (R.id.menu_normal == id) {
            switch (mFmId) {
                case Const.ARouter.fm_id_api_frame:
                    fmFrame.tbShowChoseDialog();
                    break;
                case Const.ARouter.fm_id_api_font_type:
                    fmFont.tbShowChoseDialog();
                    break;
                case Const.ARouter.fm_id_api_toast:
                    fmToast.tbClear();
                    break;
                case Const.ARouter.fm_id_rv_api_banner:
                    fmBanner.tbShowChoseDialog();
                    break;
                case Const.ARouter.fm_id_api_data_binding:
                    fmDataBinding.tbUpdateData();
                    break;
            }
        }


        switch (mFmId) {
            case Const.ARouter.fm_id_api_toast:
                break;
            case Const.ARouter.fm_id_api_dialog:
                break;
            case Const.ARouter.fm_id_api_popup:
                break;
            case Const.ARouter.fm_id_api_view_anim:
                break;
            case Const.ARouter.fm_id_api_hardware:
                break;
            case Const.ARouter.fm_id_rv_api_toutiao:
                break;
            case Const.ARouter.fm_id_rv_api_banner:
                break;
            case Const.ARouter.fm_id_api_eleme:
                break;
            case Const.ARouter.fm_id_api_load_large_image:
                break;
            case Const.ARouter.fm_id_api_system_info:
                break;
            case Const.ARouter.fm_id_api_multi_touch:
                break;
            case Const.ARouter.fm_id_api_textview_serial:
                break;
            case Const.ARouter.fm_id_api_imageview_serial:
                break;
            case Const.ARouter.fm_id_api_constraintlayout:
                break;
            case Const.ARouter.fm_id_api_data_binding:
                break;
            case Const.ARouter.fm_id_api_room:
                break;
            case Const.ARouter.fm_id_api_crash:
                break;
            case Const.ARouter.fm_id_api_toolbar:
                break;

            case Const.ARouter.fm_id_view_loading_spinkit:
                break;
            case Const.ARouter.fm_id_view_loading_smile:
                break;
            case Const.ARouter.fm_id_view_loading_round_progress:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void replace(Fragment fm) {
        replaceFragment(R.id.fl_api, fm);
    }
}

package cc.catface.api.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityViewNavigationBinding;
import cc.catface.api.view.adapter.ViewNavigationAdapter;
import cc.catface.api.view.anim_activity_transfer.activity.TransferMainActivity;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_intent.TIntent;
import cc.catface.base.utils.android.common_print.popup.TPopup;
import cc.catface.base.utils.android.common_recyclerview.TRV;

public class DemoViewNavigationFm extends NormalFragment<ApiActivityViewNavigationBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_view_navigation;
    }

    private final String  NORMAL_astrs = "(系统)astrs", NORMAL_interpolator = "(系统)interpolator", NORMAL_value = "(系统)value", NORMAL_object = "(系统)object", NORMAL_PropertyValuesHolder_Keyframe = "(系统)" +
            "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "PropertyValuesHolder_Keyframe", NORMAL_set = "(系统)api_set", NORMAL_paint_canvas = "(系统)paint_canvas", NORMAL_path_text
            = "" + "(系统)" + "path_text", NORMAL_range = "(系统)" + "" + "range", NORMAL_bezier = "" + "(系统)bezier", NORMAL_loading = "(系统)loading", SPACING = "", NORMAL_ACTIVITY_TRANSFER_ANIMATION =
            "activity切换动画";
    private List<String> mDatas;
    private ViewNavigationAdapter mAdapter;

    @Override public void initData() {
        mDatas = new ArrayList<>();
        mDatas.add(NORMAL_astrs);
        mDatas.add(NORMAL_interpolator);
        mDatas.add(NORMAL_value);
        mDatas.add(NORMAL_object);
        mDatas.add(NORMAL_PropertyValuesHolder_Keyframe);
        mDatas.add(NORMAL_set);
        mDatas.add(NORMAL_paint_canvas);
        mDatas.add(NORMAL_path_text);
        mDatas.add(NORMAL_range);
        mDatas.add(NORMAL_bezier);
        mDatas.add(NORMAL_loading);
        mDatas.add(SPACING);
        mDatas.add(NORMAL_ACTIVITY_TRANSFER_ANIMATION);
    }

    @Override public void createView() {
        initAdapter();
    }

    private void initAdapter() {
        TRV.initDefaultRV(mActivity, mBinding.rvView);

        mAdapter = new ViewNavigationAdapter(mDatas);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (mDatas.get(position)) {
                case NORMAL_astrs:
                    ARouter.getInstance().build(Const.ARouter.api_demo_view_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_astr).navigation();
                    break;
                case NORMAL_interpolator:
                    ARouter.getInstance().build(Const.ARouter.api_demo_view_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_interpolator).navigation();
                    break;
                case NORMAL_value:
                    ARouter.getInstance().build(Const.ARouter.api_demo_view_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_value).navigation();
                    break;
                case NORMAL_object:
                    ARouter.getInstance().build(Const.ARouter.api_demo_view_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_object).navigation();
                    break;
                case NORMAL_PropertyValuesHolder_Keyframe:
                    ARouter.getInstance().build(Const.ARouter.api_demo_view_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_keyframe).navigation();
                    break;
                case NORMAL_set:
                    ARouter.getInstance().build(Const.ARouter.api_demo_view_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_anim_set).navigation();
                    break;
                case NORMAL_paint_canvas:
                    ARouter.getInstance().build(Const.ARouter.api_demo_view_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_paint_canvas).navigation();
                    break;
                case NORMAL_path_text:
                    ARouter.getInstance().build(Const.ARouter.api_demo_view_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_path_text).navigation();
                    break;
                case NORMAL_range:
                    ARouter.getInstance().build(Const.ARouter.api_demo_view_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_range).navigation();
                    break;
                case NORMAL_bezier:
                    ARouter.getInstance().build(Const.ARouter.api_demo_view_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_bezier_main).navigation();
                    break;
                case NORMAL_loading:
                    String[] items_rv = getResources().getStringArray(R.array.api_view_anim_list_loading);
                    TPopup.get(mActivity).show(mBinding.rvView, "loading", items_rv, pos -> {
                        if (items_rv[pos].equals(getResources().getString(R.string.api_view_anim_list_loading_spinkit))) {
                            ARouter.getInstance().build(Const.ARouter.api_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_loading_spinkit).navigation();
                        } else if (items_rv[pos].equals(getResources().getString(R.string.api_view_anim_list_loading_smile))) {
                            ARouter.getInstance().build(Const.ARouter.api_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_loading_smile).navigation();
                        } else if (items_rv[pos].equals(getResources().getString(R.string.api_view_anim_list_loading_round_progress))) {
                            ARouter.getInstance().build(Const.ARouter.api_holder).withInt(Const.ARouter.fm_id_key, Const.ARouter.fm_id_view_loading_round_progress).navigation();
                        }
                    });
                    break;
                case NORMAL_ACTIVITY_TRANSFER_ANIMATION:
                    TIntent.startActivity(mActivity, TransferMainActivity.class, true);
                    break;
            }
        });
        mBinding.rvView.setAdapter(mAdapter);
    }
}

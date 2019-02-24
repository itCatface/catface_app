package cc.catface.api.view;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityViewNavigationBinding;
import cc.catface.api.view.adapter.ViewNavigationAdapter;
import cc.catface.api.view.anim_activity_transfer.activity.TransferMainActivity;
import cc.catface.api.view.demo01_astrs.ASTRSActivity;
import cc.catface.api.view.demo02_interpolator.InterpolatorActivity;
import cc.catface.api.view.demo03_value.ValueActivity;
import cc.catface.api.view.demo04_object.ObjectActivity;
import cc.catface.api.view.demo05_propertyvaluesholder_keyframe.KeyframeActivity;
import cc.catface.api.view.demo06_anim_set.AnimSetActivity;
import cc.catface.api.view.demo101_paint_canvas.CanvasActivity;
import cc.catface.api.view.demo102_path_text.TextActivity;
import cc.catface.api.view.demo103_range.RangeActivity;
import cc.catface.api.view.demo104_bezier.BezierMainActivity;
import cc.catface.api.view.loading.LoadingIndexActivity;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.utils.android.common_intent.TIntent;
import cc.catface.base.utils.android.common_recyclerview.TRV;

@Route(path = Const.AROUTER.api_navigation)
public class ViewNavigationActivity extends NormalActivity<ApiActivityViewNavigationBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_view_navigation;
    }

    private final String NORMAL_astrs = "(系统)astrs", NORMAL_interpolator = "(系统)interpolator", NORMAL_value = "(系统)value", NORMAL_object = "(系统)object", NORMAL_PropertyValuesHolder_Keyframe = "(系统)" +
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

    @Override public void create() {
        title();
        initAdapter();
    }

    private void title() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.AROUTER.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
    }

    private void initAdapter() {
        TRV.initDefaultRV(this, mBinding.rvView);

        mAdapter = new ViewNavigationAdapter(mDatas);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (mDatas.get(position)) {
                case NORMAL_astrs:
                    TIntent.startActivity(this, ASTRSActivity.class, true);
                    break;
                case NORMAL_interpolator:
                    TIntent.startActivity(this, InterpolatorActivity.class, true);
                    break;
                case NORMAL_value:
                    TIntent.startActivity(this, ValueActivity.class, true);
                    break;
                case NORMAL_object:
                    TIntent.startActivity(this, ObjectActivity.class, true);
                    break;
                case NORMAL_PropertyValuesHolder_Keyframe:
                    TIntent.startActivity(this, KeyframeActivity.class, true);
                    break;
                case NORMAL_set:
                    TIntent.startActivity(this, AnimSetActivity.class, true);
                    break;
                case NORMAL_paint_canvas:
                    TIntent.startActivity(this, CanvasActivity.class, true);
                    break;
                case NORMAL_path_text:
                    TIntent.startActivity(this, TextActivity.class, true);
                    break;
                case NORMAL_range:
                    TIntent.startActivity(this, RangeActivity.class, true);
                    break;
                case NORMAL_bezier:
                    TIntent.startActivity(this, BezierMainActivity.class, true);
                    break;
                case NORMAL_loading:
                    TIntent.startActivity(this, LoadingIndexActivity.class, true);
                    break;
                case NORMAL_ACTIVITY_TRANSFER_ANIMATION:
                    TIntent.startActivity(this, TransferMainActivity.class, true);
                    break;
            }
        });
        mBinding.rvView.setAdapter(mAdapter);
    }
}

package cc.catface.api.toast;

import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFmToastBinding;
import cc.catface.api.toast.adapter.ToastAdapter;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.android.common_recyclerview.TRV;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoToastFm extends LightFm<LightPresenter, ApiFmToastBinding> {

    @Override public int layoutId() {
        return R.layout.api_fm_toast;
    }

    private final String NORMAL_SHORT = "系统普通Toast[短]", NORMAL_LONG = "系统普通Toast[长]", CONTENT_SHORT = "立即更新Toast显示内容[短]", CONTENT_LONG = "立即更新Toast显示内容[长]", IMMEDIATELY_SHORT = "立即弹出Toast[短]", IMMEDIATELY_LONG = "立即弹出Toast[长]", CUSTOM_GRAVITY_SHORT = "自定义gravity[短]", CUSTOM_GRAVITY_LONG = "自定义gravity[长]", CUSTOM_LOCATION_SHORT = "自定义位置[短]", CUSTOM_LOCATION_LONG = "自定义位置[长]", B_TOAST_NORMAL_SHORT = "定制Toast[短normal]", B_TOAST_INFO_SHORT = "定制Toast[短info]", B_TOAST_SUCCESS_SHORT = "定制Toast[短success]", B_TOAST_CANCEL_SHORT = "定制Toast[短cancel]", B_TOAST_WARNING_SHORT = "定制Toast[短warning]", B_TOAST_ERROR_SHORT = "定制Toast[短error]", B_TOAST_ANIM_SHORT = "定制Toast动画[短]", B_TOAST_ANIM_LONG = "定制Toast动画[长]", TOAST_PERIOD = "自定义Toast显示时长";
    private List<String> mDatas;
    private ToastAdapter mAdapter;

    @Override public void initData() {
        mDatas = new ArrayList<>();
        mDatas.add(NORMAL_SHORT);
        mDatas.add(NORMAL_LONG);
        mDatas.add(CONTENT_SHORT);
        mDatas.add(CONTENT_LONG);
        mDatas.add(IMMEDIATELY_SHORT);
        mDatas.add(IMMEDIATELY_LONG);
        mDatas.add(CUSTOM_GRAVITY_SHORT);
        mDatas.add(CUSTOM_GRAVITY_LONG);
        mDatas.add(CUSTOM_LOCATION_SHORT);
        mDatas.add(CUSTOM_LOCATION_LONG);
        mDatas.add(B_TOAST_NORMAL_SHORT);
        mDatas.add(B_TOAST_INFO_SHORT);
        mDatas.add(B_TOAST_SUCCESS_SHORT);
        mDatas.add(B_TOAST_CANCEL_SHORT);
        mDatas.add(B_TOAST_WARNING_SHORT);
        mDatas.add(B_TOAST_ERROR_SHORT);
        mDatas.add(B_TOAST_ANIM_SHORT);
        mDatas.add(B_TOAST_ANIM_LONG);
        mDatas.add(TOAST_PERIOD);
    }


    @Override protected void created() {
        initAdapter();
    }

    private void initAdapter() {
        TRV.initDefaultRV(mActivity, mBinding.rvToast);

        mAdapter = new ToastAdapter(mDatas);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (mDatas.get(position)) {
                case NORMAL_SHORT:
                    TToast.get(mActivity).showShortNormal(NORMAL_SHORT + System.currentTimeMillis());
                    break;
                case NORMAL_LONG:
                    TToast.get(mActivity).showLongNormal(NORMAL_LONG + System.currentTimeMillis());
                    break;
                case CONTENT_SHORT:
                    TToast.get(mActivity).showShortContent(CONTENT_SHORT + System.currentTimeMillis());
                    break;
                case CONTENT_LONG:
                    TToast.get(mActivity).showLongContent(CONTENT_LONG + System.currentTimeMillis());
                    break;
                case IMMEDIATELY_SHORT:
                    TToast.get(mActivity).showShortImmediately(IMMEDIATELY_SHORT + System.currentTimeMillis());
                    break;
                case IMMEDIATELY_LONG:
                    TToast.get(mActivity).showLongImmediately(IMMEDIATELY_LONG + System.currentTimeMillis());
                    break;
                case CUSTOM_GRAVITY_SHORT:
                    int[] gravities1 = {Gravity.TOP, Gravity.LEFT, Gravity.RIGHT, Gravity.BOTTOM, Gravity.CENTER,};
                    int gravityPos1 = new Random().nextInt(gravities1.length);
                    TToast.get(mActivity).showShortGravity(gravities1[gravityPos1] + " || " + System.currentTimeMillis(), gravities1[gravityPos1]);
                    break;
                case CUSTOM_GRAVITY_LONG:
                    int[] gravities2 = {Gravity.TOP, Gravity.LEFT, Gravity.RIGHT, Gravity.BOTTOM, Gravity.CENTER};
                    int gravityPos2 = new Random().nextInt(gravities2.length);
                    TToast.get(mActivity).showLongGravity(gravities2[gravityPos2] + " || " + System.currentTimeMillis(), gravities2[gravityPos2]);
                    break;
                case CUSTOM_LOCATION_SHORT:
                    TToast.get(mActivity).showShortLocationLeftBelow(CUSTOM_LOCATION_SHORT + System.currentTimeMillis(), view);
                    break;
                case CUSTOM_LOCATION_LONG:
                    TToast.get(mActivity).showLongLocationLeftBelow(CUSTOM_LOCATION_LONG + System.currentTimeMillis(), view);
                    break;
                case B_TOAST_NORMAL_SHORT:
                    TToast.get(mActivity).showBShortView(B_TOAST_NORMAL_SHORT + System.currentTimeMillis(), TToast.B_NORMAL);
                    break;
                case B_TOAST_INFO_SHORT:
                    TToast.get(mActivity).showBShortView(B_TOAST_INFO_SHORT + System.currentTimeMillis(), TToast.B_INFO);
                    break;
                case B_TOAST_SUCCESS_SHORT:
                    TToast.get(mActivity).showBShortView(B_TOAST_SUCCESS_SHORT + System.currentTimeMillis(), TToast.B_SUCCESS);
                    break;
                case B_TOAST_CANCEL_SHORT:
                    TToast.get(mActivity).showBShortView(B_TOAST_CANCEL_SHORT + System.currentTimeMillis(), TToast.B_CANCEL);
                    break;
                case B_TOAST_WARNING_SHORT:
                    TToast.get(mActivity).showBShortView(B_TOAST_WARNING_SHORT + System.currentTimeMillis(), TToast.B_WARNING);
                    break;
                case B_TOAST_ERROR_SHORT:
                    TToast.get(mActivity).showBShortView(B_TOAST_ERROR_SHORT + System.currentTimeMillis(), TToast.B_ERROR);
                    break;
                case B_TOAST_ANIM_SHORT:
                    TToast.get(mActivity).showShortAnim(B_TOAST_ANIM_SHORT + System.currentTimeMillis(), R.style.toast_default_anim_view);
                    break;
                case B_TOAST_ANIM_LONG:
                    TToast.get(mActivity).showLongAnim(B_TOAST_ANIM_LONG + System.currentTimeMillis(), R.style.toast_default_anim_view);
                    break;
                case TOAST_PERIOD:
                    Toast toast = Toast.makeText(mActivity, "测试文本", Toast.LENGTH_LONG);
                    TToast.get(mActivity).showPeriodToast(toast, Integer.parseInt(mBinding.etPeriod.getText().toString().trim()));
                    break;
            }
        });
        mBinding.rvToast.setAdapter(mAdapter);
    }


    public void tbClear() {
        TToast.get(mActivity).clearToast();
    }
}

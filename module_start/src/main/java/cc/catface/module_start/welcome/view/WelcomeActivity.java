package cc.catface.module_start.welcome.view;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpActivity;
import cc.catface.base.utils.android.common_intent.TIntent;
import cc.catface.base.utils.android.view.viewpager.AccordionTransformer;
import cc.catface.base.utils.android.view.viewpager.base.TransAnim;
import cc.catface.module_start.R;
import cc.catface.module_start.ad.view.AdActivity;
import cc.catface.module_start.databinding.StartActivityWelcomeBinding;
import cc.catface.module_start.welcome.adapter.WelcomeAdapter;
import cc.catface.module_start.welcome.presenter.WelcomePresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.start_welcome)
@CreatePresenter(WelcomePresenterImp.class)
public class WelcomeActivity extends MvpActivity<WelcomeView, WelcomePresenterImp, StartActivityWelcomeBinding> implements WelcomeView, View.OnClickListener {
    @Override public int layoutId() {
        return R.layout.start_activity_welcome;
    }

    private int[] mImageIDs;
    private List<View> mImageViews;
    private LinearLayout.LayoutParams mImageParams;
    private LinearLayout.LayoutParams focusParams, defaultParams;

    @Override public void onClick(View view) {
        if (R.id.bt_vp_anim == view.getId()) {
            openPop(view);
        } else if (R.id.bt_start == view.getId()) {
            TIntent.startActivityAndFinish(this, AdActivity.class, true);
        }
    }

    @Override protected void initAction() {
        mBinding.btVpAnim.setOnClickListener(this);
        mBinding.btStart.setOnClickListener(this);
    }

    @Override public void create() {
        initParams();

        initViewPager();

        initPoint();
    }

    private void initParams() {
        mImageIDs = new int[]{R.drawable.start_indicator_1, R.drawable.start_indicator_2, R.drawable.start_indicator_3, R.drawable.start_indicator_4, R.drawable.start_indicator_5};
        mImageViews = new ArrayList<>();
        mImageParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        focusParams = new LinearLayout.LayoutParams(30, 10);
        focusParams.setMargins(10, 0, 10, 0);
        defaultParams = new LinearLayout.LayoutParams(10, 10);
        defaultParams.setMargins(10, 0, 10, 0);
    }


    private void initViewPager() {
        for (int mImageID : mImageIDs) {    // 集合引导图
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(mImageParams);
            imageView.setBackgroundResource(mImageID);

            mImageViews.add(imageView);
        }

        mBinding.vpIndicator.setPageTransformer(true, new AccordionTransformer());
        mBinding.vpIndicator.setAdapter(new WelcomeAdapter(mImageViews));
        addViewPagerListener();
    }

    private void addViewPagerListener() {
        mBinding.vpIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override public void onPageSelected(int position) {
                mBinding.llIndicator.removeAllViews();

                paramsLogic(position);

                mBinding.btStart.setVisibility(position == mImageIDs.length - 1 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override public void onPageScrollStateChanged(int state) { }
        });
    }


    private ImageView[] mPointViews;

    private void initPoint() {
        mPointViews = new ImageView[mImageViews.size()];

        paramsLogic(0);
    }

    private void paramsLogic(int position) {
        for (int i = 0; i < mImageViews.size(); i++) {
            ImageView iv_point = new ImageView(WelcomeActivity.this);

            if (i == position) {
                iv_point.setBackgroundResource(R.drawable.shape_indicator_focus);
                iv_point.setTag("0");
            } else {
                iv_point.setBackgroundResource(R.drawable.shape_indicator_default);
                iv_point.setTag("-1");
            }

            mPointViews[i] = iv_point;
            if ("0".equals(iv_point.getTag())) mBinding.llIndicator.addView(iv_point, focusParams);
            else mBinding.llIndicator.addView(iv_point, defaultParams);
        }
    }


    /** viewpager切换动画控制弹窗 */
    private PopupWindow mPop;

    private void openPop(View v) {
        if (null != mPop && mPop.isShowing()) return;

        View view = LayoutInflater.from(this).inflate(R.layout.pop_start_indicator_chose_anim, null);
        mPop = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mPop.setBackgroundDrawable(new BitmapDrawable());
        mPop.setFocusable(true);
        mPop.setOutsideTouchable(true);
        mPop.setAnimationStyle(R.style.PopupWindow);    // 进出动画
        mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        setBackgroundAlpha(0.5f);
        mPop.setOnDismissListener(() -> setBackgroundAlpha(1));
        viewpagerTransAnimCtrl(view);
    }

    private void dismissPop() {
        if (mPop != null && mPop.isShowing()) mPop.dismiss();
    }

    private void viewpagerTransAnimCtrl(View view) {
        view.findViewById(R.id.tv_accordionTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.accordionTransformer);
        });
        view.findViewById(R.id.tv_backgroundToForegroundTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.backgroundToForegroundTransformer);
        });
        view.findViewById(R.id.tv_cubeInTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.cubeInTransformer);
        });
        view.findViewById(R.id.tv_cubeOutTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.cubeOutTransformer);
        });
        view.findViewById(R.id.tv_depthPageTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.depthPageTransformer);
        });
        view.findViewById(R.id.tv_depthPageTransformer2).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.depthPageTransformer2);
        });
        view.findViewById(R.id.tv_drawFromBackTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.drawFromBackTransformer);
        });
        view.findViewById(R.id.tv_flipHorizontalTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.flipHorizontalTransformer);
        });
        view.findViewById(R.id.tv_flipVerticalTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.flipVerticalTransformer);
        });
        view.findViewById(R.id.tv_foregroundToBackgroundTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.foregroundToBackgroundTransformer);
        });
        view.findViewById(R.id.tv_parallaxPageTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.parallaxPageTransformer);
        });
        view.findViewById(R.id.tv_rotateDownTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.rotateDownTransformer);
        });
        view.findViewById(R.id.tv_rotateUpTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.rotateUpTransformer);
        });
        view.findViewById(R.id.tv_stackTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.stackTransformer);
        });
        view.findViewById(R.id.tv_tabletTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.tabletTransformer);
        });
        view.findViewById(R.id.tv_zoomInTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.zoomInTransformer);
        });
        view.findViewById(R.id.tv_zoomOutPageTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.zoomOutPageTransformer);
        });
        view.findViewById(R.id.tv_zoomOutSlideTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.zoomOutSlideTransformer);
        });
        view.findViewById(R.id.tv_zoomOutTransformer).setOnClickListener(v -> {
            dismissPop();
            mBinding.vpIndicator.setPageTransformer(true, TransAnim.zoomOutTransformer);
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(v -> dismissPop());
    }
}

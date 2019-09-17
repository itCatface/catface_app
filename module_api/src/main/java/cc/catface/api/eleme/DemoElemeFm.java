package cc.catface.api.eleme;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityElemeBinding;
import cc.catface.api.eleme.adapter.ElemeGVAdapter;
import cc.catface.api.eleme.adapter.ElemeVPAdapter;
import cc.catface.api.eleme.adapter.SinglePageMultiChosenGVAdapter;
import cc.catface.api.eleme.adapter.TextGVAdapter;
import cc.catface.api.eleme.adapter.TextVPAdapter;
import cc.catface.api.eleme.domain.ElemeMainBean;
import cc.catface.api.eleme.domain.ElemeSinglePageMultiChosenPersonInfo;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.toast.TToast;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoElemeFm extends NormalFragment<ApiActivityElemeBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_eleme;
    }

    @Override public void createView() {
        demoElemeMenu();

        demoTextMenu();

        singlePageMultiChosen();
    }


    /** 饿了么菜单示例-单选 */
    private ImageView[] ivElemePoints;
    private List<ElemeMainBean> mElemeDatas = new ArrayList<>();
    private List<View> mElemeVps = new ArrayList<>();
    private int mElemeTotalPages;
    private int mElemeMaxPages = 8;
    private int mElemeCurrentPageIndex = 0;

    private void demoElemeMenu() {
        // 来点菜单数据
        for (int i = 0; i < 20; i++) {
            mElemeDatas.add(new ElemeMainBean(-1, "菜单" + i));
        }

        // 计算得出菜单栏总页数
        mElemeTotalPages = (int) Math.ceil(mElemeDatas.size() * 1.0 / mElemeMaxPages);

        // 根据菜单栏总页数创建对应数量的GridView并添加至ViewPager中
        for (int i = 0; i < mElemeTotalPages; i++) {
            GridView gv = (GridView) View.inflate(mActivity, R.layout.api_item_gv_eleme_main, null);
            gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
            gv.setAdapter(new ElemeGVAdapter(mActivity, mElemeDatas, i, mElemeMaxPages));
            gv.setOnItemClickListener((adapterView, view, position, l) -> TToast.get(mActivity).showShortNormal("当前点击：" + mElemeDatas.get(position + mElemeCurrentPageIndex * mElemeMaxPages).getLabel()));

            mElemeVps.add(gv);
        }

        mBinding.vpElemePages.setAdapter(new ElemeVPAdapter(mElemeVps));

        // 初始化小圆点参数
        LinearLayout.LayoutParams paramsFocused, paramsDefault;
        paramsFocused = new LinearLayout.LayoutParams(30, 30);
        paramsFocused.setMargins(15, 0, 15, 0);
        paramsDefault = new LinearLayout.LayoutParams(30, 30);
        paramsDefault.setMargins(15, 0, 15, 0);

        ivElemePoints = new ImageView[mElemeTotalPages];
        for (int i = 0; i < mElemeTotalPages; i++) {
            ivElemePoints[i] = new ImageView(mActivity);
            if (i == 0) {
                ivElemePoints[i].setImageResource(R.drawable.shape_package_indicator_fouse);
                mBinding.llElemePoints.addView(ivElemePoints[i], paramsFocused);
            } else {
                ivElemePoints[i].setImageResource(R.drawable.shape_package_indicator_normal);
                mBinding.llElemePoints.addView(ivElemePoints[i], paramsDefault);
            }
        }

        mBinding.vpElemePages.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override public void onPageSelected(int position) {
                mBinding.llElemePoints.removeAllViews();
                mElemeCurrentPageIndex = position;
                for (int i = 0; i < mElemeTotalPages; i++) {
                    if (i == position) {
                        ivElemePoints[i].setImageResource(R.drawable.shape_package_indicator_fouse);
                        mBinding.llElemePoints.addView(ivElemePoints[i], paramsFocused);
                    } else {
                        ivElemePoints[i].setImageResource(R.drawable.shape_package_indicator_normal);
                        mBinding.llElemePoints.addView(ivElemePoints[i], paramsDefault);
                    }
                }
            }
        });
    }


    /** 单文本条目-单选 */
    private ImageView[] ivTextPoints;
    private List<String> mTextDatas = new ArrayList<>();
    private List<GridView> mTextVps = new ArrayList<>();
    private int mTextTotalPages;
    private int mTextMaxPages = 20;
    private int mTextCurrentPageIndex = 0;

    private void demoTextMenu() {
        // 来点菜单数据
        for (int i = 0; i < 77; i++) {
            mTextDatas.add("NO." + (i < 10 ? "[0" : "[") + i + "]");
        }

        // 计算得出菜单栏总页数
        mTextTotalPages = (int) Math.ceil(mTextDatas.size() * 1.0 / mTextMaxPages);

        // 根据菜单栏总页数创建对应数量的GridView并添加至ViewPager中
        for (int i = 0; i < mTextTotalPages; i++) {
            GridView gv = (GridView) View.inflate(mActivity, R.layout.api_item_gv_text_main, null);
            gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
            gv.setAdapter(new TextGVAdapter(mActivity, mTextDatas, i, mTextMaxPages));
            refreshAdapters(0);
            gv.setOnItemClickListener((adapterView, view, position, l) -> {
                int realPosition = position + mTextCurrentPageIndex * mTextMaxPages;
                TToast.get(mActivity).showShortNormal("当前点击：" + mTextDatas.get(position + mTextCurrentPageIndex * mTextMaxPages));
                refreshAdapters(realPosition);
            });

            mTextVps.add(gv);
        }

        mBinding.vpTextPages.setAdapter(new TextVPAdapter(mTextVps));

        // 初始化小圆点参数
        LinearLayout.LayoutParams paramsFocused, paramsDefault;
        paramsFocused = new LinearLayout.LayoutParams(30, 30);
        paramsFocused.setMargins(15, 0, 15, 0);
        paramsDefault = new LinearLayout.LayoutParams(30, 30);
        paramsDefault.setMargins(15, 0, 15, 0);

        ivTextPoints = new ImageView[mTextTotalPages];
        for (int i = 0; i < mTextTotalPages; i++) {
            ivTextPoints[i] = new ImageView(mActivity);
            if (i == 0) {
                ivTextPoints[i].setImageResource(R.drawable.shape_package_indicator_fouse);
                mBinding.llTextPoints.addView(ivTextPoints[i], paramsFocused);
            } else {
                ivTextPoints[i].setImageResource(R.drawable.shape_package_indicator_normal);
                mBinding.llTextPoints.addView(ivTextPoints[i], paramsDefault);
            }
        }

        mBinding.vpTextPages.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override public void onPageSelected(int position) {
                mBinding.llTextPoints.removeAllViews();
                mTextCurrentPageIndex = position;
                for (int i = 0; i < mTextTotalPages; i++) {
                    if (i == position) {
                        ivTextPoints[i].setImageResource(R.drawable.shape_package_indicator_fouse);
                        mBinding.llTextPoints.addView(ivTextPoints[i], paramsFocused);
                    } else {
                        ivTextPoints[i].setImageResource(R.drawable.shape_package_indicator_normal);
                        mBinding.llTextPoints.addView(ivTextPoints[i], paramsDefault);
                    }
                }
            }
        });
    }


    private void refreshAdapters(int position) {
        for (int i = 0; i < mTextVps.size(); i++) {
            TextGVAdapter adapter = (TextGVAdapter) mTextVps.get(i).getAdapter();
            if (i == mTextCurrentPageIndex) {
                adapter.setSelectedPosition(mTextCurrentPageIndex, position, true);
            } else {
                adapter.setSelectedPosition(i, position, false);
            }
            adapter.notifyDataSetChanged();
        }
    }


    /** 单页条目-多选 */
    private List<ElemeSinglePageMultiChosenPersonInfo> mSinglePageMultiChosenDatas = new ArrayList<>();
    private SinglePageMultiChosenGVAdapter mSinglePageMultiChosenGVAdapter;

    private void singlePageMultiChosen() {
        mBinding.btSinglePageMultiChosenOk.setOnClickListener(v -> {
            String result = "";
            for (ElemeSinglePageMultiChosenPersonInfo info : mSinglePageMultiChosenDatas) {
                if (info.isChosen()) {
                    result += "\r\n" + info.getText();
                }
            }
            TToast.get(mActivity).showLongImmediately("当前选中：" + result);
        });

        for (int i = 0; i < 9; i++) {
            ElemeSinglePageMultiChosenPersonInfo info = new ElemeSinglePageMultiChosenPersonInfo("person-" + i, i, false);
            mSinglePageMultiChosenDatas.add(info);
        }

        mBinding.gvSinglePageMultiChosen.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mSinglePageMultiChosenGVAdapter = new SinglePageMultiChosenGVAdapter(mActivity, mSinglePageMultiChosenDatas);
        mBinding.gvSinglePageMultiChosen.setAdapter(mSinglePageMultiChosenGVAdapter);
        mBinding.gvSinglePageMultiChosen.setOnItemClickListener((adapterView, view, position, l) -> {
            mSinglePageMultiChosenDatas.get(position).setChosen(!mSinglePageMultiChosenDatas.get(position).isChosen());
            mSinglePageMultiChosenGVAdapter.notifyDataSetChanged();

            setSelectedPerson();
        });
    }

    /* 全选 */
    private int mSumAges = 0;
    private boolean isSetSelectedPersonValid = false;

    private void setSelectedPerson() {
        /*  */
        mBinding.btSelectAll.setOnClickListener(v -> {
            if (!isSetSelectedPersonValid) {    // 初始状态未单个选中
                for (ElemeSinglePageMultiChosenPersonInfo info : mSinglePageMultiChosenDatas) {
                    info.setChosen(isSelectAll);
                }
                isSelectAll = !isSelectAll;
            } else {
                isSelectAll = !isSelectAll;
                for (ElemeSinglePageMultiChosenPersonInfo info : mSinglePageMultiChosenDatas) {
                    info.setChosen(isSelectAll);
                }
            }
            mSinglePageMultiChosenGVAdapter.notifyDataSetChanged();
            setSelectedPerson();
        });

        isSetSelectedPersonValid = true;
        isSelectAll = true;
        mSumAges = 0;
        for (ElemeSinglePageMultiChosenPersonInfo info : mSinglePageMultiChosenDatas) {
            if (info.isChosen()) {
                mSumAges += info.getAge();
            } else {
                isSelectAll = false;
            }
        }
        mBinding.btSelectAll.setTextColor(isSelectAll ? Color.RED : Color.BLACK);
        mBinding.tvSumAges.setText("" + mSumAges);
    }

    private boolean isSelectAll = true;


    /** 多页条目-多选 */


}

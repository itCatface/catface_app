package cc.catface.module_apis.brvah.view;

import android.view.View;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.view.recyclerview.banner.layoutmanager.BannerLayoutManager;
import cc.catface.base.utils.android.view.recyclerview.banner.layoutmanager.OrientationHelper;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.MultiQuickAdapter;
import cc.catface.module_apis.brvah.domain.MultipleItem;
import cc.catface.module_apis.brvah.engine.DataServer;
import cc.catface.module_apis.databinding.BrvahFmMultiBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_MultiFm extends NormalFragment<BrvahFmMultiBinding> implements View.OnClickListener {
    @Override public int layoutId() {
        return R.layout.brvah_fm_multi;
    }

    private List<MultipleItem> mDatas;
    private MultiQuickAdapter mMultiQuickAdapter;

    @Override protected void initAction() {
        mBinding.btGrid.setOnClickListener(this);
        mBinding.btLine.setOnClickListener(this);
        mBinding.btStag.setOnClickListener(this);
        mBinding.btBanner.setOnClickListener(this);
    }

    @Override public void onClick(View view) {
        String currentMode = "当前：";
        if (R.id.bt_grid == view.getId()) {
            currentMode += "grid";
            setGridLayoutManager();
        } else if (R.id.bt_line == view.getId()) {
            currentMode += "line";
            mBinding.rvList.setLayoutManager(mLinearLayoutManager);
        } else if (R.id.bt_stag == view.getId()) {
            currentMode += "stag";
            mBinding.rvList.setLayoutManager(mStaggeredGridLayoutManager);
        } else if (R.id.bt_banner == view.getId()) {
            currentMode += "banner";
            mBinding.rvList.setLayoutManager(mBannerLayoutManager);
        }
        mBinding.tvManager.setText(currentMode);
    }

    @Override public void createView() {
        initLayoutManager();

        mDatas = DataServer.getMultipleItemData();
        mMultiQuickAdapter = new MultiQuickAdapter(mDatas);
        setGridLayoutManager();
        mBinding.rvList.setAdapter(mMultiQuickAdapter);
    }


    private RecyclerView.LayoutManager mGridLayoutManager, mLinearLayoutManager, mStaggeredGridLayoutManager, mBannerLayoutManager;

    private void initLayoutManager() {
        mGridLayoutManager = new GridLayoutManager(mActivity, 4);
        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mBannerLayoutManager = new BannerLayoutManager(OrientationHelper.HORIZONTAL, 3);
    }


    private void setGridLayoutManager() {
        mBinding.rvList.setLayoutManager(mGridLayoutManager);
        mMultiQuickAdapter.setSpanSizeLookup((gridLayoutManager, position) -> mDatas.get(position).getSpanSize());
    }
}

package cc.catface.module_apis.brvah.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cc.catface.base.core_framework.base_normal.NormalBaseFragmentID;
import cc.catface.base.utils.android.view.recyclerview.banner.layoutmanager.BannerLayoutManager;
import cc.catface.base.utils.android.view.recyclerview.banner.layoutmanager.OrientationHelper;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.MultiQuickAdapter;
import cc.catface.module_apis.brvah.domain.MultipleItem;
import cc.catface.module_apis.brvah.engine.DataServer;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_MultiFm extends NormalBaseFragmentID implements View.OnClickListener {
    @Override public int layoutId() {
        return R.layout.brvah_fm_multi;
    }

    private TextView tv_manager;
    private RecyclerView rv_list;
    private List<MultipleItem> mDatas;
    private MultiQuickAdapter mMultiQuickAdapter;

    @Override public void ids(View v) {
        tv_manager = (TextView) v.findViewById(R.id.tv_manager);
        rv_list = (RecyclerView) v.findViewById(R.id.rv_list);
        v.findViewById(R.id.bt_grid).setOnClickListener(this);
        v.findViewById(R.id.bt_line).setOnClickListener(this);
        v.findViewById(R.id.bt_stag).setOnClickListener(this);
        v.findViewById(R.id.bt_banner).setOnClickListener(this);
    }

    @Override public void onClick(View view) {
        String currentMode = "当前：";
        if (R.id.bt_grid == view.getId()) {
            currentMode += "grid";
            setGridLayoutManager();
        } else if (R.id.bt_line == view.getId()) {
            currentMode += "line";
            rv_list.setLayoutManager(mLinearLayoutManager);
        } else if (R.id.bt_stag == view.getId()) {
            currentMode += "stag";
            rv_list.setLayoutManager(mStaggeredGridLayoutManager);
        } else if (R.id.bt_banner == view.getId()) {
            currentMode += "banner";
            rv_list.setLayoutManager(mBannerLayoutManager);
        }
        tv_manager.setText(currentMode);
    }

    @Override public void createView() {
        initLayoutManager();

        mDatas = DataServer.getMultipleItemData();
        mMultiQuickAdapter = new MultiQuickAdapter(mDatas);
        setGridLayoutManager();
        rv_list.setAdapter(mMultiQuickAdapter);
    }


    private RecyclerView.LayoutManager mGridLayoutManager, mLinearLayoutManager, mStaggeredGridLayoutManager, mBannerLayoutManager;

    private void initLayoutManager() {
        mGridLayoutManager = new GridLayoutManager(mActivity, 4);
        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mBannerLayoutManager = new BannerLayoutManager(OrientationHelper.HORIZONTAL, 3);
    }


    private void setGridLayoutManager() {
        rv_list.setLayoutManager(mGridLayoutManager);
        mMultiQuickAdapter.setSpanSizeLookup((gridLayoutManager, position) -> mDatas.get(position).getSpanSize());
    }
}

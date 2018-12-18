package cc.catface.module_apis.brvah.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.core_framework.base_normal.NormalBaseFragmentID;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.ItemClickAdapter;
import cc.catface.module_apis.brvah.domain.ClickEntity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_ItemClickFm extends NormalBaseFragmentID {
    @Override public int layoutId() {
        return R.layout.brvah_fm_item_click;
    }

    private RecyclerView rv_list;
    private ItemClickAdapter mAdapter;

    @Override public void ids(View v) {
        rv_list = (RecyclerView) v.findViewById(R.id.rv_list);
    }

    @Override public void createView() {
        rv_list.setLayoutManager(new LinearLayoutManager(mActivity));
        initAdapter();
        initAdapterEvent();
    }


    private void initAdapter() {
        List<ClickEntity> data = new ArrayList<>();
        data.add(new ClickEntity(ClickEntity.CLICK_ITEM_VIEW));
        data.add(new ClickEntity(ClickEntity.CLICK_ITEM_CHILD_VIEW));
        data.add(new ClickEntity(ClickEntity.LONG_CLICK_ITEM_VIEW));
        data.add(new ClickEntity(ClickEntity.LONG_CLICK_ITEM_CHILD_VIEW));
        data.add(new ClickEntity(ClickEntity.NEST_CLICK_ITEM_CHILD_VIEW));
        mAdapter = new ItemClickAdapter(data);
        mAdapter.openLoadAnimation();
        rv_list.setAdapter(mAdapter);
    }


    private void initAdapterEvent() {
        mAdapter.setOnItemClickListener((adapter, view, position) -> Toast.makeText(mActivity, "item clicked..." + position, Toast.LENGTH_SHORT).show());
        mAdapter.setOnItemLongClickListener((adapter, view, position) -> {
            Toast.makeText(mActivity, "item long clicked..." + position, Toast.LENGTH_SHORT).show();
            return true;
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> Toast.makeText(mActivity, "item child clicked..." + position, Toast.LENGTH_SHORT).show());
        mAdapter.setOnItemChildLongClickListener((adapter, view, position) -> {
            Toast.makeText(mActivity, "item child long clicked..." + position, Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}

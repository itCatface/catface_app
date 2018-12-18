package cc.catface.module_apis.brvah.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cc.catface.base.core_framework.base_normal.NormalBaseFragmentID;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.SectionAdapter;
import cc.catface.module_apis.brvah.decoration.GridSectionAverageGapItemDecoration;
import cc.catface.module_apis.brvah.domain.MySection;
import cc.catface.module_apis.brvah.engine.DataServer;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_SectionFm extends NormalBaseFragmentID {
    @Override public int layoutId() {
        return R.layout.brvah_fm_section;
    }

    private RecyclerView rv_list;
    private List<MySection> mDatas;
    private SectionAdapter mAdapter;

    @Override public void ids(View v) {
        rv_list = (RecyclerView) v.findViewById(R.id.rv_list);
    }

    @Override public void createView() {
        rv_list.setLayoutManager(new GridLayoutManager(mActivity, 2));
        rv_list.addItemDecoration(new GridSectionAverageGapItemDecoration(50, 20, 20, 20));
        mDatas = DataServer.getSampleData();

        initAdapter();
    }


    private void initAdapter() {
        mAdapter = new SectionAdapter(mDatas);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            MySection section = mDatas.get(position);
            if (section.isHeader) Toast.makeText(mActivity, section.header, Toast.LENGTH_SHORT).show();
            else Toast.makeText(mActivity, section.t.getName(), Toast.LENGTH_SHORT).show();
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> Toast.makeText(mActivity, "onItemChildClick..." + position, Toast.LENGTH_SHORT).show());
        rv_list.setAdapter(mAdapter);
    }
}

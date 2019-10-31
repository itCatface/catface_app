package cc.catface.module_apis.brvah.view;

import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.SectionAdapter;
import cc.catface.module_apis.brvah.decoration.GridSectionAverageGapItemDecoration;
import cc.catface.module_apis.brvah.domain.MySection;
import cc.catface.module_apis.brvah.engine.DataServer;
import cc.catface.module_apis.databinding.BrvahFmSectionBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_SectionFm extends LightFm<LightPresenter, BrvahFmSectionBinding> {

    @Override public int layoutId() {
        return R.layout.brvah_fm_section;
    }

    private List<MySection> mDatas;
    private SectionAdapter mAdapter;

    @Override protected void initView() {
        mBinding.rvList.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mBinding.rvList.addItemDecoration(new GridSectionAverageGapItemDecoration(50, 20, 20, 20));
        mDatas = DataServer.getSampleData();

        initAdapter();
    }


    private void initAdapter() {
        mAdapter = new SectionAdapter(mDatas);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            MySection section = mDatas.get(position);
            if (section.isHeader)
                Toast.makeText(mActivity, section.header, Toast.LENGTH_SHORT).show();
            else Toast.makeText(mActivity, section.t.getName(), Toast.LENGTH_SHORT).show();
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> Toast.makeText(mActivity, "onItemChildClick..." + position, Toast.LENGTH_SHORT).show());
        mBinding.rvList.setAdapter(mAdapter);
    }
}

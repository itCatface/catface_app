package cc.catface.module_apis.brvah.view;

import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.SectionMultiAdapter;
import cc.catface.module_apis.brvah.domain.SectionMultipleItem;
import cc.catface.module_apis.brvah.engine.DataServer;
import cc.catface.module_apis.databinding.BrvahFmSectionMultiBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_SectionMultiFm extends LightFm<LightPresenter, BrvahFmSectionMultiBinding> {

    @Override public int layoutId() {
        return R.layout.brvah_fm_section_multi;
    }

    private List<SectionMultipleItem> mDatas;
    private SectionMultiAdapter mAdapter;

    @Override protected void initView() {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mDatas = DataServer.getSectionMultiData();
        initAdapter();
    }


    private void initAdapter() {
        mAdapter = new SectionMultiAdapter(R.layout.brvah_item_def_section_head, mDatas);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            SectionMultipleItem item = (SectionMultipleItem) adapter.getData().get(position);
            if (R.id.card_view == view.getId()) {
                if (item.getVideo() != null) {
                    Toast.makeText(mActivity, item.getVideo().getName(), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(mActivity, "OnItemChildClickListener " + position, Toast.LENGTH_LONG).show();

            }
        });
        mBinding.rvList.setAdapter(mAdapter);
    }
}

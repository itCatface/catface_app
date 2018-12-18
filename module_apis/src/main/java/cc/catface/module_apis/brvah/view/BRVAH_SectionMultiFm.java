package cc.catface.module_apis.brvah.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cc.catface.base.core_framework.base_normal.NormalBaseFragmentID;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.SectionMultiAdapter;
import cc.catface.module_apis.brvah.domain.SectionMultipleItem;
import cc.catface.module_apis.brvah.engine.DataServer;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_SectionMultiFm extends NormalBaseFragmentID {
    @Override public int layoutId() {
        return R.layout.brvah_fm_section_multi;
    }

    private RecyclerView rv_list;
    private List<SectionMultipleItem> mDatas;
    private SectionMultiAdapter mAdapter;

    @Override public void ids(View v) {
        rv_list = (RecyclerView) v.findViewById(R.id.rv_list);
    }

    @Override public void createView() {
        rv_list.setLayoutManager(new LinearLayoutManager(mActivity));
        mDatas = DataServer.getSectionMultiData();
        initAdapter();
    }


    private void initAdapter() {
        mAdapter = new SectionMultiAdapter(R.layout.brvah_item_def_section_head, mDatas);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            SectionMultipleItem item = (SectionMultipleItem) adapter.getData().get(position);
            if (R.id.card_view==view.getId()) {
                if (item.getVideo() != null) {
                    Toast.makeText(mActivity, item.getVideo().getName(), Toast.LENGTH_LONG).show();
                }
            }else {
                    Toast.makeText(mActivity, "OnItemChildClickListener " + position, Toast.LENGTH_LONG).show();

            }
        });
        rv_list.setAdapter(mAdapter);
    }
}

package cc.catface.module_apis.brvah.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import cc.catface.base.core_framework.base_normal.NormalBaseFragmentID;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.MultiRVAdapter;
import cc.catface.module_apis.brvah.domain.MultipleItem;
import cc.catface.module_apis.brvah.domain.NormalMultipleEntity;
import cc.catface.module_apis.brvah.engine.DataServer;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_MultiRVAdapterFm extends NormalBaseFragmentID {
    @Override public int layoutId() {
        return R.layout.brvah_fm_multi_rv_adapter;
    }

    private RecyclerView rv_list;
    private List<NormalMultipleEntity> mDatas;
    private MultiRVAdapter mAdapter;

    @Override public void ids(View v) {
        rv_list = (RecyclerView) v.findViewById(R.id.rv_list);
    }

    @Override public void createView() {
        rv_list.setLayoutManager(new GridLayoutManager(mActivity, 4));
        mDatas = DataServer.getNormalMultipleEntities();
        mAdapter = new MultiRVAdapter(mDatas);
        mAdapter.setSpanSizeLookup((gridLayoutManager, position) -> {
            int type = mDatas.get(position).type;
            if (type == NormalMultipleEntity.SINGLE_TEXT) {
                return MultipleItem.TEXT_SPAN_SIZE;
            } else if (type == NormalMultipleEntity.SINGLE_IMG) {
                return MultipleItem.IMG_SPAN_SIZE;
            } else {
                return MultipleItem.IMG_TEXT_SPAN_SIZE;
            }
        });
        rv_list.setAdapter(mAdapter);
    }
}

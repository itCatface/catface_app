package cc.catface.module_apis.brvah.view;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.MultiRVAdapter;
import cc.catface.module_apis.brvah.domain.MultipleItem;
import cc.catface.module_apis.brvah.domain.NormalMultipleEntity;
import cc.catface.module_apis.brvah.engine.DataServer;
import cc.catface.module_apis.databinding.BrvahFmMultiRvAdapterBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_MultiRVAdapterFm extends LightFm<LightPresenter, BrvahFmMultiRvAdapterBinding> {

    @Override public int layoutId() {
        return R.layout.brvah_fm_multi_rv_adapter;
    }

    private List<NormalMultipleEntity> mDatas;
    private MultiRVAdapter mAdapter;

    @Override protected void initView() {
        mBinding.rvList.setLayoutManager(new GridLayoutManager(mActivity, 4));
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
        mBinding.rvList.setAdapter(mAdapter);
    }
}

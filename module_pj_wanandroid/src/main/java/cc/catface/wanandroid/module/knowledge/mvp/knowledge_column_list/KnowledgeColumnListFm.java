package cc.catface.wanandroid.module.knowledge.mvp.knowledge_column_list;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.base.utils.android.view.recyclerview.ItemClickSupport;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentKnowledgeColumnListBinding;
import cc.catface.wanandroid.engine.adapter.KnowledgeColumnListAdapter;
import cc.catface.wanandroid.engine.domain.KnowledgeColumnListData;
import cc.catface.wanandroid.module.web.WebActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(KnowledgeColumnListPresenterImpl.class)
public class KnowledgeColumnListFm extends MvpFragment<KnowledgeColumnListVP.KnowledgeColumnListView, KnowledgeColumnListPresenterImpl, WanandroidFragmentKnowledgeColumnListBinding> implements KnowledgeColumnListVP.KnowledgeColumnListView {

    private int mCid;

    public static KnowledgeColumnListFm getInstance(int cid) {
        KnowledgeColumnListFm fm = new KnowledgeColumnListFm();
        Bundle bundle = new Bundle();
        bundle.putInt("cid", cid);
        fm.setArguments(bundle);
        return fm;
    }

    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_knowledge_column_list;
    }

    @Override protected void initData() {
        mCid = getArguments().getInt("cid");
        request(mCid);
    }

    @Override protected void initAction() {
        mBinding.srlKnowledgeColumnList.setOnRefreshListener(() -> request(mCid));
        ItemClickSupport.addTo(mBinding.rvKnowledgeColumnList).setOnItemClickListener((recyclerView, position, v) -> {
            String url = mData.getData().getDatas().get(position).getLink();
            WebActivity.jump(mActivity, url);
        }).setOnItemLongClickListener((recyclerView, position, v) -> {return false;});
    }

    private KnowledgeColumnListAdapter mAdapter;

    @Override protected void viewCreated() {
        mBinding.srlKnowledgeColumnList.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY);

        mBinding.rvKnowledgeColumnList.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvKnowledgeColumnList.setHasFixedSize(true);
        mAdapter = new KnowledgeColumnListAdapter();
        mBinding.rvKnowledgeColumnList.setAdapter(mAdapter);
    }


    /** private's support */
    private void request(int cid) {
        mPresenter.request(0, cid);
    }

    /** View's */
    private KnowledgeColumnListData mData;

    @Override public void requestSuccess(KnowledgeColumnListData data) {
        mBinding.srlKnowledgeColumnList.setRefreshing(false);
        mData = data;
        mAdapter.setData(mData);
        mAdapter.notifyDataSetChanged();
    }

    @Override public void requestFailure(String error) {
        mBinding.srlKnowledgeColumnList.setRefreshing(false);
    }
}

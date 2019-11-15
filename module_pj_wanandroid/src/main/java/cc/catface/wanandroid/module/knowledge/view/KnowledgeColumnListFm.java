package cc.catface.wanandroid.module.knowledge.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.utils.android.view.recyclerview.ItemClickSupport;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentKnowledgeColumnListBinding;
import cc.catface.wanandroid.engine.adapter.KnowledgeColumnListAdapter;
import cc.catface.wanandroid.engine.domain.KnowledgeColumnListData;
import cc.catface.wanandroid.module.knowledge.vp.KnowledgeColumnListPresenterImpl;
import cc.catface.wanandroid.module.knowledge.vp.KnowledgeColumnListVP;
import cc.catface.wanandroid.module.web.WebActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class KnowledgeColumnListFm extends LightFm<KnowledgeColumnListPresenterImpl, WanandroidFragmentKnowledgeColumnListBinding> implements KnowledgeColumnListVP.KnowledgeColumnListView {

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

    @Override protected void initPresenter() {
        mPresenter = new KnowledgeColumnListPresenterImpl(this, mActivity);
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

    @Override protected void created() {
        mBinding.srlKnowledgeColumnList.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY);

        mBinding.rvKnowledgeColumnList.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvKnowledgeColumnList.setHasFixedSize(true);
    }


    /** private's support */
    private void request(int cid) {
        mPresenter.request(0, cid);
    }

    /** View's */
    private KnowledgeColumnListData mData;

    @Override public void requestSuccess(KnowledgeColumnListData data) {
        mData = data;
        mBinding.srlKnowledgeColumnList.setRefreshing(false);
        mBinding.rvKnowledgeColumnList.setAdapter(new KnowledgeColumnListAdapter(mData.getData().getDatas()));
    }

    @Override public void requestFailure(String error) {
        mBinding.srlKnowledgeColumnList.setRefreshing(false);
    }
}

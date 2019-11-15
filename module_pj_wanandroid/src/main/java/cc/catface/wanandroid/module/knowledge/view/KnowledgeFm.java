package cc.catface.wanandroid.module.knowledge.view;

import android.graphics.Color;

import androidx.recyclerview.widget.LinearLayoutManager;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.utils.android.view.recyclerview.ItemClickSupport;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentKnowledgeBinding;
import cc.catface.wanandroid.engine.adapter.KnowledgeAdapter;
import cc.catface.wanandroid.engine.domain.KnowledgeData;
import cc.catface.wanandroid.module.knowledge.vp.KnowledgePresenterImpl;
import cc.catface.wanandroid.module.knowledge.vp.KnowledgeVP;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class KnowledgeFm extends LightFm<KnowledgePresenterImpl, WanandroidFragmentKnowledgeBinding> implements KnowledgeVP.KnowledgeView {

    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_knowledge;
    }

    @Override protected void initPresenter() {
        mPresenter = new KnowledgePresenterImpl(this, mActivity);
    }

    @Override protected void initData() {
        request();
    }

    @Override protected void initAction() {
        mBinding.srlKnowledge.setOnRefreshListener(this::request);
        ItemClickSupport.addTo(mBinding.rvKnowledge).setOnItemClickListener((recyclerView, position, v) -> KnowledgeColumnActivity.jump(mActivity, position, mData));
    }

    @Override protected void created() {
        mBinding.srlKnowledge.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY);
        mBinding.rvKnowledge.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvKnowledge.setHasFixedSize(true);
    }


    /** privates's support */
    private void request() {
        mPresenter.request();
    }

    /** View's */
    private KnowledgeData mData;

    @Override public void requestSuccess(KnowledgeData data) {
        mData = data;
        mBinding.srlKnowledge.setRefreshing(false);
        mBinding.rvKnowledge.setAdapter(new KnowledgeAdapter(mData.getData()));
    }

    @Override public void requestFailure() {
        mBinding.srlKnowledge.setRefreshing(false);
    }
}

package cc.catface.wanandroid.module.knowledge.mvp.knowledge;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.base.utils.android.view.recyclerview.ItemClickSupport;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentKnowledgeBinding;
import cc.catface.wanandroid.engine.adapter.KnowledgeAdapter;
import cc.catface.wanandroid.engine.domain.KnowledgeData;
import cc.catface.wanandroid.module.knowledge.mvp.knowledge_column.KnowledgeColumnActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(KnowledgePresenterImpl.class)
public class KnowledgeFm extends MvpFragment<KnowledgeVP.KnowledgeView, KnowledgePresenterImpl, WanandroidFragmentKnowledgeBinding> implements KnowledgeVP.KnowledgeView {
    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_knowledge;
    }

    @Override protected void initData() {
        request();
    }

    @Override protected void initAction() {
        mBinding.srlKnowledge.setOnRefreshListener(this::request);
        ItemClickSupport.addTo(mBinding.rvKnowledge).setOnItemClickListener((recyclerView, position, v) -> {
            Intent intent = new Intent(mActivity, KnowledgeColumnActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("index", position);
            bundle.putSerializable("aa", mData);
            intent.putExtra("bundle", bundle);
            mActivity.startActivity(intent);
        });
    }

    private KnowledgeAdapter mAdapter;

    @Override public void viewCreated() {
        mBinding.srlKnowledge.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY);

        mBinding.rvKnowledge.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvKnowledge.setHasFixedSize(true);
        mAdapter = new KnowledgeAdapter();
        mBinding.rvKnowledge.setAdapter(mAdapter);
    }


    /** privates's support */
    private void request() {
        mPresenter.request();
    }

    /** View's */
    private KnowledgeData mData;

    @Override public void requestSuccess(KnowledgeData data) {
        mBinding.srlKnowledge.setRefreshing(false);
        mData = data;
        mAdapter.setData(mData);
        mAdapter.notifyDataSetChanged();
    }

    @Override public void requestFailure() {
        mBinding.srlKnowledge.setRefreshing(false);
    }
}

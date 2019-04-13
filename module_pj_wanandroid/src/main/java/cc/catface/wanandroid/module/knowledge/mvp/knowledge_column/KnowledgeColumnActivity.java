package cc.catface.wanandroid.module.knowledge.mvp.knowledge_column;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpActivity;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.wanandroid.R;
import cc.catface.wanandroid.databinding.WanandroidFragmentKnowledgeColumnBinding;
import cc.catface.wanandroid.engine.adapter.KnowledgeColumnAdapter;
import cc.catface.wanandroid.engine.domain.KnowledgeData;
import cc.catface.wanandroid.module.knowledge.mvp.knowledge_column_list.KnowledgeColumnListFm;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(KnowledgeColumnPresenterImpl.class)
public class KnowledgeColumnActivity extends MvpActivity<KnowledgeColumnVP.KnowledgeColumnView, KnowledgeColumnPresenterImpl, WanandroidFragmentKnowledgeColumnBinding> implements KnowledgeColumnVP.KnowledgeColumnView {


    private List<String> mColumnTitles = new ArrayList<>();
    private List<Fragment> mFms = new ArrayList<>();
    public KnowledgeColumnActivity() {

    }

    @Override public int layoutId() {
        return R.layout.wanandroid_fragment_knowledge_column;
    }

    @Override protected void initData() {
        KnowledgeData data = (KnowledgeData) getIntent().getBundleExtra("bundle").getSerializable("aa");
        int index =  getIntent().getBundleExtra("bundle").getInt("index");
        Log.d("root", "column: " + data.getData().get(index).getName());

        List<KnowledgeData.Data.Children> children = data.getData().get(index).getChildren();
        for (KnowledgeData.Data.Children child : children) {
            mColumnTitles.add(child.getName());

            mFms.add(KnowledgeColumnListFm.getInstance(child.getId()));
        }

        mBinding.vpKnowledgeColumn.setOffscreenPageLimit(mColumnTitles.size());
        mBinding.vpKnowledgeColumn.setAdapter(new KnowledgeColumnAdapter(getSupportFragmentManager(), mColumnTitles, mFms));
        mBinding.tlKnowledgeColumn.setupWithViewPager(mBinding.vpKnowledgeColumn);

    }

    @Override public void create() {

    }


}

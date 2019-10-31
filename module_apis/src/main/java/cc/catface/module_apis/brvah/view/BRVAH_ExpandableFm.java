package cc.catface.module_apis.brvah.view;

import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.Random;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.ExpandableAdapter;
import cc.catface.module_apis.brvah.domain.Level0Item;
import cc.catface.module_apis.brvah.domain.Level1Item;
import cc.catface.module_apis.brvah.domain.Person;
import cc.catface.module_apis.databinding.BrvahFmExpandableBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_ExpandableFm extends LightFm<LightPresenter, BrvahFmExpandableBinding> {

    @Override public int layoutId() {
        return R.layout.brvah_fm_expandable;
    }

    ArrayList<MultiItemEntity> mDatas;
    ExpandableAdapter mAdapter;

    @Override protected void initView() {
        mDatas = generateData();
        mAdapter = new ExpandableAdapter(mDatas);

        final GridLayoutManager manager = new GridLayoutManager(mActivity, 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                return mAdapter.getItemViewType(position) == ExpandableAdapter.TYPE_PERSON ? 1 : manager.getSpanCount();
            }
        });

        mBinding.rvList.setAdapter(mAdapter);
        // important! setLayoutManager should be called after setAdapter
        mBinding.rvList.setLayoutManager(manager);
        mAdapter.expandAll();
    }

    private ArrayList<MultiItemEntity> generateData() {
        int lv0Count = 9;
        int lv1Count = 3;
        int personCount = 5;

        String[] nameList = {"Bob", "Andy", "Lily", "Brown", "Bruce"};
        Random random = new Random();

        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            Level0Item lv0 = new Level0Item("This is " + i + "th item in Level 0", "subtitle of " + i);
            for (int j = 0; j < lv1Count; j++) {
                Level1Item lv1 = new Level1Item("Level 1 item: " + j, "(no animation)");
                for (int k = 0; k < personCount; k++) {
                    lv1.addSubItem(new Person(nameList[k], random.nextInt(40)));
                }
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        res.add(new Level0Item("This is " + lv0Count + "th item in Level 0", "subtitle of " + lv0Count));
        return res;
    }
}

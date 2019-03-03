package cc.catface.module_apis.sticky_list_headers;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cc.catface.app_base.Const;
import cc.catface.app_base.TestDataSource;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisActivityDemoStickyListBinding;
import cc.catface.module_apis.sticky_list_headers.adapter.StickyListHeaderAdapter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.apis_sticky_list)
public class DemoStickyListActivity extends NormalActivity<ApisActivityDemoStickyListBinding> {

    @Override public int layoutId() {
        return R.layout.apis_activity_demo_sticky_list;
    }

    @Override public void create() {
        title();

        /* 类似通讯录头部的粘性控件 */
        testStickyListHeaders();
    }

    private void title() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.ARouter.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
    }

    private void testStickyListHeaders() {
        initStickyListHeadersData();

        initAdapter();
    }


    List<String> mDatas = new ArrayList<>();

    private void initStickyListHeadersData() {
        mDatas.addAll(TestDataSource.get_lol_en_names());
        mDatas.addAll(TestDataSource.get_lol_zh_names());
        mDatas.add("#1");
        mDatas.add("%2");
        mDatas.add("3^");
        mDatas.add("4&");
        Collections.sort(mDatas);
    }

    private StickyListHeaderAdapter mAdapter;

    private void initAdapter() {
        mAdapter = new StickyListHeaderAdapter(this, mDatas);
        mAdapter.setOnMyItemClickListener(new StickyListHeaderAdapter.OnMyItemClickListener() {
            @Override public void onMyItemClick(int position, Object object) {
                TToast.get(DemoStickyListActivity.this).showBShortView(object.toString(), TToast.B_INFO);
            }

            @Override public void onMyItemLongClick(int position, Object object) {
                TToast.get(DemoStickyListActivity.this).showBShortView(object.toString() + "~舒服", TToast.B_INFO);
            }
        });
        mBinding.slhlv.setAdapter(mAdapter);
    }
}

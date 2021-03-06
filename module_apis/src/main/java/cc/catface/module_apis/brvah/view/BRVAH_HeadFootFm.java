package cc.catface.module_apis.brvah.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.HeadFootAdapter;
import cc.catface.module_apis.brvah.engine.DataServer;
import cc.catface.module_apis.databinding.BrvahFmHeadFootBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_HeadFootFm extends LightFm<LightPresenter, BrvahFmHeadFootBinding> {

    @Override public int layoutId() {
        return R.layout.brvah_fm_head_foot;
    }

    private HeadFootAdapter mAdapter;

    @Override protected void initView() {
        initAdapter();

        View headerView = getHeaderView(0, v -> mAdapter.addHeaderView(getHeaderView(1, getRemoveHeaderListener()), 0));
        mAdapter.addHeaderView(headerView);

        View footerView = getFooterView(0, v -> mAdapter.addFooterView(getFooterView(1, getRemoveFooterListener()), 0));
        mAdapter.addFooterView(footerView, 0);

        mBinding.rvList.setAdapter(mAdapter);
    }

    private View getHeaderView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.brvah_item_head_view, (ViewGroup) mBinding.rvList.getParent(), false);
        if (type == 1) {
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            imageView.setImageResource(R.mipmap.brvah_rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }

    private View getFooterView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.brvah_item_footer_view, (ViewGroup) mBinding.rvList.getParent(), false);
        if (type == 1) {
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            imageView.setImageResource(R.mipmap.brvah_rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }

    private View.OnClickListener getRemoveHeaderListener() {
        return v -> mAdapter.removeHeaderView(v);
    }


    private View.OnClickListener getRemoveFooterListener() {
        return v -> mAdapter.removeFooterView(v);
    }

    private void initAdapter() {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new HeadFootAdapter(3);
        mAdapter.openLoadAnimation();
        mBinding.rvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            adapter.setNewData(DataServer.getSampleData(3));
            Toast.makeText(mActivity, "" + position, Toast.LENGTH_SHORT).show();
        });
    }
}

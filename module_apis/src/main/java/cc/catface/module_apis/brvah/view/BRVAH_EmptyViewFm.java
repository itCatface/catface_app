package cc.catface.module_apis.brvah.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.Timer.TTimer;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.EmptyAdapter;
import cc.catface.module_apis.brvah.domain.Status;
import cc.catface.module_apis.brvah.engine.DataServer;
import cc.catface.module_apis.databinding.BrvahFmEmptyViewBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_EmptyViewFm extends LightFm<LightPresenter, BrvahFmEmptyViewBinding> implements View.OnClickListener {

    @Override public int layoutId() {
        return R.layout.brvah_fm_empty_view;
    }

    private boolean isNoData = true, isError = true;
    private View mViewNoData, mViewError;
    private EmptyAdapter mAdapter;

    @Override public void onClick(View view) {
        if (R.id.btn_reset == view.getId()) {
            isNoData = true;
            isError = true;
            mAdapter.setNewData(null);
            onRefresh();
        }
    }

    @Override protected void initView() {
        mBinding.btnReset.setOnClickListener(this);

        mBinding.rvList.setHasFixedSize(true);
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(mActivity));

        initHolderView();
        initAdapter();
        onRefresh();
    }

    private void initHolderView() {
        mViewNoData = getLayoutInflater().inflate(R.layout.brvah_view_empty, (ViewGroup) mBinding.rvList.getParent(), false);
        mViewNoData.setOnClickListener(view -> onRefresh());
        mViewError = getLayoutInflater().inflate(R.layout.brvah_item_error_view, (ViewGroup) mBinding.rvList.getParent(), false);
        mViewError.setOnClickListener(view -> onRefresh());
    }


    private void initAdapter() {
        mAdapter = new EmptyAdapter(0);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Status item = (Status) adapter.getItem(position);
            Toast.makeText(mActivity, item.getUserName(), Toast.LENGTH_SHORT).show();
        });
        mBinding.rvList.setAdapter(mAdapter);
    }


    private void onRefresh() {
        mAdapter.setEmptyView(R.layout.brvah_loading_view, (ViewGroup) mBinding.rvList.getParent());
        TTimer.timeFinished(700, () -> {
            if (isError) {
                mAdapter.setEmptyView(mViewError);
                isError = false;
            } else {
                if (isNoData) {
                    mAdapter.setEmptyView(mViewNoData);
                    isNoData = false;
                } else {
                    mAdapter.setNewData(DataServer.getSampleData(7));
                }
            }
        });
    }
}

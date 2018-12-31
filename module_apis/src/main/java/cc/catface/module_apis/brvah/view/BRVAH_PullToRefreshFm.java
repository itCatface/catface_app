package cc.catface.module_apis.brvah.view;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

import cc.catface.base.core_framework.base_normal.NormalBaseFragmentID;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.PullToRefreshAdapter;
import cc.catface.module_apis.brvah.domain.Status;
import cc.catface.module_apis.brvah.engine.CustomLoadMoreView;
import cc.catface.module_apis.brvah.engine.DataServer;


interface RequestCallBack {
    void success(List<Status> data);

    void fail(Exception e);
}

class Request extends Thread {
    private static final int PAGE_SIZE = 6;
    private int mPage;
    private RequestCallBack mCallBack;
    private Handler mHandler;

    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    Request(int page, RequestCallBack callBack) {
        mPage = page;
        mCallBack = callBack;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override public void run() {
        try {Thread.sleep(500);} catch (InterruptedException ignored) {}

        if (mPage == 2 && mFirstError) {
            mFirstError = false;
            mHandler.post(() -> mCallBack.fail(new RuntimeException("fail")));
        } else {
            int size = PAGE_SIZE;
            if (mPage == 1) {
                if (mFirstPageNoMore) {
                    size = 1;
                }
                mFirstPageNoMore = !mFirstPageNoMore;
                if (!mFirstError) {
                    mFirstError = true;
                }
            } else if (mPage == 4) {
                size = 1;
            }

            final int dataSize = size;
            mHandler.post(() -> mCallBack.success(DataServer.getSampleData(dataSize)));
        }
    }
}

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_PullToRefreshFm extends NormalBaseFragmentID {
    @Override public int layoutId() {
        return R.layout.brvah_fm_pull_to_refresh;
    }

    private SwipeRefreshLayout srl_brvah_refresh;
    private RecyclerView rv_list;

    @Override public void ids(View v) {
        srl_brvah_refresh = (SwipeRefreshLayout) v.findViewById(R.id.srl_brvah_refresh);
        rv_list = (RecyclerView) v.findViewById(R.id.rv_list);
    }

    @Override public void createView() {
        initView();
        initAdapter();
        addHeaderView();
        initEvent();
        refresh();
    }


    private static final int PAGE_SIZE = 6;

    private PullToRefreshAdapter mAdapter;

    private int mNextRequestPage = 1;


    private void initView() {
        srl_brvah_refresh.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_list.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    private void initAdapter() {
        mAdapter = new PullToRefreshAdapter();
        mAdapter.setOnLoadMoreListener(this::loadMore);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        rv_list.setAdapter(mAdapter);

        rv_list.addOnItemTouchListener(new OnItemClickListener() {
            @Override public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                TToast.get(mActivity).showBShortView("点击位置：" + position, TToast.B_INFO);
            }
        });
    }

    private void addHeaderView() {
        View headView = getLayoutInflater().inflate(R.layout.brvah_item_head_view, (ViewGroup) rv_list.getParent(), false);
        headView.findViewById(R.id.iv).setVisibility(View.GONE);
        ((TextView) headView.findViewById(R.id.tv)).setText("change default load view");
        headView.setOnClickListener(v -> {
            mAdapter.setNewData(null);
            mAdapter.setLoadMoreView(new CustomLoadMoreView());
            rv_list.setAdapter(mAdapter);
            TToast.get(mActivity).showBShortView("change complete...", TToast.B_SUCCESS);

            srl_brvah_refresh.setRefreshing(true);
            refresh();
        });
        mAdapter.addHeaderView(headView);
    }

    private void initEvent() {
        srl_brvah_refresh.setOnRefreshListener(this::refresh);
        srl_brvah_refresh.setRefreshing(true);
    }

    private void refresh() {
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false); // 防止下拉刷新的时候还可以上拉加载
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override public void success(List<Status> data) {
                setData(true, data);
                mAdapter.setEnableLoadMore(true);
                srl_brvah_refresh.setRefreshing(false);
            }

            @Override public void fail(Exception e) {
                TToast.get(mActivity).showBShortView("refresh-->" + getResources().getString(R.string.error_net_error_brvah), TToast.B_ERROR);
                mAdapter.setEnableLoadMore(true);
                srl_brvah_refresh.setRefreshing(false);
            }
        }).start();
    }

    private void loadMore() {
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override public void success(List<Status> data) {
                boolean isRefresh = mNextRequestPage == 1;
                setData(isRefresh, data);
            }

            @Override public void fail(Exception e) {
                mAdapter.loadMoreFail();
                TToast.get(mActivity).showBShortView("load more-->" + getResources().getString(R.string.error_net_error_brvah), TToast.B_ERROR);
            }
        }).start();
    }

    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            // 第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            TToast.get(mActivity).showBShortView("no more data!", TToast.B_WARNING);
        } else {
            mAdapter.loadMoreComplete();
        }
    }
}
package cc.catface.module_apis.showapi.mvp.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsFragmentID;
import cc.catface.base.utils.android.Timer.TTimer;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.base.utils.android.net.retrofit.RetrofitCallback;
import cc.catface.base.utils.android.net.retrofit.RetrofitT;
import cc.catface.module_apis.R;
import cc.catface.module_apis.showapi.adapter.YYJoke341_2Adapter;
import cc.catface.module_apis.showapi.domain.YYJoke341_2;
import cc.catface.module_apis.showapi.global.Const;
import cc.catface.module_apis.showapi.mvp.presenter.YYJoke341_2PresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(YYJoke341_2PresenterImp.class)
public class YYJoke341_2Fm extends AbsFragmentID<YYJoke341_2View, YYJoke341_2PresenterImp> implements YYJoke341_2View {


    @Override public int layoutId() {
        return R.layout.showapi_fm_yy_joke_341_2;
    }

    private SwipeRefreshLayout srl_joke_pic;
    private RecyclerView rv_joke_pic;
    private StaggeredGridLayoutManager mLayoutManager;
    private int mPage = 1;
    private List<YYJoke341_2.Showapi_res_body.Contentlist> mDatas = new ArrayList<>();
    private ProgressBar pb;

    @Override public void ids(View v) {
        srl_joke_pic = (SwipeRefreshLayout) v.findViewById(R.id.srl_joke_pic);
        rv_joke_pic = (RecyclerView) v.findViewById(R.id.rv_joke_pic);
        pb = (ProgressBar) v.findViewById(R.id.pb);
    }

    @Override public void viewCreated() {
        initView();
        initEvent();
        initData();
    }


    private void initView() {
        srl_joke_pic.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY);
        rv_joke_pic.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rv_joke_pic.setLayoutManager(mLayoutManager);
    }

    @SuppressLint("ClickableViewAccessibility") private void initEvent() {
        srl_joke_pic.setOnRefreshListener(() -> {
            srl_joke_pic.setRefreshing(true);
            mPage = 1;
            initData();
        });

//        rv_joke_pic.setOnTouchListener((view, event) -> {
//            if (View.VISIBLE != pb.getVisibility() && !rv_joke_pic.canScrollVertically(1)) { // 1加载-1刷新
//                pb.setVisibility(View.VISIBLE);
//                mPage++;
//                TTimer.timeFinished(1_500, () -> initData());
//            }
//            return false;
//        });
        rv_joke_pic.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mLayoutManager.invalidateSpanAssignments();

                if (rv_joke_pic.getScrollState() == recyclerView.SCROLL_STATE_IDLE) {
                    int[] outRange = getOutRange();
//                    int len = outRange[1] - outRange[0] + 1;
//
//                    int num = (int) (Math.random() * 10);
//                    for (int i = 0; i < len; i++) {
//                        mItems.set(outRange[0] + i, num + "");
//                    }

                    TLog.d("root", outRange[0] + "、、、"+outRange[1]);
                    if (outRange[1] >= mDatas.size()-1) {
                        pb.setVisibility(View.VISIBLE);
                        mPage++;
                        TTimer.timeFinished(1_500, () -> initData());
                    }


                }
            }

            @Override public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

//                if (mLayoutManager == null) {
//                    if (layoutManager instanceof LinearLayoutManager) {
//                        mLayoutManager = LayoutManagerType.LinearLayout;
//                    } else if (layoutManager instanceof GridLayoutManager) {
//                        mLayoutManager = LayoutManagerType.GridLayout;
//                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
//                        mLayoutManager = LayoutManagerType.StaggeredGridLayout;
//                    } else {
//                        throw new RuntimeException(
//                                "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
//                    }
//                }
//
//                switch (mLayoutManager) {
//                    case LinearLayout:
//                        lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
//                        break;
//                    case GridLayout:
//                        lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
//                        break;
//                    case StaggeredGridLayout:
//                        StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
//                        if (lastPositions == null) {
//                            lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
//                        }
//                        staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
//                        lastVisibleItemPosition = findMax(lastPositions);
//                        break;
//                    default:
//                        break;
//                }

            }
        });
    }


    private int[] getOutRange() {
        int[] range = new int[2];

        int[] first = new int[mLayoutManager.getSpanCount()];
        mLayoutManager.findFirstVisibleItemPositions(first);

        int[] last = new int[mLayoutManager.getSpanCount()];
        mLayoutManager.findLastVisibleItemPositions(last);

        range[0] = first[0];

        Arrays.sort(last);
        range[1] = last[last.length - 1];

        return range;
    }




    private Map<String, String> map = new HashMap<>();

    private void initData() {
        map.put("page", String.valueOf(mPage));
        RetrofitT.getInstance().post(Const.url_yy_joke_341_2, map, new RetrofitCallback() {
            @Override public void onSuccess(String result) {
                srl_joke_pic.setRefreshing(false);
                pb.setVisibility(View.GONE);
                YYJoke341_2 yyJoke341_2 = new Gson().fromJson(result, YYJoke341_2.class);
                List<YYJoke341_2.Showapi_res_body.Contentlist> data = yyJoke341_2.getShowapi_res_body().getContentlist();
                if (null == rv_joke_pic.getAdapter()) {
                    mDatas.addAll(data);
                    rv_joke_pic.setAdapter(new YYJoke341_2Adapter(mDatas));
                } else if (mPage > 1) {
                    mDatas.addAll(data);
                    rv_joke_pic.getAdapter().notifyItemInserted(mDatas.size());
                } else {
                    mDatas.clear();
                    mDatas.addAll(data);
                    rv_joke_pic.getAdapter().notifyDataSetChanged();
                }
            }

            @Override public void onError(String error) {
                srl_joke_pic.setRefreshing(false);
                pb.setVisibility(View.GONE);
            }
        });
    }
}

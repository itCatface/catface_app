package cc.catface.showapi.joke.mvp.view;

import android.graphics.Color;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.utils.android.Timer.TTimer;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.base.utils.android.net.retrofit.RetrofitCallback;
import cc.catface.base.utils.android.net.retrofit.RetrofitT;
import cc.catface.showapi.R;
import cc.catface.showapi.databinding.ShowapiFmYyJoke3412Binding;
import cc.catface.showapi.joke.adapter.YYJoke341_2Adapter;
import cc.catface.showapi.joke.domain.YYJoke341_2;
import cc.catface.showapi.joke.global.ShowapiConst;
import cc.catface.showapi.joke.mvp.vp.YYJoke341_2PresenterImp;
import cc.catface.showapi.joke.mvp.vp.YYJoke341_2VP;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class YYJoke341_2Fm extends LightFm<YYJoke341_2PresenterImp, ShowapiFmYyJoke3412Binding> implements YYJoke341_2VP.YYJoke341_2View {

    @Override public int layoutId() {
        return R.layout.showapi_fm_yy_joke_341_2;
    }

    private Map<String, String> map = new HashMap<>();

    @Override public void initData() {
        map.put("page", String.valueOf(mPage));
        RetrofitT.getInstance().post(ShowapiConst.url_yy_joke_341_2, map, new RetrofitCallback() {
            @Override public void onSuccess(String result) {
                mBinding.srlJokePic.setRefreshing(false);
                mBinding.pb.setVisibility(View.GONE);
                YYJoke341_2 yyJoke341_2 = new Gson().fromJson(result, YYJoke341_2.class);
                List<YYJoke341_2.Showapi_res_body.Contentlist> data = yyJoke341_2.getShowapi_res_body().getContentlist();
                if (null == mBinding.rvJokePic.getAdapter()) {
                    mDatas.addAll(data);
                    mBinding.rvJokePic.setAdapter(new YYJoke341_2Adapter(mDatas));
                } else if (mPage > 1) {
                    mDatas.addAll(data);
                    mBinding.rvJokePic.getAdapter().notifyItemInserted(mDatas.size());
                } else {
                    mDatas.clear();
                    mDatas.addAll(data);
                    mBinding.rvJokePic.getAdapter().notifyDataSetChanged();
                }
            }

            @Override public void onError(String error) {
                mBinding.srlJokePic.setRefreshing(false);
                mBinding.pb.setVisibility(View.GONE);
            }
        });
    }

    @Override protected void initAction() {
        mBinding.srlJokePic.setOnRefreshListener(() -> {
            mBinding.srlJokePic.setRefreshing(true);
            mPage = 1;
            initData();
        });

        //        rvJokePic.setOnTouchListener((view, event) -> {
        //            if (View.VISIBLE != pb.getVisibility() && !rvJokePic.canScrollVertically(1)) { // 1加载-1刷新
        //                pb.setVisibility(View.VISIBLE);
        //                mPage++;
        //                TTimer.timeFinished(1_500, () -> initData());
        //            }
        //            return false;
        //        });
        mBinding.rvJokePic.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mLayoutManager.invalidateSpanAssignments();

                if (mBinding.rvJokePic.getScrollState() == recyclerView.SCROLL_STATE_IDLE) {
                    int[] outRange = getOutRange();
                    //                    int len = outRange[1] - outRange[0] + 1;
                    //
                    //                    int num = (int) (Math.random() * 10);
                    //                    for (int i = 0; i < len; i++) {
                    //                        mItems.set(outRange[0] + i, num + "");
                    //                    }

                    TLog.d("root", outRange[0] + "、、、" + outRange[1]);
                    if (outRange[1] >= mDatas.size() - 1) {
                        mBinding.pb.setVisibility(View.VISIBLE);
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

    private StaggeredGridLayoutManager mLayoutManager;
    private int mPage = 1;
    private List<YYJoke341_2.Showapi_res_body.Contentlist> mDatas = new ArrayList<>();

    @Override protected void initView() {
        mBinding.srlJokePic.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY);
        mBinding.rvJokePic.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mBinding.rvJokePic.setLayoutManager(mLayoutManager);
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
}

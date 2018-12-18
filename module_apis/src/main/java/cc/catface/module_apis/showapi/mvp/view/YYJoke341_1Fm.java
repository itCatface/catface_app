package cc.catface.module_apis.showapi.mvp.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.catface.app_base.ARouterApp;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.AbsFragmentID;
import cc.catface.base.utils.android.Timer.TTimer;
import cc.catface.base.utils.android.net.retrofit.RetrofitCallback;
import cc.catface.base.utils.android.net.retrofit.RetrofitT;
import cc.catface.module_apis.R;
import cc.catface.module_apis.showapi.adapter.YYJoke341_1Adapter;
import cc.catface.module_apis.showapi.domain.YYJoke341_1;
import cc.catface.module_apis.showapi.global.Const;
import cc.catface.module_apis.showapi.mvp.presenter.YYJoke341_1PresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(YYJoke341_1PresenterImp.class)
public class YYJoke341_1Fm extends AbsFragmentID<YYJoke341_1View, YYJoke341_1PresenterImp> implements YYJoke341_1View {
    @Override public int layoutId() {
        return R.layout.showapi_fm_yy_joke_341_1;
    }

    private SwipeRefreshLayout srl_joke;
    private RecyclerView rv_joke;
    private ProgressBar pb;

    @Override public void ids(View v) {
        srl_joke = (SwipeRefreshLayout) v.findViewById(R.id.srl_joke);
        rv_joke = (RecyclerView) v.findViewById(R.id.rv_joke);
        pb = (ProgressBar) v.findViewById(R.id.pb);
    }

    @Override public void viewCreated() {
        initView();
        initEvent();
        initData();
    }

    private int mPage = 1;
    private List<YYJoke341_1.Showapi_res_body.Contentlist> mDatas = new ArrayList<>();

    private void initView() {
        srl_joke.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY);
        rv_joke.setHasFixedSize(true);
        rv_joke.setLayoutManager(new LinearLayoutManager(ARouterApp.getContext()));
    }

    @SuppressLint("ClickableViewAccessibility") private void initEvent() {
        srl_joke.setOnRefreshListener(() -> {
            srl_joke.setRefreshing(true);
            mPage = 1;
            initData();
        });

        rv_joke.setOnTouchListener((view, event) -> {
            if (View.VISIBLE != pb.getVisibility() && !rv_joke.canScrollVertically(1)) { // 1加载-1刷新
                pb.setVisibility(View.VISIBLE);
                mPage++;

                TTimer.timeFinished(1_200, this::initData);
            }
            return false;
        });
    }


    private Map<String, String> map = new HashMap<>();

    private void initData() {
        map.put("page", String.valueOf(mPage));
        RetrofitT.getInstance().post(Const.url_yy_joke_341_1, map, new RetrofitCallback() {
            @Override public void onSuccess(String result) {
                srl_joke.setRefreshing(false);
                pb.setVisibility(View.GONE);
                YYJoke341_1 yyJoke341_1 = new Gson().fromJson(result, YYJoke341_1.class);
                List<YYJoke341_1.Showapi_res_body.Contentlist> data = yyJoke341_1.getShowapi_res_body().getContentlist();
                if (null == rv_joke.getAdapter()) {
                    mDatas.addAll(data);
                    rv_joke.setAdapter(new YYJoke341_1Adapter(mDatas));
                } else if (mPage > 1) {
                    mDatas.addAll(data);
                    rv_joke.getAdapter().notifyItemRangeInserted(mDatas.size(), data.size());
                } else {
                    mDatas.clear();
                    mDatas.addAll(data);
                    rv_joke.getAdapter().notifyDataSetChanged();
                }
            }

            @Override public void onError(String error) {
                srl_joke.setRefreshing(false);
                pb.setVisibility(View.GONE);
            }
        });
    }
}

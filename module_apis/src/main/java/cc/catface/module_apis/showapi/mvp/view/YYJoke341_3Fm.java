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
import cc.catface.base.utils.android.net.retrofit.RetrofitCallback;
import cc.catface.base.utils.android.net.retrofit.RetrofitT;
import cc.catface.module_apis.R;
import cc.catface.module_apis.showapi.adapter.YYJoke341_3Adapter;
import cc.catface.module_apis.showapi.domain.YYJoke341_3;
import cc.catface.module_apis.showapi.global.Const;
import cc.catface.module_apis.showapi.mvp.presenter.YYJoke341_3PresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(YYJoke341_3PresenterImp.class)
public class YYJoke341_3Fm extends AbsFragmentID<YYJoke341_3View, YYJoke341_3PresenterImp> implements YYJoke341_3View {
    @Override public int layoutId() {
        return R.layout.showapi_fm_yy_joke_341_3;
    }

   private SwipeRefreshLayout srl_gif;
  private RecyclerView rv_gif;
   private ProgressBar pb;

    @Override public void ids(View v) {
        srl_gif = (SwipeRefreshLayout) v.findViewById(R.id.srl_gif);
        rv_gif = (RecyclerView) v.findViewById(R.id.rv_gif);
        pb = (ProgressBar) v.findViewById(R.id.pb);
    }

    @Override public void viewCreated() {
        initView();
        initEvent();
        initData();
    }

    private int mPage = 1;
    private List<YYJoke341_3.Showapi_res_body.Contentlist> mDatas = new ArrayList<>();

    private void initView() {
        srl_gif.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY);
        rv_gif.setHasFixedSize(true);
        rv_gif.setLayoutManager(new LinearLayoutManager(ARouterApp.getContext()));
    }

    @SuppressLint("ClickableViewAccessibility") private void initEvent() {
        srl_gif.setOnRefreshListener(() -> {
            srl_gif.setRefreshing(true);
            mPage = 1;
            initData();
        });

        rv_gif.setOnTouchListener((view, event) -> {
            if (View.VISIBLE != pb.getVisibility() && !rv_gif.canScrollVertically(1)) { // 1加载-1刷新
                pb.setVisibility(View.VISIBLE);
                mPage++;
                initData();
            }
            return false;
        });
    }


    private Map<String, String> map = new HashMap<>();

    private void initData() {
        map.put("page", String.valueOf(mPage));
        RetrofitT.getInstance().post(Const.url_yy_joke_341_3, map, new RetrofitCallback() {
            @Override public void onSuccess(String result) {
                srl_gif.setRefreshing(false);
                pb.setVisibility(View.GONE);
                YYJoke341_3 yyJoke341_3 = new Gson().fromJson(result, YYJoke341_3.class);
                List<YYJoke341_3.Showapi_res_body.Contentlist> data = yyJoke341_3.getShowapi_res_body().getContentlist();
                if (null == rv_gif.getAdapter()) {
                    mDatas.addAll(data);
                    rv_gif.setAdapter(new YYJoke341_3Adapter(mDatas));
                } else if (mPage > 1) {
                    mDatas.addAll(data);
                    rv_gif.getAdapter().notifyItemRangeInserted(mDatas.size(), data.size());
                } else {
                    mDatas.clear();
                    mDatas.addAll(data);
                    rv_gif.getAdapter().notifyDataSetChanged();
                }
            }

            @Override public void onError(String error) {
                srl_gif.setRefreshing(false);
                pb.setVisibility(View.GONE);
            }
        });
    }
}

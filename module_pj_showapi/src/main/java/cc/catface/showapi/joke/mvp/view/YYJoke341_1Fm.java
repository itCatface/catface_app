package cc.catface.showapi.joke.mvp.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.ctool.system.Timer.TTimer;
import cc.catface.base.utils.android.net.retrofit.RetrofitCallback;
import cc.catface.base.utils.android.net.retrofit.RetrofitT;
import cc.catface.showapi.R;
import cc.catface.showapi.databinding.ShowapiFmYyJoke3411Binding;
import cc.catface.showapi.joke.engine.adapter.YYJoke341_1Adapter;
import cc.catface.showapi.joke.engine.domain.YYJoke341_1;
import cc.catface.showapi.joke.engine.ShowapiConst;
import cc.catface.showapi.joke.mvp.vp.YYJoke341_1PresenterImp;
import cc.catface.showapi.joke.mvp.vp.YYJoke341_1VP;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class YYJoke341_1Fm extends LightFm<YYJoke341_1PresenterImp, ShowapiFmYyJoke3411Binding> implements YYJoke341_1VP.YYJoke341_1View {

    @Override public int layoutId() {
        return R.layout.showapi_fm_yy_joke_341_1;
    }

    @SuppressLint("ClickableViewAccessibility") @Override protected void initAction() {
        mBinding.srlJoke.setOnRefreshListener(() -> {
            mBinding.srlJoke.setRefreshing(true);
            mPage = 1;
            initData();
        });

        mBinding.rvJoke.setOnTouchListener((view, event) -> {
            if (View.VISIBLE != mBinding.pb.getVisibility() && !mBinding.rvJoke.canScrollVertically(1)) { // 1加载-1刷新
                mBinding.pb.setVisibility(View.VISIBLE);
                mPage++;

                TTimer.timeFinished(1_200, this::initData);
            }
            return false;
        });
    }

    private Map<String, String> map = new HashMap<>();

    @Override public void initData() {
        map.put("page", String.valueOf(mPage));
        RetrofitT.getInstance().post(ShowapiConst.url_yy_joke_341_1, map, new RetrofitCallback() {
            @Override public void onSuccess(String result) {
                mBinding.srlJoke.setRefreshing(false);
                mBinding.pb.setVisibility(View.GONE);
                YYJoke341_1 yyJoke341_1 = new Gson().fromJson(result, YYJoke341_1.class);
                List<YYJoke341_1.Showapi_res_body.Contentlist> data = yyJoke341_1.getShowapi_res_body().getContentlist();
                if (null == mBinding.rvJoke.getAdapter()) {
                    mDatas.addAll(data);
                    mBinding.rvJoke.setAdapter(new YYJoke341_1Adapter(mDatas));
                } else if (mPage > 1) {
                    mDatas.addAll(data);
                    mBinding.rvJoke.getAdapter().notifyItemRangeInserted(mDatas.size(), data.size());
                } else {
                    mDatas.clear();
                    mDatas.addAll(data);
                    mBinding.rvJoke.getAdapter().notifyDataSetChanged();
                }
            }

            @Override public void onError(String error) {
                mBinding.srlJoke.setRefreshing(false);
                mBinding.pb.setVisibility(View.GONE);
            }
        });
    }

    private int mPage = 1;
    private List<YYJoke341_1.Showapi_res_body.Contentlist> mDatas = new ArrayList<>();

    @Override protected void initView() {
        mBinding.srlJoke.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY);
        mBinding.rvJoke.setHasFixedSize(true);
        mBinding.rvJoke.setLayoutManager(new LinearLayoutManager(mActivity));
    }
}

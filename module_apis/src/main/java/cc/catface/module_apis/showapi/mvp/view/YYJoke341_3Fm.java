package cc.catface.module_apis.showapi.mvp.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import cc.catface.app_base.ARouterApp;
import cc.catface.base.core_framework.base_mvp.factory.CreatePresenter;
import cc.catface.base.core_framework.base_mvp.view.MvpFragment;
import cc.catface.base.utils.android.net.retrofit.RetrofitCallback;
import cc.catface.base.utils.android.net.retrofit.RetrofitT;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ShowapiFmYyJoke3413Binding;
import cc.catface.module_apis.showapi.adapter.YYJoke341_3Adapter;
import cc.catface.module_apis.showapi.domain.YYJoke341_3;
import cc.catface.module_apis.showapi.global.Const;
import cc.catface.module_apis.showapi.mvp.presenter.YYJoke341_3PresenterImp;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@CreatePresenter(YYJoke341_3PresenterImp.class)
public class YYJoke341_3Fm extends MvpFragment<YYJoke341_3View, YYJoke341_3PresenterImp, ShowapiFmYyJoke3413Binding> implements YYJoke341_3View {
    @Override public int layoutId() {
        return R.layout.showapi_fm_yy_joke_341_3;
    }

    private Map<String, String> map = new HashMap<>();

    @Override public void initData() {
        map.put("page", String.valueOf(mPage));
        RetrofitT.getInstance().post(Const.url_yy_joke_341_3, map, new RetrofitCallback() {
            @Override public void onSuccess(String result) {
                mBinding.srlGif.setRefreshing(false);
                mBinding.pb.setVisibility(View.GONE);
                YYJoke341_3 yyJoke341_3 = new Gson().fromJson(result, YYJoke341_3.class);
                List<YYJoke341_3.Showapi_res_body.Contentlist> data = yyJoke341_3.getShowapi_res_body().getContentlist();
                if (null == mBinding.rvGif.getAdapter()) {
                    mDatas.addAll(data);
                    mBinding.rvGif.setAdapter(new YYJoke341_3Adapter(mDatas));
                } else if (mPage > 1) {
                    mDatas.addAll(data);
                    mBinding.rvGif.getAdapter().notifyItemRangeInserted(mDatas.size(), data.size());
                } else {
                    mDatas.clear();
                    mDatas.addAll(data);
                    mBinding.rvGif.getAdapter().notifyDataSetChanged();
                }
            }

            @Override public void onError(String error) {
                mBinding.srlGif.setRefreshing(false);
                mBinding.pb.setVisibility(View.GONE);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility") @Override protected void initAction() {
        mBinding.srlGif.setOnRefreshListener(() -> {
            mBinding.srlGif.setRefreshing(true);
            mPage = 1;
            initData();
        });

        mBinding.rvGif.setOnTouchListener((view, event) -> {
            if (View.VISIBLE != mBinding.pb.getVisibility() && !mBinding.rvGif.canScrollVertically(1)) { // 1加载-1刷新
                mBinding.pb.setVisibility(View.VISIBLE);
                mPage++;
                initData();
            }
            return false;
        });
    }

    @Override public void viewCreated() {
        initView();
    }

    private int mPage = 1;
    private List<YYJoke341_3.Showapi_res_body.Contentlist> mDatas = new ArrayList<>();

    private void initView() {
        mBinding.srlGif.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY);
        mBinding.rvGif.setHasFixedSize(true);
        mBinding.rvGif.setLayoutManager(new LinearLayoutManager(ARouterApp.getContext()));
    }
}

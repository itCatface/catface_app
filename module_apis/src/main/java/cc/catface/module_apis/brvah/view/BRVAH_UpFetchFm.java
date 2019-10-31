package cc.catface.module_apis.brvah.view;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.recyclerview.widget.LinearLayoutManager;

import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.UpFetchAdapter;
import cc.catface.module_apis.brvah.domain.Movie;
import cc.catface.module_apis.databinding.BrvahFmUpFetchBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_UpFetchFm extends LightFm<LightPresenter, BrvahFmUpFetchBinding> {

    private UpFetchAdapter mAdapter;

    @Override public int layoutId() {
        return R.layout.brvah_fm_up_fetch;
    }


    @Override protected void initView() {
        mAdapter = new UpFetchAdapter();
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mBinding.rvList.setAdapter(mAdapter);
        mAdapter.setNewData(genData());
        mAdapter.setUpFetchEnable(true);
        /**
         * start fetch when scroll to position 2, default is 1.
         */
        mAdapter.setStartUpFetchPosition(2);
        mAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override public void onUpFetch() {
                startUpFetch();
            }
        });
    }

    private int count;

    private void startUpFetch() {
        count++;
        /**
         * api_set fetching on when start network request.
         */
        mAdapter.setUpFetching(true);
        /**
         * get data from internet.
         */
        mBinding.rvList.postDelayed(new Runnable() {
            @Override public void run() {
                mAdapter.addData(0, genData());
                /**
                 * api_set fetching off when network request ends.
                 */
                mAdapter.setUpFetching(false);
                /**
                 * api_set fetch enable false when you don't need anymore.
                 */
                if (count > 5) {
                    mAdapter.setUpFetchEnable(false);
                }
            }
        }, 300);
    }


    private List<Movie> genData() {
        ArrayList<Movie> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String name = "Chad";
            int price = i + 1;
            int len = 100 + i * 2;
            Movie movie = new Movie(name, len, price, "He was one of Australia's most distinguished artistes");
            list.add(movie);
        }
        return list;
    }
}

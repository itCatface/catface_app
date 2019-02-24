package cc.catface.module_apis.brvah.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.recyclerview.widget.LinearLayoutManager;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.module_apis.R;
import cc.catface.module_apis.brvah.adapter.DataBindingAdapter;
import cc.catface.module_apis.brvah.domain.Movie;
import cc.catface.module_apis.databinding.BrvahFmDataBindingBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class BRVAH_DataBindingFm extends NormalFragment<BrvahFmDataBindingBinding> {
    @Override public int layoutId() {
        return R.layout.brvah_fm_data_binding;
    }

    private DataBindingAdapter mAdapter;

    @Override public void createView() {
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(mActivity));

        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new DataBindingAdapter(R.layout.brvah_item_data_binding_movie, genData());
        mBinding.rvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> TToast.get(mActivity).showBShortView("item clicked..." + position, TToast.B_INFO));
        mAdapter.setOnItemLongClickListener((adapter, view, position) -> {
            TToast.get(mActivity).showBShortView("item long clicked..." + position, TToast.B_INFO);
            return true;
        });
        mAdapter.setOnItemChildLongClickListener((adapter, view, position) -> {
            TToast.get(mActivity).showBShortView("item child long clicked..." + position, TToast.B_INFO);
            return true;
        });
    }


    private List<Movie> genData() {
        ArrayList<Movie> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String name = "catface" + i;
            int price = random.nextInt(10) + 10;
            int len = random.nextInt(80) + 60;
            Movie movie = new Movie(name, len, price, "He was one of Australia's most distinguished artistes");
            list.add(movie);
        }
        return list;
    }
}

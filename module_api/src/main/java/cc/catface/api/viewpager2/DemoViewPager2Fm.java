package cc.catface.api.viewpager2;

import android.os.Message;
import android.widget.Toast;

import androidx.viewpager2.widget.ViewPager2;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFragmentViewPager2Binding;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.ctool.system.TWeakHandler;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoViewPager2Fm extends NormalFragment<ApiFragmentViewPager2Binding> implements TWeakHandler.MessageListener {

    private TWeakHandler<DemoViewPager2Fm> mHandler;

    @Override public void handleMessage(Message msg) {

    }

    @Override public int layoutId() {
        return R.layout.api_fragment_view_pager_2;
    }

    @Override protected void initData() {
        mHandler = new TWeakHandler<>(this);
    }

    @Override protected void createView() {

        mBinding.vp21.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                Toast.makeText(mActivity, "当前页：" + position, Toast.LENGTH_SHORT).show();
            }
        });

        mBinding.vp21.setAdapter(new ViewPager2Adapter());

        mBinding.vp22.setAdapter(new ViewPager2Adapter());
    }


}

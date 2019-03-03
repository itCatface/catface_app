package cc.catface.module_apis.flow_layout;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cc.catface.app_base.Const;
import cc.catface.app_base.TestDataSource;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.android.common_title.TitleFontAwesome;
import cc.catface.base.utils.java.TNumber;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisActivityDemoFlowLayoutBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.apis_flow_layout)
public class DemoFlowLayoutActivity extends NormalActivity<ApisActivityDemoFlowLayoutBinding> {

    @Override public int layoutId() {
        return R.layout.apis_activity_demo_flow_layout;
    }

    private List<String> mTags = new ArrayList<>();

    @Override
    public void initData() {
        mTags = TestDataSource.get_lol_zh_names();
    }

    @Override public void create() {
        title();
        showData();
    }

    private void title() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.ARouter.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left).setIcon4("更新数据").setOnClickListener((TitleFontAwesome.OnClickListener) view -> {
            if(view.getId() == R.id.ttv4) showData();
        });
    }


    private void showData() {
        int totalTagSize = mTags.size();
        int[] randoms = TNumber.getRandoms(0, totalTagSize - 1, new Random().nextInt(totalTagSize));
        if(null == randoms) return;

        if(randoms.length < 1) {
            TToast.get(this).showBShortView("当前无可显示数据", TToast.B_ERROR);
            mBinding.flowlayout.setVisibility(View.GONE);
            return;
        }


        int length = randoms.length;

        mBinding.flowlayout.setVisibility(View.VISIBLE);
        mBinding.flowlayout.removeAllViews();

        for(int i = 0; i < length; i++) {
            TextView textView = buildLabel(mTags.get(randoms[i]));
            mBinding.flowlayout.addView(textView);
        }
    }

    private TextView buildLabel(final String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv.setPadding((int) dpToPx(16), (int) dpToPx(8), (int) dpToPx(16), (int) dpToPx(8));
        tv.setTextColor(Color.parseColor("#565959"));
        tv.setBackgroundResource(R.drawable.apis_shape_flowlayout_item_bg);
        tv.setOnClickListener(v -> Toast.makeText(DemoFlowLayoutActivity.this, "当前点击内容：" + text, Toast.LENGTH_SHORT).show());
        return tv;
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}

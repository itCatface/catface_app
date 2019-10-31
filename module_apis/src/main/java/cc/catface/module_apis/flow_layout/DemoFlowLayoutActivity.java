package cc.catface.module_apis.flow_layout;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cc.catface.app_base.Const;
import cc.catface.app_base.TestDataSource;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.java.TNumber;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisActivityDemoFlowLayoutBinding;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.apis_flow_layout) public class DemoFlowLayoutActivity extends LightAct<LightPresenter, ApisActivityDemoFlowLayoutBinding> {

    @Override public int layoutId() {
        return R.layout.apis_activity_demo_flow_layout;
    }

    private List<String> mTags = new ArrayList<>();

    @Override public void initData() {
        mTags = TestDataSource.get_lol_zh_names();
    }

    @Override public void created() {
        initToolBar();
        showData();
    }


    private void showData() {
        int totalTagSize = mTags.size();
        int[] randoms = TNumber.getRandoms(0, totalTagSize - 1, new Random().nextInt(totalTagSize));
        if (null == randoms) return;

        if (randoms.length < 1) {
            TToast.get(this).showBShortView("当前无可显示数据", TToast.B_ERROR);
            mBinding.flowlayout.setVisibility(View.GONE);
            return;
        }


        int length = randoms.length;

        mBinding.flowlayout.setVisibility(View.VISIBLE);
        mBinding.flowlayout.removeAllViews();

        for (int i = 0; i < length; i++) {
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


    /** tool bar */
    private void initToolBar() {
        Toolbar toolbar = mBinding.iTbApis.tbTitle;
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (null != bar) {
            bar.setDisplayShowHomeEnabled(true);
            bar.setTitle("流式布局组件使用");
        }
        toolbar.setNavigationIcon(R.mipmap.flaticon_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_menu, menu);
        menu.findItem(R.id.menu_normal).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.findItem(R.id.menu_normal).setVisible(true);
        menu.findItem(R.id.menu_normal).setTitle("更新数据");
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (R.id.menu_normal == id) {
            showData();
        }
        return super.onOptionsItemSelected(item);
    }
}

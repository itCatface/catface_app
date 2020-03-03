package cc.catface.module_apis.memo.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cc.catface.app_base.ARouterApp;
import cc.catface.app_base.Const;
import cc.catface.app_base.greendao.Memo;
import cc.catface.app_base.greendao.domain.greendao_gen.MemoDao;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.ctool.system.TLog;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.android.common_title.TitleFontAwesome;
import cc.catface.base.utils.android.view.recyclerview.divider.ItemDecorationDivider;
import cc.catface.base.utils.android.view.recyclerview.swipe.SwipeItemListener;
import cc.catface.module_apis.R;
import cc.catface.module_apis.databinding.ApisActivityMemoBinding;
import cc.catface.module_apis.memo.adapter.MemoAdapter;
import cc.catface.module_apis.memo.vp.MemoPresenterImp;
import cc.catface.module_apis.memo.vp.MemoVP;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.apis_memo) public class MemoActivity extends LightAct<MemoPresenterImp, ApisActivityMemoBinding> implements MemoVP.MemoView {

    @Override public int layoutId() {
        return R.layout.apis_activity_memo;
    }

    @Override protected void initPresenter() {
        mPresenter = new MemoPresenterImp(this, this);
    }

    @Override protected void initAction() {
        mBinding.tfaMemo.setOnClickListener((TitleFontAwesome.OnClickListener) view -> {
            if (R.id.ttv1 == view.getId()) {  // 返回
                finish();
            } else if (R.id.ttv2 == view.getId()) {  // 查询
                dialog(MEMO_SEARCH);
            } else if (R.id.ttv3 == view.getId()) { // 排序
                popup(view);
            } else if (R.id.ttv4 == view.getId()) { // 新增
                dialog(MEMO_ADD);
            }
        });
    }

    @Override protected void initView() {
        mBinding.smrvMemo.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mBinding.smrvMemo.setLayoutManager(mLayoutManager);
        mBinding.smrvMemo.addItemDecoration(new ItemDecorationDivider(this, ItemDecorationDivider.VERTICAL));

        mAdapter = new MemoAdapter(mDatas, mBinding.smrvMemo, mLayoutManager, new MemoSwipeItemListener());
        mBinding.smrvMemo.setAdapter(mAdapter);
    }


    private MemoDao mDao;
    private List<Memo> mDatas = new ArrayList<>();
    private MemoAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override public void initData() {
        mDao = ARouterApp.getDaoSession().getMemoDao();
        mDatas = mDao.queryBuilder().list();
    }

    @Override public void created() {
        initToolBar();
        title();
    }

    private void title() {
        mBinding.tfaMemo.setTitle(getIntent().getStringExtra(Const.ARouter.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left).setIcon2(R.string.fa_search).setIcon4(R.string.fa_plus).setIcon3(R.string.fa_reorder);
    }


    class MemoSwipeItemListener implements SwipeItemListener {

        @Override public void onItemClick(RecyclerView.ViewHolder holder, int position, String content) {
            //            TVibrator.vibrate(MemoActivity.this, 40);
        }

        @Override public void onLeftMenuClick(int position) { }

        @Override public void onRightMenuClick(int position) { }
    }

    /** 增查改弹框 */
    private final String MEMO_ADD = "memo_add", MEMO_SEARCH = "memo_search", MEMO_UPDATE = "memo_update";

    private void dialog(String type) {
        View view = View.inflate(this, R.layout.apis_dialog_memo_add, null);
        Dialog dialog = new Dialog(this, R.style.CustomDialog);
        dialog.setContentView(view);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (getWindowManager().getDefaultDisplay().getWidth() * 0.8);
        attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);

        TextView tv_title = view.findViewById(R.id.tv_title);
        EditText et_content = view.findViewById(R.id.et_content);
        EditText et_tag = view.findViewById(R.id.et_tag);
        RelativeLayout rl_star = view.findViewById(R.id.rl_star);
        RatingBar rb = view.findViewById(R.id.rb);
        Button bt_ok = view.findViewById(R.id.bt_ok);
        Button bt_cancel = view.findViewById(R.id.bt_cancel);


        switch (type) {
            case MEMO_ADD:
                bt_ok.setOnClickListener(btView -> {
                    dialog.dismiss();

                    String content = et_content.getText().toString();
                    String tag = TextUtils.isEmpty(et_tag.getText().toString()) ? "默认" : et_tag.getText().toString();
                    int stars = (int) rb.getRating();

                    insert(content, tag, stars);
                });

                bt_cancel.setOnClickListener(btView -> dialog.dismiss());
                break;

            case MEMO_SEARCH:
                et_tag.setVisibility(View.GONE);
                rl_star.setVisibility(View.GONE);
                tv_title.setText("查询备忘");
                bt_ok.setText("查询");
                bt_cancel.setText("所有备忘");

                bt_ok.setOnClickListener(btView -> {
                    dialog.dismiss();

                    String content = et_content.getText().toString();
                    query(content);
                });

                bt_cancel.setOnClickListener(btView -> {
                    dialog.dismiss();

                    mDatas.clear();
                    mDatas.addAll(mDao.queryBuilder().list());
                    mBinding.smrvMemo.getAdapter().notifyItemRangeChanged(0, mDatas.size());
                });
                break;

            case MEMO_UPDATE:
                tv_title.setText("修改备忘");
                bt_ok.setText("修改");
                bt_cancel.setText("取消");

                bt_ok.setOnClickListener(btView -> {
                    dialog.dismiss();

                    String content = et_content.getText().toString();
                    String tag = TextUtils.isEmpty(et_tag.getText().toString()) ? "默认" : et_tag.getText().toString();
                    int stars = (int) rb.getRating();

                    update(content, tag, stars);
                });

                bt_cancel.setOnClickListener(btView -> dialog.dismiss());
                break;
        }
    }


    /* 增删改查逻辑 */
    private void insert(String content, String tag, int stars) {
        if (TextUtils.isEmpty(content.trim())) TToast.get(this).showShortNormal("请添加备忘内容...");
        else {
            Memo memo = new Memo(SystemClock.currentThreadTimeMillis(), content, SystemClock.currentThreadTimeMillis(), tag, stars);
            mDao.insert(memo);
            mDatas.add(0, memo);
            mBinding.smrvMemo.getAdapter().notifyItemInserted(0);
            mBinding.smrvMemo.getAdapter().notifyItemRangeChanged(0, mDatas.size());
        }
    }


    private void update(String content, String tag, int stars) {
        if (TextUtils.isEmpty(content.trim())) TToast.get(this).showShortNormal("清添加修改内容...");
        else {
            Memo memo = new Memo(SystemClock.currentThreadTimeMillis(), content, SystemClock.currentThreadTimeMillis(), tag, stars);
            mDao.update(memo);
            mBinding.smrvMemo.getAdapter().notifyItemRangeChanged(0, mDatas.size());
        }
    }


    private void query(String content) {
        if (TextUtils.isEmpty(content.trim())) TToast.get(this).showShortNormal("清添加查询内容...");
        else {
            List<Memo> list = mDao.queryBuilder().where(MemoDao.Properties.Content.like("%" + content + "%")).list();
            TLog.d("size====" + list.size());
            mDatas.clear();
            mDatas.addAll(list);
            mBinding.smrvMemo.getAdapter().notifyItemRangeChanged(0, mDatas.size());
        }
    }


    /** 排序 */
    private PopupWindow mPopup;

    private void popup(View view) {
        if (null == mPopup) {
            ListView lv = new ListView(this);
            lv.setBackgroundColor(Color.WHITE);

            String tagStar = "排序by星级", tagTime = "排序by修改时间", tagUUID = "排序by_uuid", tagMess = "排列by乱序", tagAddTestData = "添加测试数据", tagDelAllData = "删除所有备忘";
            String[] tagArr = {tagStar, tagTime, tagUUID, tagMess, tagAddTestData, tagDelAllData};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tagArr);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener((adapterView, view1, i, l) -> {
                if (tagStar.equals(tagArr[i]))
                    Collections.sort(mDatas, (memo1, memo2) -> memo2.getStars() - memo1.getStars());
                else if (tagTime.equals(tagArr[i]))
                    Collections.sort(mDatas, (memo1, memo2) -> (int) (memo2.getDate() - memo1.getDate()));
                else if (tagUUID.equals(tagArr[i]))
                    Collections.sort(mDatas, (memo1, memo2) -> (int) (memo2.getId() - memo1.getId()));
                else if (tagMess.equals(tagArr[i])) Collections.shuffle(mDatas);
                else if (tagAddTestData.equals(tagArr[i])) {
                    for (int j = 0; j < 50; j++) {
                        Memo memo = new Memo(SystemClock.currentThreadTimeMillis(), "第-" + j + "-条记录", SystemClock.currentThreadTimeMillis(), "默认", new Random().nextInt(5));
                        mDao.insert(memo);
                    }
                    mBinding.smrvMemo.getAdapter().notifyDataSetChanged();
                } else if (tagDelAllData.equals(tagArr[i])) {
                    mDatas.clear();
                    mDao.deleteAll();
                    mBinding.smrvMemo.getAdapter().notifyDataSetChanged();
                }

                mBinding.smrvMemo.getAdapter().notifyDataSetChanged();
                mPopup.dismiss();
            });

            mPopup = new PopupWindow(lv, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopup.setBackgroundDrawable(new ColorDrawable());
        }

        mPopup.showAsDropDown(view, 40, view.getHeight());
    }


    /** tool bar */
    private ActionBar mBar;

    private void initToolBar() {
        Toolbar toolbar = mBinding.iTbApis.tbTitle;
        setSupportActionBar(toolbar);
        mBar = getSupportActionBar();
        if (null != mBar) {
            mBar.setDisplayShowHomeEnabled(true);
            mBar.setTitle(mTitle);
        }
        toolbar.setNavigationIcon(R.mipmap.flaticon_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private String mTitle = "memo", mNormalTitle = "";

    private void updateToolBar() {
        if (null != mBar) {
            mBar.setTitle(mTitle);
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_menu, menu);
        menu.findItem(R.id.menu_search).setVisible(false);
        menu.findItem(R.id.menu_normal).setVisible(!TextUtils.isEmpty(mNormalTitle.trim()));
        menu.findItem(R.id.menu_plus_1).setVisible(false);
        menu.findItem(R.id.menu_plus_2).setVisible(false);

        menu.findItem(R.id.menu_normal).setTitle(mNormalTitle);
        menu.findItem(R.id.menu_normal).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}

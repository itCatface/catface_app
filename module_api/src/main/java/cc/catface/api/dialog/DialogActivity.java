package cc.catface.api.dialog;

import android.app.ProgressDialog;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.core_framework.base_normal.NormalBaseActivity;
import cc.catface.base.utils.android.common_print.dialog.normal.TDialogNormal;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.android.common_title.TitleBuilder;
import cc.catface.api.R;
import cc.catface.api.dialog.adapter.DialogAdapter;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = "/api/dialog")
public class DialogActivity extends NormalBaseActivity {
    @Override public int layoutId() {
        return R.layout.api_activity_dialog;
    }

    private RecyclerView rv_dialog;
    private List<String> mDatas;
    private DialogAdapter mAdapter;

    @Override public void create() {
        rv_dialog = (RecyclerView) findViewById(R.id.rv_dialog);
        initTitle();
        initData();
        initRV();
        initAdapter();
    }

    private void initTitle() {
        new TitleBuilder(this).setIVLeftRes(R.mipmap.flaticon_back).setTVCenterText("Dialog示例").setListener(v -> {
            if (R.id.iv_title_left == v.getId()) finish();
        });
    }

    private final String NORMAL_NOTIFICATION = "(系统)[通知对话框]", NORMAL_LIST = "(系统)[列表对话框]", NORMAL_SINGLE_CHOICE = "(系统)[单选对话框]", NORMAL_MULTI_CHOICE = "(系统)[复选对话框]", NORMAL_PROGRESS = "(系统)" +
            "[进度对话框]", NORMAL_CUSTOM_SIMPLE = "(定制)[简单的自定义样式的弹框]";

    private void initData() {
        mDatas = new ArrayList<>();
        mDatas.add(NORMAL_NOTIFICATION);
        mDatas.add(NORMAL_LIST);
        mDatas.add(NORMAL_SINGLE_CHOICE);
        mDatas.add(NORMAL_MULTI_CHOICE);
        mDatas.add(NORMAL_PROGRESS);
        mDatas.add(NORMAL_CUSTOM_SIMPLE);
    }

    private void initRV() {
        rv_dialog.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_rv_devider_colors));
        rv_dialog.addItemDecoration(decoration);
    }

    private void initAdapter() {
        mAdapter = new DialogAdapter(mDatas);
        String[] heroes = {"艾希", "锤石", "德莱文", "卡特"};
        mAdapter.setOnItemClickListener((adapter, view, i) -> {
            switch (mDatas.get(i)) {
                case NORMAL_NOTIFICATION:
                    TDialogNormal.get(this).notification(R.mipmap.ic_launcher_round, "号外号外", "今天有大新闻...", "看", "不看", isPositive -> {
                        if (isPositive) TToast.get(this).showBShortView("看看哦", TToast.B_SUCCESS);
                        else TToast.get(this).showBShortView("不看看了", TToast.B_CANCEL);
                    });
                    break;
                case NORMAL_LIST:
                    TDialogNormal.get(this).list(R.mipmap.ic_launcher_round, "选择英雄", heroes, chosenPosition -> TToast.get(this).showBLongView("你选择了：" + heroes[chosenPosition], TToast.B_SUCCESS));
                    break;
                case NORMAL_SINGLE_CHOICE:
                    TDialogNormal.get(this).singleChoice(R.mipmap.ic_launcher_round, "选择一个英雄", heroes, "选好了", "取消", new TDialogNormal.SingleChoiceCallback() {
                        @Override public void onItemClick(int chosenPosition) {
                            TToast.get(DialogActivity.this).showShortImmediately("已选：" + heroes[chosenPosition]);
                        }

                        @Override public void onButtonClick(int chosenPosition, boolean isPositive) {
                            TToast.get(DialogActivity.this).showShortImmediately(isPositive ? "您选择了：" + heroes[chosenPosition] : "您取消了选择");
                        }
                    });
                    break;
                case NORMAL_MULTI_CHOICE:
                    TDialogNormal.get(this).MultiChoice(R.mipmap.ic_launcher_round, "选择您需要的英雄", heroes, "选好了", "取消", new TDialogNormal.MultiChoiceCallback() {
                        @Override public void onItemClick(int position, boolean isChosen) {
                            TToast.get(DialogActivity.this).showShortImmediately("选项：" + heroes[position] + (isChosen ? "已选" : "取消"));
                        }

                        @Override public void onButtonClick(boolean isPositive, List<String> chosenList) {
                            TToast.get(DialogActivity.this).showShortImmediately(isPositive ? "您选择这么多：" + chosenList.size() : "您取消了选择");
                        }
                    });
                    break;
                case NORMAL_PROGRESS:
                    ProgressDialog progressDialog = TDialogNormal.get(this).progress(R.mipmap.ic_launcher_round, "正在下载", "即将获得全套福利视频！", ProgressDialog.STYLE_HORIZONTAL, 100, 1);
                    new Thread(() -> {
                        while (true) {
                            SystemClock.sleep(100);
                            progressDialog.incrementProgressBy(1);
                            if (progressDialog.getProgress() == progressDialog.getMax()) {
                                runOnUiThread(new Runnable() {
                                    @Override public void run() {
                                        progressDialog.dismiss();
                                        TToast.get(DialogActivity.this).showShortImmediately("准备好了哦~");
                                    }
                                });
                                break;
                            }
                        }
                    }).start();
                    break;

                case NORMAL_CUSTOM_SIMPLE:
                    TDialogNormal.get(this).customSimple();
                    break;
            }
        });
        rv_dialog.setAdapter(mAdapter);
    }
}

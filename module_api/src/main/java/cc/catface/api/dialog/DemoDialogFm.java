package cc.catface.api.dialog;

import android.app.ProgressDialog;
import android.os.SystemClock;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityDialogBinding;
import cc.catface.api.dialog.adapter.DialogAdapter;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.core_framework.base_normal.NormalFragment;
import cc.catface.base.utils.android.common_print.dialog.normal.TDialogNormal;
import cc.catface.base.utils.android.common_print.toast.TToast;
import cc.catface.base.utils.android.common_recyclerview.TRV;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.ARouter.api_dialog)
public class DemoDialogFm extends NormalFragment<ApiActivityDialogBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_dialog;
    }

    private final String NORMAL_NOTIFICATION_TWO = "(系统)[通知对话框-两个选项]", NORMAL_NOTIFICATION_THREE = "(系统)[通知对话框-三个选项]", NORMAL_LIST = "(系统)[列表对话框]", NORMAL_SINGLE_CHOICE = "(系统)[单选对话框]",
            NORMAL_MULTI_CHOICE = "(系统)[复选对话框]", NORMAL_SINGLE_EDITTEXT = "(系统)[单输入框对话框]", NORMAL_PROGRESS = "(系统)" + "[进度对话框]", NORMAL_CUSTOM_SIMPLE = "(定制)[简单的自定义样式的弹框]";
    private List<String> mDatas;
    private DialogAdapter mAdapter;

    @Override public void initData() {
        mDatas = new ArrayList<>();
        mDatas.add(NORMAL_NOTIFICATION_TWO);
        mDatas.add(NORMAL_NOTIFICATION_THREE);
        mDatas.add(NORMAL_LIST);
        mDatas.add(NORMAL_SINGLE_CHOICE);
        mDatas.add(NORMAL_MULTI_CHOICE);
        mDatas.add(NORMAL_SINGLE_EDITTEXT);
        mDatas.add(NORMAL_PROGRESS);
        mDatas.add(NORMAL_CUSTOM_SIMPLE);
    }

    @Override public void createView() {
        title();
        initAdapter();
    }

    private void title() {
        mBinding.tfa.setTitle("dialog").setIcon1(R.string.fa_chevron_left);
    }


    private void initAdapter() {
        TRV.initDefaultRV(mActivity, mBinding.rvDialog);

        mAdapter = new DialogAdapter(mDatas);
        String[] heroes = {"艾希", "锤石", "德莱文", "卡特"};
        mAdapter.setOnItemClickListener((adapter, view, i) -> {
            switch (mDatas.get(i)) {
                case NORMAL_NOTIFICATION_TWO:
                    TDialogNormal.get(mActivity).notification(R.mipmap.ic_launcher_round, "号外号外", "今天有大新闻...", "看", "不看", notificationType -> {
                        switch (notificationType) {
                            case TDialogNormal.NotificationPositive:
                                TToast.get(mActivity).showBShortView("看看哦", TToast.B_SUCCESS);
                                break;
                            case TDialogNormal.NotificationNegative:
                                TToast.get(mActivity).showBShortView("不看看了", TToast.B_CANCEL);
                                break;
                            case TDialogNormal.NotificationNeutral:

                                break;
                        }
                    });
                    break;
                case NORMAL_NOTIFICATION_THREE:
                    TDialogNormal.get(mActivity).notification(R.mipmap.ic_launcher_round, "号外号外", "今天有大新闻...", "看", "不看", "保持中立", notificationType -> {
                        switch (notificationType) {
                            case TDialogNormal.NotificationPositive:
                                TToast.get(mActivity).showBShortView("看看哦", TToast.B_SUCCESS);
                                break;
                            case TDialogNormal.NotificationNegative:
                                TToast.get(mActivity).showBShortView("不看看了", TToast.B_CANCEL);
                                break;
                            case TDialogNormal.NotificationNeutral:
                                TToast.get(mActivity).showBShortView("保持中立", TToast.B_INFO);
                                break;
                        }
                    });
                    break;
                case NORMAL_LIST:
                    TDialogNormal.get(mActivity).list(R.mipmap.ic_launcher_round, "选择英雄", heroes, chosenPosition -> TToast.get(mActivity).showBLongView("你选择了：" + heroes[chosenPosition], TToast.B_SUCCESS));
                    break;
                case NORMAL_SINGLE_CHOICE:
                    TDialogNormal.get(mActivity).singleChoice(R.mipmap.ic_launcher_round, "选择一个英雄", heroes, "选好了", "取消", new TDialogNormal.SingleChoiceCallback() {
                        @Override public void onItemClick(int chosenPosition) {
                            TToast.get(mActivity).showShortImmediately("已选：" + heroes[chosenPosition]);
                        }

                        @Override public void onButtonClick(int chosenPosition, boolean isPositive) {
                            TToast.get(mActivity).showShortImmediately(isPositive ? "您选择了：" + heroes[chosenPosition] : "您取消了选择");
                        }
                    });
                    break;
                case NORMAL_MULTI_CHOICE:
                    TDialogNormal.get(mActivity).multiChoice(R.mipmap.ic_launcher_round, "选择您需要的英雄", heroes, "选好了", "取消", new TDialogNormal.MultiChoiceCallback() {
                        @Override public void onItemClick(int position, boolean isChosen) {
                            TToast.get(mActivity).showShortImmediately("选项：" + heroes[position] + (isChosen ? "已选" : "取消"));
                        }

                        @Override public void onButtonClick(boolean isPositive, List<String> chosenList) {
                            TToast.get(mActivity).showShortImmediately(isPositive ? "您选择这么多：" + chosenList.size() : "您取消了选择");
                        }
                    });
                    break;
                case NORMAL_SINGLE_EDITTEXT:
                    EditText et = new EditText(mActivity);
                    TDialogNormal.get(mActivity).singleEditText(R.mipmap.ic_launcher, "有点东西", "这里输入一些内容", "确定", "取消", et, notificationType -> {
                        switch (notificationType) {
                            case TDialogNormal.NotificationPositive:
                                TToast.get(mActivity).showBShortView("确定了->" + et.getText().toString(), TToast.B_SUCCESS);
                                break;
                            case TDialogNormal.NotificationNegative:
                                TToast.get(mActivity).showBShortView("不用了", TToast.B_CANCEL);
                                break;
                        }
                    });
                    break;
                case NORMAL_PROGRESS:
                    ProgressDialog progressDialog = TDialogNormal.get(mActivity).progress(R.mipmap.ic_launcher_round, "正在下载", "即将获得全套福利视频！", ProgressDialog.STYLE_HORIZONTAL, 100, 1);
                    new Thread(() -> {
                        while (true) {
                            SystemClock.sleep(100);
                            progressDialog.incrementProgressBy(1);
                            if (progressDialog.getProgress() == progressDialog.getMax()) {
                                mActivity.runOnUiThread(new Runnable() {
                                    @Override public void run() {
                                        progressDialog.dismiss();
                                        TToast.get(mActivity).showShortImmediately("准备好了哦~");
                                    }
                                });
                                break;
                            }
                        }
                    }).start();
                    break;

                case NORMAL_CUSTOM_SIMPLE:
                    TDialogNormal.get(mActivity).customSimple();
                    break;
            }
        });
        mBinding.rvDialog.setAdapter(mAdapter);
    }
}

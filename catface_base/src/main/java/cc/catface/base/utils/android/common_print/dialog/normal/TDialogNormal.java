package cc.catface.base.utils.android.common_print.dialog.normal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TDialogNormal implements TDialogNormalI {


    private Context mCtx;
    private AlertDialog.Builder mBuilder;
    private ProgressDialog mProgressDialog;

    public static TDialogNormal get(Context ctx) {
        return new TDialogNormal(ctx);
    }

    private TDialogNormal(Context ctx) {
        mCtx = ctx;
    }


    /** 普通对话框(包含两个选项和三个选项) */
    public static final int NotificationPositive = 1;
    public static final int NotificationNegative = -1;
    public static final int NotificationNeutral = 0;

    public interface NotificationCallback {
        void onClick(int notificationType);
    }

    @Override public void notification(String title, String content, String btPositiveText, String btNegativeText, NotificationCallback callback) {
        notification(0, title, content, btPositiveText, btNegativeText, callback);
    }

    @Override public void notification(int iconId, String title, String content, String btPositiveText, String btNegativeText, final NotificationCallback callback) {
        iconId = (0 == iconId ? R.mipmap.toast_ic_default_warning_sharp_white_96 : iconId);
        mBuilder = null;
        mBuilder = new AlertDialog.Builder(mCtx);
        mBuilder.setIcon(iconId).setTitle(title).setMessage(content).setPositiveButton(btPositiveText, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                if (null != callback) callback.onClick(NotificationPositive);
            }
        }).setNegativeButton(btNegativeText, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                if (null != callback) callback.onClick(NotificationNegative);
            }
        }).show();
    }

    @Override public void notification(String title, String content, String btPositiveText, String btNegativeText, String btNeutralText, NotificationCallback callback) {
        notification(0, title, content, btPositiveText, btNegativeText, btNeutralText, callback);
    }

    @Override public void notification(int iconId, String title, String content, String btPositiveText, String btNegativeText, String btNeutralText, NotificationCallback callback) {
        iconId = (0 == iconId ? R.mipmap.toast_ic_default_warning_sharp_white_96 : iconId);
        mBuilder = null;
        mBuilder = new AlertDialog.Builder(mCtx);
        mBuilder.setIcon(iconId).setTitle(title).setMessage(content).setPositiveButton(btPositiveText, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                if (null != callback) callback.onClick(NotificationPositive);
            }
        }).setNegativeButton(btNegativeText, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                if (null != callback) callback.onClick(NotificationNegative);
            }
        }).setNeutralButton(btNeutralText, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                if (null != callback) callback.onClick(NotificationNeutral);
            }
        }).show();
    }


    /** 列表对话框 */
    public interface ListCallback {
        void onClick(int chosenPosition);
    }

    @Override public void list(String title, String[] items, ListCallback callback) {
        list(0, title, items, callback);
    }

    @Override public void list(int iconId, String title, String[] items, final ListCallback callback) {
        iconId = (0 == iconId ? R.mipmap.toast_ic_default_warning_sharp_white_96 : iconId);
        mBuilder = null;
        mBuilder = new AlertDialog.Builder(mCtx);
        mBuilder.setIcon(iconId).setTitle(title).setItems(items, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                if (null != callback) callback.onClick(which);
            }
        }).show();
    }


    /** 单选对话框 */
    public interface SingleChoiceCallback {
        void onItemClick(int chosenPosition);

        void onButtonClick(int chosenPosition, boolean isPositive);
    }

    @Override public void singleChoice(String title, String[] items, String btPositiveText, String btNegativeText, SingleChoiceCallback callback) {
        singleChoice(0, title, items, btPositiveText, btNegativeText, callback);
    }


    private int mSingleChosenPosition = -1;

    @Override public void singleChoice(int iconId, String title, String[] items, String btPositiveText, String btNegativeText, final SingleChoiceCallback callback) {
        iconId = (0 == iconId ? R.mipmap.toast_ic_default_warning_sharp_white_96 : iconId);
        mBuilder = null;
        mBuilder = new AlertDialog.Builder(mCtx);
        mBuilder.setIcon(iconId).setTitle(title).setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                mSingleChosenPosition = which;
                if (null != callback) callback.onItemClick(mSingleChosenPosition);
            }
        }).setPositiveButton(btPositiveText, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                if (null != callback) callback.onButtonClick(mSingleChosenPosition, true);
            }
        }).setNegativeButton(btNegativeText, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                if (null != callback) callback.onButtonClick(mSingleChosenPosition, false);
            }
        }).show();
    }

    /** 复选对话框 */
    public interface MultiChoiceCallback {
        void onItemClick(int position, boolean isChosen);

        void onButtonClick(boolean isPositive, List<String> chosenList);
    }


    @Override public void multiChoice(String title, String[] items, String btPositiveText, String btNegativeText, MultiChoiceCallback callback) {
        multiChoice(0, title, items, btPositiveText, btNegativeText, callback);
    }

    @Override public void multiChoice(int iconId, String title, final String[] items, String btPositiveText, String btNegativeText, final MultiChoiceCallback callback) {
        final List<String> chosenList = new ArrayList<>();

        iconId = (0 == iconId ? R.mipmap.toast_ic_default_warning_sharp_white_96 : iconId);
        mBuilder = null;
        mBuilder = new AlertDialog.Builder(mCtx);
        mBuilder.setIcon(iconId).setTitle(title).setMultiChoiceItems(items, new boolean[items.length], new DialogInterface.OnMultiChoiceClickListener() {
            @Override public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    chosenList.add(items[which]);
                    if (null != callback) callback.onItemClick(which, true);
                } else {
                    chosenList.remove(items[which]);
                    if (null != callback) callback.onItemClick(which, false);
                }
            }
        }).setPositiveButton(btPositiveText, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                if (null != callback) callback.onButtonClick(true, chosenList);
            }
        }).setNegativeButton(btNegativeText, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                if (null != callback) callback.onButtonClick(false, chosenList);
            }
        }).show();
    }


    /** 单输入框对话框 */
    @Override public void singleEditText(String title, String content, String btPositiveText, String btNegativeText, EditText et, NotificationCallback callback) {
        singleEditText(title, content, btPositiveText, btNegativeText, et, callback);
    }

    @Override public void singleEditText(int iconId, String title, String content, String btPositiveText, String btNegativeText, EditText et, NotificationCallback callback) {
        iconId = (0 == iconId ? R.mipmap.toast_ic_default_warning_sharp_white_96 : iconId);

        mBuilder = null;
        mBuilder = new AlertDialog.Builder(mCtx);
        mBuilder.setIcon(iconId).setTitle(title).setMessage(content).setView(et).setPositiveButton(btPositiveText, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                if (null != callback) callback.onClick(NotificationPositive);
            }
        }).setNegativeButton(btNegativeText, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                if (null != callback) callback.onClick(NotificationNegative);
            }
        }).show();
    }


    /** 进度对话框 */
    public interface ProgressCallback {
        void onComplete();
    }

    @Override public ProgressDialog progress(String title, String content) {
        return progress(0, title, content, ProgressDialog.STYLE_HORIZONTAL, 100, 1);
    }

    @Override public ProgressDialog progress(int iconId, String title, String content, int Style, int max, final int increment) {
        iconId = (0 == iconId ? R.mipmap.toast_ic_default_warning_sharp_white_96 : iconId);
        mProgressDialog = null;
        mProgressDialog = new ProgressDialog(mCtx);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIcon(iconId);
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(content);
        mProgressDialog.setProgressStyle(Style);
        mProgressDialog.setMax(max);
        mProgressDialog.show();
        return mProgressDialog;
    }


    /** demo-自定义示例对话框 */
    private EditText etPWD1, etPWD2;
    private String pwdStr1, pwdStr2;

    @Override public void customSimple() {
        mBuilder = new AlertDialog.Builder(mCtx);
        final AlertDialog dialog = mBuilder.create();
        View dialogView = View.inflate(mCtx, R.layout.dialog_layout_custom_simple_view, null);
        dialog.setView(dialogView, 0, 0, 0, 0); // 去边距，兼容2.X

        etPWD1 = (EditText) dialogView.findViewById(R.id.et_pwd_1); // 记录两次密码
        etPWD2 = (EditText) dialogView.findViewById(R.id.et_pwd_2);

        dialogView.findViewById(R.id.bt_YES).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                pwdStr1 = etPWD1.getText().toString();
                pwdStr2 = etPWD2.getText().toString();
                if (TextUtils.isEmpty(pwdStr1) || TextUtils.isEmpty(pwdStr2)) {
                    Toast.makeText(mCtx, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (pwdStr1.equals(pwdStr2)) {
                        Toast.makeText(mCtx, "密码设置成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(mCtx, "密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dialogView.findViewById(R.id.bt_NO).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    /**  */
    private void initBuilder() {

    }

    /**  */
    public void release() {
        if (null != mBuilder) mBuilder = null;
        if (null != mCtx) mCtx = null;
    }
}

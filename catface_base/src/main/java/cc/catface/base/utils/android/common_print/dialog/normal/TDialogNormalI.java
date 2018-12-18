package cc.catface.base.utils.android.common_print.dialog.normal;

import android.app.ProgressDialog;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public interface TDialogNormalI {

    /** 通知对话框 */
    void notification(String title, String content, String btPositiveText, String btNegativeText, TDialogNormal.NotificationCallback callback);

    void notification(int iconId, String title, String content, String btPositiveText, String btNegativeText, TDialogNormal.NotificationCallback callback);


    /** 列表对话框 */
    void list(String title, String[] items, TDialogNormal.ListCallback callback);

    void list(int iconId, String title, String[] items, TDialogNormal.ListCallback callback);


    /** 单选对话框 */
    void singleChoice(String title, String[] items, String btPositiveText, String btNegativeText, TDialogNormal.SingleChoiceCallback callback);

    void singleChoice(int iconId, String title, String[] items, String btPositiveText, String btNegativeText, TDialogNormal.SingleChoiceCallback callback);


    /** 复选对话框 */
    void MultiChoice(String title, String[] items, String btPositiveText, String btNegativeText, TDialogNormal.MultiChoiceCallback callback);

    void MultiChoice(int iconId, String title, String[] items, String btPositiveText, String btNegativeText, TDialogNormal.MultiChoiceCallback callback);


    /** 进度对话框 */
    ProgressDialog progress(String title, String content);

    ProgressDialog progress(int iconId, String title, String content, int Style, int max, int increment);


    /** 自定义示例对话框 */
    void customSimple();
}

package cc.catface.ctool.system;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;

import cc.catface.ctool.context.TContext;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TClipboard {

    /**
     * 设置文本可自由复制android:textIsSelectable="true"
     */
    public static void copyText(String label, String text) {
        ClipboardManager cm = (ClipboardManager) TContext.getContext().getSystemService(CLIPBOARD_SERVICE);
        if (null == cm) return;
        ClipData mClipData = ClipData.newPlainText(label, text);
        cm.setPrimaryClip(mClipData);
    }

    public static void copyUrl(String label, String url) {
        ClipboardManager cm = (ClipboardManager) TContext.getContext().getSystemService(CLIPBOARD_SERVICE);
        if (null == cm) return;
        ClipData mClipData = ClipData.newRawUri(label, Uri.parse(url));
        cm.setPrimaryClip(mClipData);
    }

    public static void copyIntent(String label, Intent intent) {
        ClipboardManager cm = (ClipboardManager) TContext.getContext().getSystemService(CLIPBOARD_SERVICE);
        if (null == cm) return;
        ClipData mClipData = ClipData.newIntent(label, intent);
        cm.setPrimaryClip(mClipData);
    }

    /**
     * 获取剪贴板数据
     */
    public static String get() {
        ClipboardManager cm = (ClipboardManager) TContext.getContext().getSystemService(CLIPBOARD_SERVICE);
        if (null == cm) return "";
        ClipData data = cm.getPrimaryClip();
        if (null == data) return "";
        ClipData.Item item = data.getItemAt(0);
        return item.getText().toString();
    }

}

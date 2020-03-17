package cc.catface.ctool.view;

import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.widget.EditText;

import androidx.annotation.NonNull;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TEditText {


    /**
     * 控制只能输入数字和英文大小写
     *
     * @param isShowNumberKeyboard 显示数字/字字母键盘
     * @param editTexts            所有控件
     */
    public static void filterNumberEnChar(boolean isShowNumberKeyboard, EditText... editTexts) {
        if (isShowNumberKeyboard) {
            for (EditText editText : editTexts) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
            }
            return;
        }

        for (EditText editText : editTexts) {
            editText.setKeyListener(new DigitsKeyListener() {
                @Override public int getInputType() {
                    return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL;
                }

                @NonNull @Override protected char[] getAcceptedChars() {
                    return "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
                }
            });
        }
    }

}

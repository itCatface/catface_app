package cc.catface.ctool.view;

import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.widget.EditText;

import androidx.annotation.NonNull;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TEditText {


    /* 数字和大小写字母[isShowNumberKeyboard显示数字/字母键盘] */
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

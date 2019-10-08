package cc.catface.ctool.view.textview;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TTextView {

    public static SpannableString highlight(String string, String keyword, int color) {
        return highlight(string, keyword, color, true);
    }

    public static SpannableString highlight(String string, String keyword, int color, boolean isIgnoreCase) {
        String str = "", key = "";

        if (null != string) {
            str = string;
        }

        if (null != keyword) {
            key = keyword;
        }

        if (isIgnoreCase) {
            str = str.toLowerCase();
            key = key.toLowerCase();
        }

        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(str);

        SpannableString spannableString = new SpannableString(string);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spannableString;
    }

    public static SpannableString highlight(String string, Set<String> keywords, int color) {
        return highlight(string, keywords, color, true);
    }

    public static SpannableString highlight(String string, Set<String> keywords, int color, boolean isIgnoreCase) {
        String str = isIgnoreCase ? string.toLowerCase() : string;
        SpannableString spannableString = new SpannableString(string);
        for (String keyword : keywords) {
            String key = isIgnoreCase ? keyword.toLowerCase() : keyword;

            Pattern pattern = Pattern.compile(key);
            Matcher matcher = pattern.matcher(str);

            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return spannableString;
    }
}
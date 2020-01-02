package cc.catface.ctool.view.textview;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

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


    /** 响应部分区域文本点击事件 */
    public interface Listener {
        void click();
    }

    public static void clickSub(String full, String sub, int color, TextView tv, final Listener listener) {


        final SpannableStringBuilder style = new SpannableStringBuilder();

        // 设置文字
        style.append(full);

        // 设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override public void onClick(@NonNull View widget) {
                if (null != listener) {
                    listener.click();
                }
            }

            @Override public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
                // super.updateDrawState(ds);
            }
        };

        int iStart = full.indexOf(sub);
        int iEnd = iStart + sub.length();

        style.setSpan(clickableSpan, iStart, iEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(style);

        // 设置部分文字颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
        style.setSpan(foregroundColorSpan, iStart, iEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 配置给TextView
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(style);
    }
}
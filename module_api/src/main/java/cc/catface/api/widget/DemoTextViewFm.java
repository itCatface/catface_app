package cc.catface.api.widget;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityTestTextViewBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.system.IInterface.ISystemInterface;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DemoTextViewFm extends LightFm<LightPresenter, ApiActivityTestTextViewBinding> {

    @Override public int layoutId() {
        return R.layout.api_activity_test_text_view;
    }


    @Override protected void initAction() {
        mBinding.etKeyWords.addTextChangedListener((ISystemInterface.TextChangedWatcher) (s, start, before, count) -> handleTextColor());
        mBinding.cbHighlight.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isSensitiveCase = isChecked;
            handleTextColor();
        });
    }


    private void handleTextColor() {
        String keyWords = mBinding.etKeyWords.getText().toString().trim();
        SpannableString spannableString = matcherSearchTitle(Color.RED, getResources().getString(R.string.brbc), keyWords);
        mBinding.tvBrbc.setText(spannableString);
    }

    private boolean isSensitiveCase = false;

    public SpannableString matcherSearchTitle(int color, String content, String keyWords) {
        String string = isSensitiveCase ? content : content.toLowerCase();
        String key = isSensitiveCase ? keyWords : keyWords.toLowerCase();

        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(string);

        SpannableString ss = new SpannableString(content);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }
}

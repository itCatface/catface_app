package cc.catface.api.widget;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityTestTextViewBinding;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.AROUTER.api_test_text_view)
public class TestTextViewActivity extends NormalActivity<ApiActivityTestTextViewBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_test_text_view;
    }


    @Override protected void initAction() {
        RxTextView.textChanges(mBinding.etKeyWords).subscribe(charSequence -> handleTextColor());
        RxCompoundButton.checkedChanges(mBinding.cbHighlight).subscribe(isChecked -> {
            isSensitiveCase = isChecked;
            handleTextColor();
        });
    }

    @Override public void create() {
        title();
    }

    private void title() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.AROUTER.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
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
        while(matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }
}

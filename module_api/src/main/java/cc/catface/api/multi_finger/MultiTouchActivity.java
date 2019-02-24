package cc.catface.api.multi_finger;

import android.app.Dialog;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding.widget.RxTextView;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityMultiFingerBinding;
import cc.catface.api.multi_finger.view.MultiTouchTextView;
import cc.catface.app_base.Const;
import cc.catface.base.core_framework.base_normal.NormalActivity;
import cc.catface.base.utils.AndroidBarUtils;
import rx.functions.Action1;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
@Route(path = Const.AROUTER.api_multi_finger)
public class MultiTouchActivity extends NormalActivity<ApiActivityMultiFingerBinding> {
    @Override public int layoutId() {
        return R.layout.api_activity_multi_finger;
    }

    @Override protected void initAction() {
        RxTextView.textChanges(mBinding.mtetBrbc).subscribe(new Action1<CharSequence>() {
            @Override public void call(CharSequence charSequence) {
                mBinding.mtetBrbc.setSelection(charSequence.toString().length());
            }
        });
    }

    @Override public void create() {
        title();

        AndroidBarUtils.setBarDarkMode(this, false);

        mBinding.mtetBrbc.setCallback(isZoomLarge -> {
            if (isZoomLarge) getDialog().show();
        });
    }


    private void title() {
        mBinding.tfa.setTitle(getIntent().getStringExtra(Const.AROUTER.DEFAULT_STRING_KEY)).setIcon1(R.string.fa_chevron_left);
    }


    private Dialog mDialog;

    private Dialog getDialog() {
        mDialog = new Dialog(this, R.style.dialog_no_title);
        mDialog.setCancelable(true);
        View view = View.inflate(this, R.layout.api_dialog_fullscreen_text_view, null);
        MultiTouchTextView mttv_fullscreen = ((MultiTouchTextView) view.findViewById(R.id.mttv_fullscreen));
        mttv_fullscreen.setText("当前是全屏文本[TextView]\r\n" + getResources().getString(R.string.text_default_long));
        mttv_fullscreen.setCallback(
                isZoomLarge -> {
                    if (!isZoomLarge && null != mDialog && mDialog.isShowing())
                        mDialog.dismiss();
                });

        mDialog.setContentView(view);
        return mDialog;
    }
}

package cc.catface.base.utils.android.common_print.dialog.normal;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

import cc.catface.base.R;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class DialogLoadingFm extends DialogFragment {

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        /* 圆角透明[否则圆角有白边] */
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getDialog().setCanceledOnTouchOutside(true);
        return inflater.inflate(R.layout.dialog_bg_load_data, container);
    }


    @Override public void onStart() {
        super.onStart();

        /* 适应布局宽高 */
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        /* 设置灰色背景阴影透明度[0.0为完全透明] */
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
    }
}
package cc.catface.api.clipboard;


import android.view.View;
import android.widget.Toast;

import cc.catface.api.R;
import cc.catface.api.databinding.ApiFmClipboardBinding;
import cc.catface.base.core_framework.light_mvp.LightFm;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.ctool.system.TClipboard;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ClipboardFm extends LightFm<LightPresenter, ApiFmClipboardBinding> {
    @Override public int layoutId() {
        return R.layout.api_fm_clipboard;
    }

    @Override protected void initAction() {
        mBinding.btCopy1.setOnClickListener(this);
        mBinding.btCopy2.setOnClickListener(this);
        mBinding.btShow.setOnClickListener(this);
    }

    @Override public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btCopy1) {
            TClipboard.copyText(mBinding.etLabel1.getText().toString(), mBinding.etContent1.getText().toString());
        } else if (id == R.id.btCopy2) {
            TClipboard.copyText(mBinding.etLabel2.getText().toString(), mBinding.etContent2.getText().toString());
        } else if (id == R.id.btShow) {
            Toast.makeText(mActivity, "show:" + TClipboard.get(), Toast.LENGTH_SHORT).show();
        }
    }
}

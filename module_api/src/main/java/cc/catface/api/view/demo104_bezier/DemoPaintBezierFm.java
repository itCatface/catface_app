package cc.catface.api.view.demo104_bezier;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import cc.catface.api.R;
import cc.catface.api.databinding.ApiActivityPaintBezierBinding;
import cc.catface.api.view.demo104_bezier.view.ColorPicker;
import cc.catface.api.view.demo104_bezier.view.OnSeekColorListener;
import cc.catface.base.core_framework.base_normal.NormalFragment;


public class DemoPaintBezierFm extends NormalFragment<ApiActivityPaintBezierBinding> implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener, SeekBar.OnSeekBarChangeListener, OnSeekColorListener {

    @Override public int layoutId() {
        return R.layout.api_activity_paint_bezier;
    }

    @Override public void createView() {
        mBinding.llWidth.setVisibility(View.GONE);
        mBinding.rlColor.setVisibility(View.GONE);

        initEvent();
    }

    private void initEvent() {
        mBinding.pbv.setOnTouchListener(this);
        mBinding.btReset.setOnClickListener(this);
        mBinding.btReset.setOnLongClickListener(this);
        mBinding.sbWidth.setOnSeekBarChangeListener(this);
        mBinding.cp.setOnSeekColorListener(this);
    }

    @Override public boolean onTouch(View view, MotionEvent motionEvent) {
        if (R.id.pbv == view.getId()) {

            if (     mBinding.llWidth.getVisibility() == View.VISIBLE)      mBinding.llWidth.setVisibility(View.GONE);
            if (       mBinding.rlColor.getVisibility() == View.VISIBLE)        mBinding.rlColor.setVisibility(View.GONE);
        }
        return false;
    }

    @Override public void onClick(View view) {
        if (R.id.bt_reset == view.getId()) showSettingPop();
    }

    @Override public boolean onLongClick(View view) {
        if (R.id.bt_reset == view.getId()) {
            mBinding.  pbv.reset();
            return true;
        }
        return false;
    }

    @Override public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (R.id.sb_width == seekBar.getId()) mBinding.pbv.setPenWidth(i);
    }

    @Override public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override public void onStopTrackingTouch(SeekBar seekBar) { }

    @Override public void onSeekColorListener(int color) {
        mBinding.pbv.setPenColor(color);
    }


    /** 更多设置弹窗 */
    private PopupWindow mPopup;

    private void showSettingPop() {
        if (null == mPopup) {
            ListView lv = new ListView(mActivity);
            lv.setBackgroundColor(Color.WHITE);

            String penColor = "颜色", penWidth = "粗细", save = "保存";
            String[] tagArr = {penColor, penWidth, save};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_list_item_1, tagArr);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener((adapterView, view1, i, l) -> {
                if (penColor.equals(tagArr[i])) {
                    mBinding.llWidth.setVisibility(View.GONE);
                    mBinding.rlColor.setVisibility(View.VISIBLE);
                } else if (penWidth.equals(tagArr[i])) {
                    mBinding.llWidth.setVisibility(View.VISIBLE);
                    mBinding.rlColor.setVisibility(View.GONE);
                } else if (save.equals(tagArr[i])) {
                    //                    pbv.save("/sdcard/sign_" + System.currentTimeMillis() + ".png", true, 10);
                }
                mPopup.dismiss();
            });

            mPopup = new PopupWindow(lv, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopup.setBackgroundDrawable(new ColorDrawable());
        }

        mPopup.showAsDropDown(  mBinding.btReset,   mBinding.btReset.getWidth() + 15, -  mBinding.btReset.getHeight() * 7);
    }
}

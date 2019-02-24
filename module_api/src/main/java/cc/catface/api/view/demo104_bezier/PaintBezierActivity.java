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
import cc.catface.api.view.demo104_bezier.view.ColorPicker;
import cc.catface.api.view.demo104_bezier.view.OnSeekColorListener;


public class PaintBezierActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener, SeekBar.OnSeekBarChangeListener, OnSeekColorListener {

    private cc.catface.api.view.demo104_bezier.PaintBezierView pbv;
    private Button bt_reset;

    // 粗细
    private LinearLayout ll_width;
    private SeekBar sb_width;
    // 颜色
    private RelativeLayout rl_color;
    private ColorPicker cp;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.api_activity_paint_bezier);
        pbv = (cc.catface.api.view.demo104_bezier.PaintBezierView) findViewById(R.id.pbv);
        bt_reset = (Button) findViewById(R.id.bt_reset);
        ll_width = (LinearLayout) findViewById(R.id.ll_width);
        sb_width = (SeekBar) findViewById(R.id.sb_width);
        rl_color = (RelativeLayout) findViewById(R.id.rl_color);
        cp = (ColorPicker) findViewById(R.id.cp);

        ll_width.setVisibility(View.GONE);
        rl_color.setVisibility(View.GONE);

        initEvent();
    }

    private void initEvent() {
        pbv.setOnTouchListener(this);
        bt_reset.setOnClickListener(this);
        bt_reset.setOnLongClickListener(this);
        sb_width.setOnSeekBarChangeListener(this);
        cp.setOnSeekColorListener(this);
    }

    @Override public boolean onTouch(View view, MotionEvent motionEvent) {
        if (R.id.pbv == view.getId()) {

            if (ll_width.getVisibility() == View.VISIBLE) ll_width.setVisibility(View.GONE);
            if (rl_color.getVisibility() == View.VISIBLE) rl_color.setVisibility(View.GONE);
        }
        return false;
    }

    @Override public void onClick(View view) {
        if (R.id.bt_reset == view.getId()) showSettingPop();
    }

    @Override public boolean onLongClick(View view) {
        if (R.id.bt_reset == view.getId()) {
            pbv.reset();
            return true;
        }
        return false;
    }

    @Override public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (R.id.sb_width == seekBar.getId()) pbv.setPenWidth(i);
    }

    @Override public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override public void onStopTrackingTouch(SeekBar seekBar) { }

    @Override public void onSeekColorListener(int color) {
        pbv.setPenColor(color);
    }


    /** 更多设置弹窗 */
    private PopupWindow mPopup;

    private void showSettingPop() {
        if (null == mPopup) {
            ListView lv = new ListView(this);
            lv.setBackgroundColor(Color.WHITE);

            String penColor = "颜色", penWidth = "粗细", save = "保存";
            String[] tagArr = {penColor, penWidth, save};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tagArr);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener((adapterView, view1, i, l) -> {
                if (penColor.equals(tagArr[i])) {
                    ll_width.setVisibility(View.GONE);
                    rl_color.setVisibility(View.VISIBLE);
                } else if (penWidth.equals(tagArr[i])) {
                    ll_width.setVisibility(View.VISIBLE);
                    rl_color.setVisibility(View.GONE);
                } else if (save.equals(tagArr[i])) {
                    //                    pbv.save("/sdcard/sign_" + System.currentTimeMillis() + ".png", true, 10);
                }
                mPopup.dismiss();
            });

            mPopup = new PopupWindow(lv, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopup.setBackgroundDrawable(new ColorDrawable());
        }

        mPopup.showAsDropDown(bt_reset, bt_reset.getWidth() + 15, -bt_reset.getHeight() * 7);
    }
}

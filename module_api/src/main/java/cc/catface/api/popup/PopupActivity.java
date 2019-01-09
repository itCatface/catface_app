package cc.catface.api.popup;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import cc.catface.api.R;
import cc.catface.base.core_framework.base_normal.NormalBaseActivityID;

@Route(path = "/api/popup")
public class PopupActivity extends NormalBaseActivityID implements View.OnClickListener {

    private Button btnPopDown;
    private CardView cvMain;
    private RelativeLayout rlMain;
    private FloatingActionButton fabStart;
    private FloatingActionButton fadEnd;

    private PopupWindow myPop;
    private View view, picView01, picView02, picView03, picView04;
    private List<View> views;

    @Override public int layoutId() {
        return R.layout.api_activity_popup;
    }

    @Override public void ids() {
        rlMain = findViewById(R.id.rl_main);
        Button btnPopPicSelect = findViewById(R.id.btn_pop_pic_select);
        Button btnPopQq = findViewById(R.id.btn_pop_qq);
        Button btnPagerCenter = findViewById(R.id.btn_pager_center);
        btnPopDown = findViewById(R.id.btn_pop_down);
        fabStart = findViewById(R.id.flb_pop_start);
        fadEnd = findViewById(R.id.flb_pop_end);
        cvMain = findViewById(R.id.cv_pop);

        btnPopPicSelect.setOnClickListener(this);
        btnPopQq.setOnClickListener(this);
        btnPagerCenter.setOnClickListener(this);
        fabStart.setOnClickListener(this);
        fadEnd.setOnClickListener(this);
        btnPopDown.setOnClickListener(this);
    }


    @Override public void create() {

    }


    @Override public void onClick(View view) {
        if (R.id.btn_pop_pic_select == view.getId()) {
            showPicSelect();
        } else if (R.id.btn_pop_qq == view.getId()) {
            showQq();
        } else if (R.id.btn_pager_center == view.getId()) {
            showPager();
        } else if (R.id.btn_pop_down == view.getId()) {
            showDown();
        } else if (R.id.ll_pic == view.getId() || R.id.btn_pic_cancel == view.getId()) {
            Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
            myPop.dismiss();
        } else if (R.id.btn_pic_camera == view.getId()) {
            Toast.makeText(this, "相机拍照", Toast.LENGTH_SHORT).show();
            myPop.dismiss();
        } else if (R.id.btn_pic_photo == view.getId()) {
            Toast.makeText(this, "相册选择", Toast.LENGTH_SHORT).show();
            myPop.dismiss();
        } else if (R.id.tv_be_top == view.getId()) {
            Toast.makeText(this, "置顶成功", Toast.LENGTH_SHORT).show();
            myPop.dismiss();
        } else if (R.id.tv_delete == view.getId()) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
            myPop.dismiss();
        } else if (R.id.flb_pop_start == view.getId()) {
            showStart();
        } else if (R.id.flb_pop_end == view.getId()) {
            showEnd();
        }
    }

    /**
     * 照片选择器
     */
    @SuppressLint("InflateParams") private void showPicSelect() {
        view = LayoutInflater.from(this).inflate(R.layout.api_item_popup_pic_select, null, false);
        LinearLayout llPop = view.findViewById(R.id.ll_pic);
        Button btnCamera = view.findViewById(R.id.btn_pic_camera);
        Button btnPhoto = view.findViewById(R.id.btn_pic_photo);
        Button btnCancel = view.findViewById(R.id.btn_pic_cancel);

        btnCamera.setOnClickListener(this);
        btnPhoto.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        llPop.setOnClickListener(this);

        myPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        myPop.setBackgroundDrawable(new ColorDrawable());
        myPop.showAtLocation(rlMain, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 仿qq 产生水滴按钮
     */
    @SuppressLint("InflateParams") private void showQq() {
        view = LayoutInflater.from(this).inflate(R.layout.api_item_popup_qq, null, false);
        TextView tvTop = view.findViewById(R.id.tv_be_top);
        TextView tvDelete = view.findViewById(R.id.tv_delete);
        tvDelete.setOnClickListener(this);
        tvTop.setOnClickListener(this);

        myPop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myPop.setBackgroundDrawable(new ColorDrawable());
        myPop.setOutsideTouchable(true);
        myPop.getContentView().measure(0, 0);
        myPop.showAsDropDown(cvMain, (cvMain.getWidth() - myPop.getContentView().getMeasuredWidth()) / 2, -(cvMain.getHeight() + myPop.getContentView().getMeasuredHeight()));
    }

    /**
     * 轮播效果
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP) private void showPager() {
        views = new ArrayList<>();
        view = LayoutInflater.from(this).inflate(R.layout.api_item_popup_pager, null, false);
        ViewPager vpPop = view.findViewById(R.id.vp_pop);
        picView01 = LayoutInflater.from(this).inflate(R.layout.api_item_popup_pop_vp_01, null, false);
        picView02 = LayoutInflater.from(this).inflate(R.layout.api_item_popup_pop_vp_02, null, false);
        picView03 = LayoutInflater.from(this).inflate(R.layout.api_item_popup_pop_vp_03, null, false);
        picView04 = LayoutInflater.from(this).inflate(R.layout.api_item_popup_pop_vp_04, null, false);

        views.add(picView01);
        views.add(picView02);
        views.add(picView03);
        views.add(picView04);
        vpPop.setAdapter(new MyPopAdapter());

        myPop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myPop.setOutsideTouchable(true);
        //悬浮效果
        myPop.setElevation(5);
        myPop.setBackgroundDrawable(new ColorDrawable(0x00ffffff));
        myPop.showAtLocation(rlMain, Gravity.CENTER, 0, 0);
    }

    /**
     * 配置  adapter
     */
    class MyPopAdapter extends PagerAdapter {

        @Override public int getCount() {
            return views.size();
        }

        @Override public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull @Override public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(views.get(position));
        }

    }

    /**
     * 向下弹出
     */
    @SuppressLint("InflateParams") private void showDown() {
        view = LayoutInflater.from(this).inflate(R.layout.api_item_popup_anywhere, null, false);
        view.findViewById(R.id.tv_delete).setOnClickListener(this);
        view.findViewById(R.id.tv_be_top).setOnClickListener(this);

        myPop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myPop.setBackgroundDrawable(new ColorDrawable());
        myPop.setOutsideTouchable(true);
        myPop.getContentView().measure(0, 0);
        myPop.showAsDropDown(btnPopDown, -((myPop.getContentView().getMeasuredWidth() - btnPopDown.getWidth()) / 2), 0);
    }

    /**
     * 向左弹出
     */
    @SuppressLint("InflateParams") private void showStart() {
        view = LayoutInflater.from(this).inflate(R.layout.api_item_popup_pop_start, null, false);

        myPop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myPop.setBackgroundDrawable(new ColorDrawable());
        myPop.setOutsideTouchable(true);
        myPop.getContentView().measure(0, 0);
        myPop.showAsDropDown(fabStart, -(myPop.getContentView().getMeasuredWidth()), -(fabStart.getHeight() / 2 + myPop.getContentView().getMeasuredHeight()));
    }


    /**
     * 向右弹出 输入框
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP) @SuppressLint("InflateParams") private void showEnd() {
        view = LayoutInflater.from(this).inflate(R.layout.api_item_popup_end_input, null, false);

        myPop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myPop.setBackgroundDrawable(new ColorDrawable(0x00ffffff));
        myPop.setElevation(10);
        myPop.setOutsideTouchable(true);
        myPop.setFocusable(true);
        myPop.getContentView().measure(0, 0);
        myPop.showAsDropDown(fadEnd, (int) (fadEnd.getWidth() * 1.3), -((fadEnd.getHeight() + myPop.getContentView().getMeasuredHeight()) / 2));
    }


    @Override public void onBackPressed() {
        if (myPop.isShowing()) {
            myPop.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (views != null) {
            views.remove(picView01);
            views.remove(picView02);
            views.remove(picView03);
            views.remove(picView04);
        }
        if (myPop.isShowing()) {
            myPop.dismiss();
        }
    }
}
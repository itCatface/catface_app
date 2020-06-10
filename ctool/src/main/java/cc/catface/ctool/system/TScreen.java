package cc.catface.ctool.system;import android.annotation.SuppressLint;import android.app.Activity;import android.content.Context;import android.graphics.Bitmap;import android.graphics.Bitmap.Config;import android.graphics.Canvas;import android.graphics.Rect;import android.util.DisplayMetrics;import android.util.TypedValue;import android.view.View;import android.view.WindowManager;import android.widget.ListView;import android.widget.ScrollView;import cc.catface.ctool.context.TApp;/** * Created by catfaceWYH --> tel|wechat|qq 130 128 92925 */public class TScreen {    /** px - sp - dp互转 */    public static int dp2px(float dpValue) {        final float scale = TApp.getInstance().getResources().getDisplayMetrics().density;        return (int) (dpValue * scale + 0.5f);    }    public static int px2dp(float pxValue) {        final float scale = TApp.getInstance().getResources().getDisplayMetrics().density;        return (int) (pxValue / scale + 0.5f);    }    public static int sp2px(float spVal) {        DisplayMetrics metrics = TApp.getInstance().getResources().getDisplayMetrics();        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, metrics);    }    public static float px2sp(float pxVal) {        return (pxVal / TApp.getInstance().getResources().getDisplayMetrics().scaledDensity);    }    /** 获取StatusBar/ActionBar高度 */    public static int getStatusBarHeight() {        try {            @SuppressLint("PrivateApi") Class<?> clazz = Class.forName("com.android.internal.R$dimen");            Object object = clazz.newInstance();            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object) + "");            return TApp.getInstance().getResources().getDimensionPixelSize(height);        } catch (Exception e) {            return 0;        }    }    public static int getActionBarHeight() {        TypedValue tv = new TypedValue();        if (TApp.getInstance().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {            return TypedValue.complexToDimensionPixelSize(tv.data, TApp.getInstance().getResources().getDisplayMetrics());        }        return 0;    }    /** 获得屏幕宽/高 */    public static int getScreenWidth() {        WindowManager wm = (WindowManager) TApp.getInstance().getSystemService(Context.WINDOW_SERVICE);        DisplayMetrics outMetrics = new DisplayMetrics();        wm.getDefaultDisplay().getMetrics(outMetrics);        return outMetrics.widthPixels;    }    public static int getScreenHeight() {        WindowManager wm = (WindowManager) TApp.getInstance().getSystemService(Context.WINDOW_SERVICE);        DisplayMetrics outMetrics = new DisplayMetrics();        wm.getDefaultDisplay().getMetrics(outMetrics);        return outMetrics.heightPixels;    }    /** 屏幕截图 */    /* 当前屏幕截图(包含状态栏) */    public static Bitmap screenShotWithStatusBar(Activity activity) {        View view = activity.getWindow().getDecorView();        view.setDrawingCacheEnabled(true);        view.buildDrawingCache();        Bitmap bmp = view.getDrawingCache();        int width = getScreenWidth();        int height = getScreenHeight();        Bitmap bp = Bitmap.createBitmap(bmp, 0, 0, width, height);        view.destroyDrawingCache();        return bp;    }    /* 当前屏幕截图(不包含状状态栏) */    public static Bitmap screenShotWithoutStatusBar(Activity activity) {        View view = activity.getWindow().getDecorView();        view.setDrawingCacheEnabled(true);        view.buildDrawingCache();        Bitmap bmp = view.getDrawingCache();        Rect frame = new Rect();        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);        int statusBarHeight = frame.top;        int width = getScreenWidth();        int height = getScreenHeight();        Bitmap bp = null;        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);        view.destroyDrawingCache();        return bp;    }    /* 普通View截屏 */    public static Bitmap screenShotNormalView(View view) {        view.clearFocus();        view.setPressed(false);        boolean willNotCache = view.willNotCacheDrawing();        view.setWillNotCacheDrawing(false);        int color = view.getDrawingCacheBackgroundColor();        view.setDrawingCacheBackgroundColor(0);        if (color != 0) {            view.destroyDrawingCache();        }        view.buildDrawingCache();        Bitmap cacheBitmap = view.getDrawingCache();        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);        view.destroyDrawingCache();        view.setWillNotCacheDrawing(willNotCache);        view.setDrawingCacheBackgroundColor(color);        return bitmap;    }    /* ScrollView截屏 */    public static Bitmap screenShotScrollView(ScrollView scrollView) {        int h = 0;        Bitmap bitmap;        for (int i = 0; i < scrollView.getChildCount(); i++) {            h += scrollView.getChildAt(i).getHeight();        }        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Config.ARGB_8888);        final Canvas canvas = new Canvas(bitmap);        scrollView.draw(canvas);        return bitmap;    }    /* ListView截屏 */    public static Bitmap screenShotListView(ListView listView) {        int h = 0;        Bitmap bitmap = null;        for (int i = 0; i < listView.getChildCount(); i++) {            h += listView.getChildAt(i).getHeight();        }        bitmap = Bitmap.createBitmap(listView.getWidth(), h, Config.ARGB_8888);        final Canvas canvas = new Canvas(bitmap);        listView.draw(canvas);        return bitmap;    }}
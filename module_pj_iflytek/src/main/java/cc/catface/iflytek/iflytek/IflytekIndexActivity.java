package cc.catface.iflytek.iflytek;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.alibaba.android.arouter.facade.annotation.Route;

import cc.catface.app_base.Const;
import cc.catface.base.core_framework.light_mvp.LightAct;
import cc.catface.base.core_framework.light_mvp.LightPresenter;
import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.iflytek.AiuiActivity;
import cc.catface.iflytek.R;
import cc.catface.iflytek.databinding.ApisIflytekActivityIndexBinding;

@Route(path = Const.ARouter.pj_iflytek_main) public class IflytekIndexActivity extends LightAct<LightPresenter, ApisIflytekActivityIndexBinding> implements OnClickListener {

    private Toast mToast;

    @Override public int layoutId() {
        return R.layout.apis_iflytek_activity_index;
    }

    @Override public void created() {
        initToolBar();

        requestPermissions();

        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        SimpleAdapter listitemAdapter = new SimpleAdapter();
        ((ListView) findViewById(R.id.listview_main)).setAdapter(listitemAdapter);
    }

    private void requestPermissions() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.LOCATION_HARDWARE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_SETTINGS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_CONTACTS}, 0x0010);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        TLog.d("requestcode: " + requestCode);
        for (String permission : permissions) {
            TLog.d(permission);
        }

        for (int result : grantResults) {
            TLog.d(result);
        }
    }


    @Override public void onClick(View view) {
        int tag = Integer.parseInt(view.getTag().toString());
        Intent intent = null;
        switch (tag) {
            case 0:
                // 语音转写
                intent = new Intent(this, IflytekIatActivity.class);
                break;
            case 1:
                // 语法识别
                showTip("请登录：http://www.xfyun.cn/ 下载aiui体验吧！");
                break;
            case 2:
                // 语义理解
                intent = new Intent(this, AiuiActivity.class);
                break;
            case 3:
                // 合成
                intent = new Intent(this, IflytekTtsActivity.class);
                break;
            case 4:
                // 唤醒
                showTip("请登录：http://www.xfyun.cn/ 下载aiui体验吧！");
                break;
            case 5:
                // 声纹
                showTip("请登录：http://www.xfyun.cn/ 下载体验吧！");
                break;
            case 6:
                // 印刷体在线OCR识别
                intent = new Intent(this, OCRPrintWebActivity.class);
                break;
            case 7:
                // 高德地图测试
                intent = new Intent(this, AmapActivity.class);
                break;
            default:
                showTip("此功能将于近期开放，敬请期待。");
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }


    //Menu 列表
    String items[] = {"立刻体验语音听写", "立刻体验语法识别(X)", "立刻体验语义理解", "立刻体验语音合成", "立刻体验语音唤醒(X)", "立刻体验声纹密码(X)", "印刷体在线OCR", "amap定位demo"};

    private class SimpleAdapter extends BaseAdapter {
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                LayoutInflater factory = LayoutInflater.from(IflytekIndexActivity.this);
                View mView = factory.inflate(R.layout.apis_iflytek_item_index_list, null);
                convertView = mView;
            }
            Button btn = (Button) convertView.findViewById(R.id.btn);
            btn.setOnClickListener(IflytekIndexActivity.this);
            btn.setTag(position);
            btn.setText(items[position]);
            return convertView;
        }

        @Override public int getCount() {
            return items.length;
        }

        @Override public Object getItem(int position) {
            return null;
        }

        @Override public long getItemId(int position) {
            return 0;
        }
    }

    private void showTip(final String str) {
        runOnUiThread(new Runnable() {
            @Override public void run() {
                mToast.setText(str);
                mToast.show();
            }
        });
    }


    /**
     * tool bar
     */
    private ActionBar mBar;

    private void initToolBar() {
        Toolbar toolbar = mBinding.iTbIflytek.tbTitle;
        setSupportActionBar(toolbar);
        mBar = getSupportActionBar();
        if (null != mBar) {
            mBar.setDisplayShowHomeEnabled(true);
            mBar.setTitle(mTitle);
        }
        toolbar.setNavigationIcon(R.mipmap.flaticon_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private String mTitle = "讯飞能力示例", mNormalTitle = "";

    private void updateToolBar() {
        if (null != mBar) {
            mBar.setTitle(mTitle);
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_menu, menu);
        menu.findItem(R.id.menu_search).setVisible(false);
        menu.findItem(R.id.menu_normal).setVisible(!TextUtils.isEmpty(mNormalTitle.trim()));
        menu.findItem(R.id.menu_plus_1).setVisible(false);
        menu.findItem(R.id.menu_plus_2).setVisible(false);

        menu.findItem(R.id.menu_normal).setTitle(mNormalTitle);
        menu.findItem(R.id.menu_normal).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}

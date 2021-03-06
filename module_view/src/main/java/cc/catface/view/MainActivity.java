package cc.catface.view;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import cc.catface.view.databinding.ActivityMainBinding;
import viewmodel.AccountLiveData;


/**
 * 本文主要实例用语展示，Android 5.0之后的新的转场动画效果
 * 主要展示的是：分解、滑动、浅入浅出、共享元素动画效果
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityMainBinding mBinding;

    @RequiresApi(api = Build.VERSION_CODES.M) @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /// 获取系统联系人
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }

        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Log.d("catface", "读取联系人：" + displayName + " || " + number);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        ///


        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.btChangeBounds.setOnClickListener(this);
        mBinding.explode.setOnClickListener(this);
        mBinding.slide.setOnClickListener(this);
        mBinding.fade.setOnClickListener(this);
        mBinding.sharedElements1.setOnClickListener(this);
        mBinding.sharedElements2.setOnClickListener(this);
        mBinding.sharedElements4.setOnClickListener(this);

        AccountLiveData.getInstance().observe(this, new Observer<String>() {
            @Override public void onChanged(String s) {
                Log.d("catface", "main activity-->onChanged: " + s);
            }
        });
    }

    @Override public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btChangeBounds) {
            startActivity(new Intent(this, ChangeBoundsActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }

        switch (view.getId()) {


            case R.id.explode://分解
                startActivity(new Intent(this, ExplodeActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.slide://滑动
                startActivity(new Intent(this, SlideActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

                break;
            case R.id.fade://渐入渐出
                startActivity(new Intent(this, FadeActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

                break;
            case R.id.sharedElements1://共享元素 下一个页面的按钮 相互绑定
                startActivity(new Intent(this, SharedElementsActivity.class), ActivityOptions.makeSceneTransitionAnimation(this, view, "myButton1").toBundle());
            case R.id.sharedElements2://共享元素 多个元素
                startActivity(new Intent(this, SharedElementsActivity.class), ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(view, "myButton2"), Pair.create(view, "myButton3")).toBundle());
                break;
            case R.id.sharedElements4://共享元素 单独设置
                startActivity(new Intent(this, SharedElementsActivity.class), ActivityOptions.makeSceneTransitionAnimation(this, view, "myButton4").toBundle());
                break;
        }
    }
}

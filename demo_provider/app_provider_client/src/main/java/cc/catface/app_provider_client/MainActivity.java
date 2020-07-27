package cc.catface.app_provider_client;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.Random;

import cc.catface.app_provider_client.databinding.ActivityMainBinding;
import cc.catface.ctool.system.TLog;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.btFinish.setOnClickListener(v -> finish());
        mBinding.btGetContacts.setOnClickListener(v -> getContacts());

        textContentProvider();
    }

    /**
     * 获取系统联系人信息
     */
    private void getContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
            }
        }

        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    TLog.d("读取联系人：" + displayName + " || " + number);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    /**
     * content provider测试
     */
    private final Uri mUri = Uri.parse("content://cc.catface.app_provider_provider.provider/user");
    private UserProviderObserver mObserver;

    class UserProviderObserver extends ContentObserver {
        public UserProviderObserver(Handler handler) {
            super(handler);
        }


        @Override
        public void onChange(boolean selfChange, Uri uri) {
            Toast.makeText(MainActivity.this, "provider's db has changed: " + uri.toString() + " || " + selfChange, Toast.LENGTH_SHORT).show();
            Cursor cursor = getContentResolver().query(mUri, null, null, null, null);
            if (null == cursor) return;

            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                int sex = cursor.getInt(cursor.getColumnIndex("sex"));
                long createTime = cursor.getLong(cursor.getColumnIndex("create_time"));
                TLog.d(name + "||" + age + "||" + sex + "||" + createTime);
            }
            cursor.close();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(mObserver);
    }


    private void textContentProvider() {
        mObserver = new UserProviderObserver(new Handler());
        getContentResolver().registerContentObserver(mUri, true, mObserver);


        /** content provider增删改差案例 */
        mBinding.btCPInsert.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put("name", "name" + new Random().nextInt(10));
            values.put("age", new Random().nextInt(100));
            values.put("sex", 777);
            values.put("create_time", System.currentTimeMillis());
            getContentResolver().insert(mUri, values);
        });
        mBinding.btCPDelete.setOnClickListener(v -> {
            getContentResolver().delete(mUri, "sex=?", new String[]{"777"});
            getContentResolver().delete(mUri, "sex=?", new String[]{"888"});
        });
        mBinding.btCPUpdate.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put("sex", 888);
            getContentResolver().update(mUri, values, "age>?", new String[]{"50"});
        });
        mBinding.btCPQuery.setOnClickListener(v -> {
            Cursor cursor = getContentResolver().query(mUri, null, null, null, null);
            if (null == cursor) return;

            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                int sex = cursor.getInt(cursor.getColumnIndex("sex"));
                long createTime = cursor.getLong(cursor.getColumnIndex("create_time"));
                TLog.d(name + "||" + age + "||" + sex + "||" + createTime);
            }
            cursor.close();
        });
    }
}

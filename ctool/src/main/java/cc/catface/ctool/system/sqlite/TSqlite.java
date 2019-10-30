package cc.catface.ctool.system.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TSqlite {
    private static final String TAG = "TSqlite";


    /** 检查表中某列是否存在(两种方法) */
    public static boolean isColumnExist1(SQLiteDatabase db, String tableName, String columnName) {
        boolean result = false;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 0", null);
            result = cursor != null && cursor.getColumnIndex(columnName) != -1;
        } catch (Exception e) {
            Log.e(TAG, "TSqlite-->isColumnExist1-->exception: " + e.getMessage());
        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return result;
    }

    public static boolean isColumnExist2(SQLiteDatabase db, String tableName, String columnName) {
        boolean result = false;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("select * from sqlite_master where name = ? and sql like ?", new String[]{tableName, "%" + columnName + "%"});
            result = null != cursor && cursor.moveToFirst();
        } catch (Exception e) {
            Log.e(TAG, "TSqlite-->isColumnExist2-->exception: " + e.getMessage());
        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return result;
    }
}

package cc.catface.app_provider_provider.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cc.catface.app_provider_provider.dao.DBHelper;
import cc.catface.ctool.system.TLog;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;

public class UserProvider extends ContentProvider {

    private static final Pair<String, Integer> TABLE_USER = new Pair<>("user", 0);
    private static final Pair<String, Integer> TABLE_AUDIO = new Pair<>("table_audio", 1);
    private static final Pair<String, Integer> TABLE_VIDEO = new Pair<>("table_video", 2);


    // 主机名
    private static final String AUTHORITY = "cc.catface.app_provider_provider.provider";
    private static final Uri HOST_URI = Uri.parse("content://" + AUTHORITY);

    private static UriMatcher mUriMatcher;

    // 本地匹配规则
    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, TABLE_USER.first, TABLE_USER.second);
        mUriMatcher.addURI(AUTHORITY, TABLE_AUDIO.first, TABLE_AUDIO.second);
        mUriMatcher.addURI(AUTHORITY, TABLE_VIDEO.first, TABLE_VIDEO.second);
    }

    public UserProvider() {
        TLog.d("constructor...");
    }

    @Override
    public boolean onCreate() {
        TLog.d("create...");
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = mUriMatcher.match(uri);
        if (match == TABLE_USER.second) {
            return TABLE_USER.first;
        }
        if (match == TABLE_AUDIO.second) {
            return TABLE_AUDIO.first;
        }
        if (match == TABLE_VIDEO.second) {
            return TABLE_VIDEO.first;
        }
        return null;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long row = DBHelper.getInstance().getOpenHelper().getWritableDatabase().insert(getType(uri), CONFLICT_IGNORE, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, row);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int i = DBHelper.getInstance().getOpenHelper().getWritableDatabase().delete(getType(uri), selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return i;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int i = DBHelper.getInstance().getOpenHelper().getWritableDatabase().update(getType(uri), CONFLICT_IGNORE, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return i;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = DBHelper.getInstance().getOpenHelper().getReadableDatabase().query("select * from user");
        // getContext().getContentResolver().notifyChange(uri, null);
        return cursor;
    }
}

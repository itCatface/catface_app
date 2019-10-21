package cc.catface.ctool.context;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TSP {

    private static final String SP_FILE_NAME = "default_config";    // SharedPreferences文件名
    private static SharedPreferences mSP;


    private static final class Holder {
        private static final TSP instance = new TSP();
    }

    public static TSP getInstance() {
        return Holder.instance;
    }

    private TSP() {
        if (null != mSP) return;
        mSP = TContext.getContext().getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }


    public void setString(String key, String value) {
        mSP.edit().putString(key, value).apply();
    }

    public String getString(String key, String defaultValue) {
        return mSP.getString(key, defaultValue);
    }


    public void setBoolean(String key, boolean value) {
        mSP.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mSP.getBoolean(key, defaultValue);
    }


    public void setInt(String key, int value) {
        mSP.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int defaultValue) {
        return mSP.getInt(key, defaultValue);
    }


    public void setLong(String key, long value) {
        mSP.edit().putLong(key, value).apply();
    }

    public long getLong(String key, long defaultValue) {
        return mSP.getLong(key, defaultValue);
    }


    public void setFloat(String key, float value) {
        mSP.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key, float defaultValue) {
        return mSP.getFloat(key, defaultValue);
    }


    public void removeAt(String key) {
        mSP.edit().remove(key).apply();
    }


    public void clearAll() {
        mSP.edit().clear().apply();
    }


    public Map<String, ?> getAll() {
        return mSP.getAll();
    }
}
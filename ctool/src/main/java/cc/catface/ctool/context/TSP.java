package cc.catface.ctool.context;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TSP {

    private static final String SP_FILE_NAME = "default_config";    // SharedPreferences文件名
    private static SharedPreferences mSP;


    /**
     * 单例
     */
    private static final class Holder {
        private static final TSP instance = new TSP();
    }

    public static TSP getInstance() {
        return Holder.instance;
    }

    private TSP() {
        if (null != mSP) return;
        mSP = TApp.getInstance().getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }


    /**
     * set/get各类型的值
     */
    public void setString(String key, String value) {
        mSP.edit().putString(key, value).apply();
    }

    public String getString(String key, String defaultValue) {
        return mSP.getString(key, defaultValue);
    }

    public String getString(String key) {
        return getString(key, "");
    }


    public void setBoolean(String key, boolean value) {
        mSP.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mSP.getBoolean(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }


    public void setInt(String key, int value) {
        mSP.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int defaultValue) {
        return mSP.getInt(key, defaultValue);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }


    public void setLong(String key, long value) {
        mSP.edit().putLong(key, value).apply();
    }

    public long getLong(String key, long defaultValue) {
        return mSP.getLong(key, defaultValue);
    }

    public long getLong(String key) {
        return getLong(key, 0);
    }


    public void setFloat(String key, float value) {
        mSP.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key, float defaultValue) {
        return mSP.getFloat(key, defaultValue);
    }

    public float getFloat(String key) {
        return getFloat(key, 0);
    }


    public void setStrSet(String key, Set<String> strs) {
        mSP.edit().putStringSet(key, strs).apply();
    }

    public Set<String> getStrSet(String key, Set<String> defaultValue) {
        return mSP.getStringSet(key, defaultValue);
    }

    public Set<String> getStrSet(String key) {
        return getStrSet(key, null);
    }


    /**
     * 某key是否存在
     */
    public boolean containsKey(String key) {
        return mSP.contains(key);
    }


    /**
     * 清除某key键值对
     */
    public void removeAt(String key) {
        mSP.edit().remove(key).apply();
    }


    /**
     * 清除所有键值对
     */
    public void clearAll() {
        mSP.edit().clear().apply();
    }


    /**
     * 获取所有键值对
     */
    public Map<String, ?> getAll() {
        return mSP.getAll();
    }
}
package cc.catface.base.utils.android;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import cc.catface.base.AppBase;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TSP {

    // SharedPreferences文件名
    private static final String SP_NAME = "config";
    private static SharedPreferences sp;


    private static final class Holder {
        private static final TSP instance = new TSP();
    }

    public static TSP getInstance() {
        return Holder.instance;
    }

    private TSP() {
        if (null == sp) {
            sp = AppBase.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
    }


    /**
     * @author WangYehan
     * @desc 存取String值
     */
    public void setString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }


    /**
     * @author WangYehan
     * @desc 存取boolean值
     */
    public void setBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }


    /**
     * @author WangYehan
     * @desc 存取int值
     */
    public void setInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }


    /**
     * @author WangYehan
     * @desc 存取long值
     */
    public void setLong(String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    public long getlong(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }


    /**
     * @author WangYehan
     * @desc 存取float值
     */
    public void setFloat(String key, float value) {
        sp.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }


    /**
     * @author WangYeHan
     * @desc 移除某条数据
     */
    public void removeAt(String key) {
        sp.edit().remove(key).apply();
    }


    /**
     * @author WangYehan
     * @desc 清空SP中所有数据
     */
    public void clearAll() {
        sp.edit().clear().apply();
    }


    /**
     * @author WangYehan
     * @desc 获取所有键值对Map--可自行遍历获取所有key
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }
}
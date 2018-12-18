package cc.catface.base.utils.android;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */

public class SPT {

    // SharedPreferences文件名
    private static final String SP_NAME = "config";
    private static SharedPreferences sp;


    /**
     * @author WangYehan
     * @desc 存取String值
     */
    public static void setString(Context ctx, String key, String value) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).apply();
    }

    public static String getString(Context ctx, String key, String defaultValue) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getString(key, defaultValue);
    }


    /**
     * @author WangYehan
     * @desc 存取boolean值
     */
    public static void setBoolean(Context ctx, String key, boolean value) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context ctx, String key, boolean defaultValue) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defaultValue);
    }


    /**
     * @author WangYehan
     * @desc 存取int值
     */
    public static void setInt(Context ctx, String key, int value) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(Context ctx, String key, int defaultValue) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defaultValue);
    }


    /**
     * @author WangYehan
     * @desc 存取long值
     */
    public static void setLong(Context ctx, String key, long value) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putLong(key, value).apply();
    }

    public static long getlong(Context ctx, String key, long defaultValue) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getLong(key, defaultValue);
    }


    /**
     * @author WangYehan
     * @desc 存取float值
     */
    public static void setFloat(Context ctx, String key, float value) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putFloat(key, value).apply();
    }

    public static float getFloat(Context ctx, String key, float defaultValue) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getFloat(key, defaultValue);
    }


    /**
     * @author WangYeHan
     * @desc 移除某条数据
     */
    public static void removeAt(Context ctx, String key) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().remove(key).apply();
    }


    /**
     * @author WangYehan
     * @desc 清空SP中所有数据
     */
    public static void clearAll(Context ctx) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().clear().apply();
    }


    /**
     * @author WangYehan
     * @desc 获取所有键值对Map--可自行遍历获取所有key
     */
    public static Map<String, ?> getAll(Context ctx) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getAll();
    }
}
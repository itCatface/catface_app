package cc.catface.base.utils.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.util.ArrayList;
import java.util.List;

import cc.catface.base.domain.AppInfo;


/**
 * @author WangYeHan on ${data}
 */
public class TAppInfo {

    /**
     * @author WangYeHan at 2016/1/28-8:35
     * @desc 获取版本名
     */
    public static String getVerName(Context ctx) {
        try {
            return ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @author WangYeHan at 2016/1/28-8:36
     * @desc 获取版本号
     */
    public static int getVerCode(Context ctx) {
        try {
            return ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * @author WangYeHan at 2016/1/28-14:04
     * @desc 获取手机ROM剩余空间
     */
    public static String getRomSpace(Activity ctx) {
        return getAvailSpace(ctx, Environment.getDataDirectory().getAbsolutePath());
    }

    /**
     * @author WangYeHan at 2016/1/28-14:06
     * @desc 获取手机SD卡剩余空间
     */
    public static String getSDSpace(Activity ctx) {
        return getAvailSpace(ctx, Environment.getExternalStorageDirectory().getAbsolutePath());
    }


    /**
     * @author WangYeHan at 2016/1/28-13:59
     * @desc 获取可用空间
     */
    private static String getAvailSpace(Activity ctx, String path) {
        StatFs sf = new StatFs(path);

        long availableBlocks = sf.getAvailableBlocks();
        long blockSize = sf.getBlockSize();

        return Formatter.formatFileSize(ctx, availableBlocks * blockSize);
    }


    /**
     * @author WangYeHan at 2016/1/28-14:21
     * @desc 获取已安装的应用
     */
    public static ArrayList<AppInfo> getInstalledAPP(Context ctx) {

        ArrayList<AppInfo> datas = new ArrayList<>();

        PackageManager pm = ctx.getPackageManager();
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);

        for (PackageInfo packageInfo : installedPackages) {
            String packageName = packageInfo.packageName;

            ApplicationInfo appInfo = packageInfo.applicationInfo;

            Drawable icon = appInfo.loadIcon(pm);
            String label = appInfo.loadLabel(pm).toString();
            int uid = appInfo.uid;

            AppInfo info = new AppInfo();
            info.packageName = packageName;
            info.icon = icon;
            info.label = label;

            int flags = appInfo.flags;
            if ((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) == ApplicationInfo.FLAG_EXTERNAL_STORAGE) {
                info.isInRom = false;
            } else {
                info.isInRom = true;
            }

            if ((flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) {
                info.isUser = false;
            } else {
                info.isUser = true;
            }

            datas.add(info);
        }

        return datas;
    }
}

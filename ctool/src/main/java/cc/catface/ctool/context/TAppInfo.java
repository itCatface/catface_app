package cc.catface.ctool.context;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TAppInfo {

    /* 获取版本名 */
    public static String getVerName() {
        try {
            return TContext.getContext().getPackageManager().getPackageInfo(TContext.getContext().getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* 获取版本号 */
    public static int getVerCode() {
        try {
            return TContext.getContext().getPackageManager().getPackageInfo(TContext.getContext().getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /* 获取手机ROM剩余空间 */
    public static String getRomSpace() {
        return getAvailSpace(Environment.getDataDirectory().getAbsolutePath());
    }

    /* 获取手机SD卡剩余空间 */
    public static String getSDSpace() {
        return getAvailSpace(Environment.getExternalStorageDirectory().getAbsolutePath());
    }


    /* 获取可用空间 */
    private static String getAvailSpace(String path) {
        StatFs sf = new StatFs(path);

        long availableBlocks = sf.getAvailableBlocks();
        long blockSize = sf.getBlockSize();

        return Formatter.formatFileSize(TContext.getContext(), availableBlocks * blockSize);
    }


    /* 获取系统已安装的应用信息 */
    public static ArrayList<AppInfo> getInstalledAPP() {

        ArrayList<AppInfo> datas = new ArrayList<>();

        PackageManager pm = TContext.getContext().getPackageManager();
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
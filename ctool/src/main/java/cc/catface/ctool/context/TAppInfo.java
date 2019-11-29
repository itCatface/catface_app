package cc.catface.ctool.context;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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


    /* 获取设备剩余ROM */
    public static String getROMRemain() {
        return getAvailSpace(Environment.getDataDirectory().getAbsolutePath());
    }

    /* 获取设备剩余SD */
    public static String getSDRemain() {
        return getAvailSpace(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    /* 获取设备剩余RAM[byte] */
    public static String getRAMRemain() {
        ActivityManager am = (ActivityManager) TContext.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
        if (null == am) return "";
        am.getMemoryInfo(outInfo);
        return Formatter.formatFileSize(TContext.getContext(), outInfo.availMem);
    }

    /* 获取设备总RAM[byte] */
    public static String getTotalRAM() {
        //		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //		MemoryInfo outInfo = new MemoryInfo();
        //		am.getMemoryInfo(outInfo);
        //		return outInfo.totalMem;
        FileInputStream fis = null;
        try {
            File file = new File("/proc/meminfo");
            fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            StringBuffer stringBuffer = new StringBuffer();
            for (char c : line.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    stringBuffer.append(c);
                }
            }
            return Formatter.formatFileSize(TContext.getContext(), Long.parseLong(stringBuffer.toString()) * 1024);

        } catch (Exception e) {
            e.printStackTrace();
            return "";

        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /* 获取可用空间 */
    private static String getAvailSpace(String path) {
        StatFs sf = new StatFs(path);

        long availableBlocks = sf.getAvailableBlocks();
        long blockSize = sf.getBlockSize();

        return Formatter.formatFileSize(TContext.getContext(), availableBlocks * blockSize);
    }


    /* 获取设备运行中的进程数量 */
    public static int getRunningProcessCount() {
        ActivityManager am = (ActivityManager) TContext.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        if (null == am) return 0;
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        return infos.size();
    }

    /* 判断服务是否正在运行 */
    public static boolean isServiceRunning(String classname) {
        if (TextUtils.isEmpty(classname)) return false;
        ActivityManager am = (ActivityManager) TContext.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        if (null == am) return false;
        List<ActivityManager.RunningServiceInfo> infos = am.getRunningServices(1000);
        for (ActivityManager.RunningServiceInfo info : infos) {
            String clzName = info.service.getClassName();
            if (classname.equals(clzName)) {
                return true;
            }
        }
        return false;
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
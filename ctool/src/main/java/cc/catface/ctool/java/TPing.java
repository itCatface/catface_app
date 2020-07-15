package cc.catface.ctool.java;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TPing {
    private static final String TAG = "TPing";

    public static TPingResultBean ping(String ip, int pingCount, int pingTimeout) {
        TPingResultBean TPingResultBean = new TPingResultBean();
        TPingResultBean.setIp(ip);
        TPingResultBean.setPingCount(pingCount);
        TPingResultBean.setPingTimeout(pingTimeout);
        TPingResultBean.setResultBuffer(new StringBuffer());

        String line = null;
        Process process = null;
        BufferedReader successReader = null;
        String command = "ping -c " + TPingResultBean.getPingCount() + " -w " + TPingResultBean.getPingTimeout() + " " + TPingResultBean.getIp();
        try {
            process = Runtime.getRuntime().exec(command);
            if (process == null) {
                append(TPingResultBean.getResultBuffer(), "ping fail:process is null.");
                TPingResultBean.setPingTime(null);
                TPingResultBean.setResult(false);
                return TPingResultBean;
            }
            successReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = successReader.readLine()) != null) {
                append(TPingResultBean.getResultBuffer(), line);
                String time;
                if ((time = getTime(line)) != null) {
                    TPingResultBean.setPingTime(time);
                }
            }
            int status = process.waitFor();
            if (status == 0) {
                append(TPingResultBean.getResultBuffer(), "exec cmd success:" + command);
                TPingResultBean.setResult(true);
            } else {
                append(TPingResultBean.getResultBuffer(), "exec cmd fail.");
                TPingResultBean.setPingTime(null);
                TPingResultBean.setResult(false);
            }
            append(TPingResultBean.getResultBuffer(), "exec finished.");
        } catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (InterruptedException e) {
            Log.e(TAG, String.valueOf(e));
        } finally {
            if (process != null) {
                process.destroy();
            }
            if (successReader != null) {
                try {
                    successReader.close();
                } catch (IOException e) {
                    Log.e(TAG, String.valueOf(e));
                }
            }
        }
        Log.i(TAG, TPingResultBean.getResultBuffer().toString());
        return TPingResultBean;
    }

    private static void append(StringBuffer stringBuffer, String text) {
        if (stringBuffer != null) {
            stringBuffer.append(text + "\n");
        }
    }

    private static String getTime(String line) {
        String[] lines = line.split("\n");
        String time = null;
        for (String l : lines) {
            if (!l.contains("time=")) continue;
            int index = l.indexOf("time=");
            time = l.substring(index + "time=".length());
        }
        return time;
    }
}



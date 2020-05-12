package cc.catface.ctool.context.net.livedata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import java.lang.ref.WeakReference;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class NetworkLiveData extends LiveData<NetworkInfo> {


    private final WeakReference<Context> mWeakContext;
    private static NetworkLiveData mNetworkLiveData;
    private NetworkReceiver mReceiver;
    private final IntentFilter mFilter;


    private NetworkLiveData(WeakReference<Context> context) {
        mWeakContext = context;
        mReceiver = new NetworkReceiver();
        mFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    public static NetworkLiveData getInstance(WeakReference<Context> context) {
        if (null == mNetworkLiveData) {
            synchronized (NetworkLiveData.class) {
                if (null == mNetworkLiveData) {
                    mNetworkLiveData = new NetworkLiveData(context);
                }
            }
        }

        return mNetworkLiveData;
    }


    @Override protected void onActive() {
        super.onActive();
        mWeakContext.get().registerReceiver(mReceiver, mFilter);
    }

    @Override protected void onInactive() {
        super.onInactive();
        mWeakContext.get().unregisterReceiver(mReceiver);
    }


    private static class NetworkReceiver extends BroadcastReceiver {
        @Override public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null == manager) return;
            NetworkInfo info = manager.getActiveNetworkInfo();
            getInstance(new WeakReference<>(context)).setValue(info);
        }
    }
}

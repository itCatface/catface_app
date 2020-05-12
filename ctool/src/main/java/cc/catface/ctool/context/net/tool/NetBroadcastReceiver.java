package cc.catface.ctool.context.net.tool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class NetBroadcastReceiver extends BroadcastReceiver {
    private NetEventListener mListener;

    @Override public void onReceive(Context context, Intent intent) {
        if (null == intent || null == intent.getAction()) {
            mListener.onNetChange(NetStateUtil.NETWORK_NONE);
            return;
        }

        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkType = NetStateUtil.getNetWorkType();
            mListener.onNetChange(netWorkType);
        }
    }

    public interface NetEventListener {
        void onNetChange(int netWorkType);
    }

    public void setListener(NetEventListener listener) {
        if (null != listener) this.mListener = listener;
    }
}
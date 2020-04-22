package cc.catface.ctool.system;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TWeakHandler<T extends TWeakHandler.MessageListener> extends Handler {
    private final WeakReference<T> mHandlerReference;

    public TWeakHandler(T owner) {
        mHandlerReference = new WeakReference<>(owner);
    }


    @Override public void handleMessage(@NonNull Message message) {
        final T owner = getOwner();
        if (null != owner) {
            TLog.d("TWeakHandler-->handleMessage-->receive message: " + message.what);
            owner.handleMessage(message);
        }
    }

    public interface MessageListener {
        void handleMessage(Message msg);
    }

    private T getOwner() {
        return this.mHandlerReference.get();
    }

    public void clear() {
        removeCallbacksAndMessages(null);
        mHandlerReference.clear();
    }
}
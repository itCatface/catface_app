package cc.catface.ctool.system;

import android.os.Handler;
import android.os.Message;

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
            owner.handleMessage(message);
        }
    }

    public interface MessageListener {
        void handleMessage(Message msg);
    }

    public T getOwner() {
        return this.mHandlerReference.get();
    }

    public void clear() {
        mHandlerReference.clear();
    }
}
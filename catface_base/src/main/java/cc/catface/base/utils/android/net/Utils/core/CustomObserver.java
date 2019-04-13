package cc.catface.base.utils.android.net.Utils.core;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.reactivex.disposables.Disposable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc resources release
 */
public abstract class CustomObserver<T> extends BaseObserver<T> {

    private boolean isShowDialog;
    private ProgressDialog mProgressDialog;
    private Context mContext;
    private Disposable mDisposable;

    public CustomObserver(Context context) {
        this(context, false);
    }

    public CustomObserver(Context context, Boolean isShowDialog) {
        this.mContext = context;
        this.isShowDialog = isShowDialog;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        this.mDisposable = disposable;
        if (!isConnected(mContext)) {   // no net connected
            if (disposable.isDisposed()) {
                disposable.dispose();
            }
        } else if (mProgressDialog == null && isShowDialog) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("正在请求数据...");
            mProgressDialog.show();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        hidDialog();
        super.onError(e);
    }

    @Override
    public void onComplete() {
        if (mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        hidDialog();
        super.onComplete();
    }

    public void hidDialog() {
        if (null != mProgressDialog && isShowDialog) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = null;
    }


    /* check net connection state[wifi or data traffic] */
    private boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return null != info && info.isConnected();
    }
}


package cc.catface.base.utils.android.net.Utils.core;

import cc.catface.base.utils.android.net.Utils.core.domain.BaseResponse;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * @desc handle the response data unified
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    @Override
    public void onSubscribe(Disposable d) { }

    @Override
    public void onNext(BaseResponse<T> response) {
        if (RetrofitConstant.CODE_SUCCESS == response.getCode()) {
            onSuccess(response.getData());
        } else {
            onFailure(new Throwable("response.code is not success..."), response.getMessage());
        }
    }

    @Override
    public void onComplete() { }

    @Override
    public void onError(Throwable e) {
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }


    public abstract void onSuccess(T result);

    public abstract void onFailure(Throwable e, String errorMsg);
}

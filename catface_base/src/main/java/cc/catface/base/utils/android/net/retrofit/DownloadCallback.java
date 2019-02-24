package cc.catface.base.utils.android.net.retrofit;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class DownloadCallback<T> implements Callback<T> {
    @Override public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(call, response);
        } else {
            onFailure(call, new Throwable(response.message()));
        }
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);

    //用于进度的回调
    public abstract void onLoading(long total, long progress);
}
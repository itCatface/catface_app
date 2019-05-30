package cc.catface.base.utils.android.net.Utils;

import com.google.gson.JsonObject;

import retrofit2.Callback;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public abstract class CustomCallback implements Callback<JsonObject> {

    public long id = System.currentTimeMillis();

    public CustomCallback(long id) {
        this.id = id;
    }
}

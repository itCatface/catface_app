package cc.catface.iflytek;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class IflytekApp extends Application {

    @Override public void onCreate() {
        super.onCreate();

        String param = ("appid=" + getString(R.string.app_id)) + "," + SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_MSC;
        SpeechUtility.createUtility(this, param);
    }
}

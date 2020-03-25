package cc.catface.iflytek;

import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class BaseIflytek {

    public static void init(Context context) {
        String param = ("appid=" + context.getString(R.string.app_id)) + "," + SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_MSC;
        SpeechUtility.createUtility(context, param);
    }

}

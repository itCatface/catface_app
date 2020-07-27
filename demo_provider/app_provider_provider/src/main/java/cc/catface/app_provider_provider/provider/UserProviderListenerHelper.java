package cc.catface.app_provider_provider.provider;

import cc.catface.ctool.system.TLog;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 *
 * 外部辅助设置监听
 */
public class UserProviderListenerHelper {


    /** 单例 */
    private static class holder {
        public static final UserProviderListenerHelper instance = new UserProviderListenerHelper();
    }

    public static UserProviderListenerHelper getInstance() {
        return holder.instance;
    }

    private UserProviderListenerHelper() { }


    /** 监听接口 */
    public interface UserProviderListener {
        void onOption(int optionType, int rowId, String fileId);

        void onError(int code, String desc);
    }

    public UserProviderListener mUserProviderListener;

    /** 需要监听的地方设置监听器 */
    public void setUserProviderListener(UserProviderListener listener) {
        TLog.d(getClass().getSimpleName() + " -- setUserProviderListener --> listener:" + listener);
        mUserProviderListener = listener;
    }

    /** 被监听的地方补充已有监听器的回调 */
    public UserProviderListener getUserProviderListener() {
        TLog.d(getClass().getSimpleName() + " -- getUserProviderListener:" + mUserProviderListener);
        return mUserProviderListener;
    }

}

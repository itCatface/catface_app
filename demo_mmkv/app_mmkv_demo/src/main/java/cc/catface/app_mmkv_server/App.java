package cc.catface.app_mmkv_server;

import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVLogLevel;

import cc.catface.ctool.context.TApp;

public class App extends TApp {

    @Override
    public void onCreate() {
        super.onCreate();

        String mmkvDir = MMKV.initialize("sdcard/mmkv_server/mmkv");
        MMKV.setLogLevel(MMKVLogLevel.LevelDebug);
    }
}

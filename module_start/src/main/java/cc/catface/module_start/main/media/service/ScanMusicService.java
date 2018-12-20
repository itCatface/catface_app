package cc.catface.module_start.main.media.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.List;

import cc.catface.base.utils.android.common_print.log.TLog;
import cc.catface.module_start.main.media.domain.Song;
import cc.catface.module_start.main.media.utils.MusicUtils;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ScanMusicService extends Service {


    @Override public void onDestroy() {
        super.onDestroy();
    }


    @Nullable @Override public IBinder onBind(Intent intent) {
        return new Binder();
    }

    private String mData = "default data...";

    public class Binder extends android.os.Binder {
        public void setData(String data) {
            mData = data;
            TLog.d("root", "ScanMusicService's setData: " + mData);
        }
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override public void onCreate() {
        super.onCreate();
        List<Song> musicData = MusicUtils.getMusicData(this, new MusicUtils.Callback() {
            @Override public void findSong(String songName) {
                TLog.d("root", "已搜到歌曲:" + songName);
            }

            @Override public void finish() {
                TLog.d("root", "歌曲搜索完毕:");
            }
        });
    }


}

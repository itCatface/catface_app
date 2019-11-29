package cc.catface.ctool.context;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TRing {

    /* 提示音播放 */
    public static void playSound(Context ctx, String soundPath, boolean isRepeat) {
        MediaPlayer player;
        try {
            AssetManager manager = ctx.getAssets();
            AssetFileDescriptor fileDescriptor = manager.openFd(soundPath);
            player = new MediaPlayer();
            player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            player.setLooping(isRepeat);
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* 播放res/raw文件夹下的提示音 */
    public static void playRaw(Context ctx, int rawSourceId) {
        Uri notification = Uri.parse("android.resource://" + ctx.getPackageName() + "/" + rawSourceId);
        final Ringtone ringtone = RingtoneManager.getRingtone(ctx, notification);
        ringtone.play();
    }

    public static void playRaw(Context ctx, int rawSourceId, long period) {
        Uri notification = Uri.parse("android.resource://" + ctx.getPackageName() + "/" + rawSourceId);
        final Ringtone ringtone = RingtoneManager.getRingtone(ctx, notification);
        ringtone.setStreamType(AudioManager.STREAM_RING);   // 因为rt.stop()使得MediaPlayer置null，所以要重新创建（具体看源码）
        setRingtoneRepeat(ringtone);
        ringtone.play();
        new Timer().schedule(new TimerTask() {
            @Override public void run() {
                ringtone.stop();
            }
        }, period);
    }


    private static void setRingtoneRepeat(Ringtone ringtone) {
        Class<Ringtone> clazz = Ringtone.class;
        try {
            Field field = clazz.getDeclaredField("mLocalPlayer");
            field.setAccessible(true);
            MediaPlayer target = (MediaPlayer) field.get(ringtone);
            if (null == target) return;
            target.setLooping(true);
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}

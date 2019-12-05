package cc.catface.start.main.media.domain;

public class Song {
    public String singer;   // 歌手
    public String song;     // 歌曲名
    public String path;     // 歌曲的地址
    public int duration;    // 歌曲长度
    public long size;       // 歌曲的大小

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override public String toString() {
        return "Song{" + "singer='" + singer + '\'' + ", song='" + song + '\'' + ", path='" + path + '\'' + ", duration=" + duration + ", size=" + size + '}';
    }
}
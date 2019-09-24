package cc.catface.wechat.engine.domain;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ChatsBean {

    private int groupType;

    private String avatar;
    private String name;
    private String content;
    private String dt;
    private boolean isMute;

    public ChatsBean(int groupType, String avatar, String name, String content, String dt, boolean isMute) {
        this.groupType = groupType;
        this.avatar = avatar;
        this.name = name;
        this.content = content;
        this.dt = dt;
        this.isMute = isMute;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public boolean isMute() {
        return isMute;
    }

    public void setMute(boolean mute) {
        isMute = mute;
    }
}

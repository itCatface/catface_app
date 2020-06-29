package cc.catface.aidls.login;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class SendBean implements Parcelable {

    private String code;
    private String desc;

    protected SendBean() { }

    public SendBean(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(desc);
    }

    public void readFromParcel(Parcel source) {
        code = source.readString();
        desc = source.readString();
    }

    public SendBean(Parcel in) {
        code = in.readString();
        desc = in.readString();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static final Creator<SendBean> CREATOR = new Creator<SendBean>() {
        @Override
        public SendBean createFromParcel(Parcel in) {
            return new SendBean(in);
        }

        @Override
        public SendBean[] newArray(int size) {
            return new SendBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "BaseResponse{" + "code='" + code + '\'' + ", desc='" + desc + '\'' + '}';
    }
}

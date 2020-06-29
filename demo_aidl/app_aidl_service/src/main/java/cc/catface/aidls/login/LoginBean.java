package cc.catface.aidls.login;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * {"biz":{"lastLoginTime":1593349277744,"userInfo":{"gender":"0","phone":"13012892925","userId":2090983969115105},"isReg":0,"logErrTimes":0,"sessionId":"202093349277732500"},"code":"000000","desc":"成功"}
 */
public class LoginBean implements Parcelable {

    private String code;
    private String desc;
    private Biz biz;

    public LoginBean() { }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(desc);
        dest.writeParcelable(biz, flags);
    }

    public void readFromParcel(Parcel source) {
        code = source.readString();
        desc = source.readString();
        biz = source.readParcelable(Biz.class.getClassLoader());
    }

    protected LoginBean(Parcel in) {
        code = in.readString();
        desc = in.readString();
        biz = in.readParcelable(Biz.class.getClassLoader());
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

    public Biz getBiz() {
        return biz;
    }

    public void setBiz(Biz biz) {
        this.biz = biz;
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel in) {
            return new LoginBean(in);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public static class Biz implements Parcelable {

        private long lastLoginTime;
        private UserInfo userInfo;
        private int isReg;
        private int logErrTimes;
        private String sessionId;

        public Biz() { }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(lastLoginTime);
            dest.writeParcelable(userInfo, flags);
            dest.writeInt(isReg);
            dest.writeInt(logErrTimes);
            dest.writeString(sessionId);
        }

        public void readFromParcel(Parcel source) {
            lastLoginTime = source.readLong();
            userInfo = source.readParcelable(UserInfo.class.getClassLoader());
            isReg = source.readInt();
            logErrTimes = source.readInt();
            sessionId = source.readString();
        }

        protected Biz(Parcel in) {
            lastLoginTime = in.readLong();
            userInfo = in.readParcelable(UserInfo.class.getClassLoader());
            isReg = in.readInt();
            logErrTimes = in.readInt();
            sessionId = in.readString();
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setIsReg(int isReg) {
            this.isReg = isReg;
        }

        public int getIsReg() {
            return isReg;
        }

        public void setLogErrTimes(int logErrTimes) {
            this.logErrTimes = logErrTimes;
        }

        public int getLogErrTimes() {
            return logErrTimes;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getSessionId() {
            return sessionId;
        }

        public static final Creator<Biz> CREATOR = new Creator<Biz>() {
            @Override
            public Biz createFromParcel(Parcel in) {
                return new Biz(in);
            }

            @Override
            public Biz[] newArray(int size) {
                return new Biz[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }


        public static class UserInfo implements Parcelable {

            private String gender;
            private String phone;
            private long userId;

            public UserInfo() { }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(gender);
                dest.writeString(phone);
                dest.writeLong(userId);
            }

            public void readFromParcel(Parcel source) {
                gender = source.readString();
                phone = source.readString();
                userId = source.readLong();
            }

            protected UserInfo(Parcel in) {
                gender = in.readString();
                phone = in.readString();
                userId = in.readLong();
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getGender() {
                return gender;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPhone() {
                return phone;
            }

            public void setUserId(long userId) {
                this.userId = userId;
            }

            public long getUserId() {
                return userId;
            }

            public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
                @Override
                public UserInfo createFromParcel(Parcel in) {
                    return new UserInfo(in);
                }

                @Override
                public UserInfo[] newArray(int size) {
                    return new UserInfo[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }


            @Override
            public String toString() {
                return "UserInfo{" + "gender='" + gender + '\'' + ", phone='" + phone + '\'' + ", userId=" + userId + '}';
            }
        }

        @Override
        public String toString() {
            return "Biz{" + "lastLoginTime=" + lastLoginTime + ", userInfo=" + userInfo + ", isReg=" + isReg + ", logErrTimes=" + logErrTimes + ", sessionId='" + sessionId + '\'' + '}';
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" + "code='" + code + '\'' + ", desc='" + desc + '\'' + ", biz=" + biz + '}';
    }
}
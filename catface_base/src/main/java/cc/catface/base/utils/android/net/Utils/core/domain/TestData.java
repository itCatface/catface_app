package cc.catface.base.utils.android.net.Utils.core.domain;

import java.util.List;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */

public class TestData {

    @Override public String toString() {
        return "TestData{" +
                "userInfo=" + userInfo +
                ", password='" + password + '\'' +
                ", busiInfo=" + busiInfo +
                ", username='" + username + '\'' +
                '}';
    }

    private UserInfo userInfo;
    private String password;
    private List<BusiInfo> busiInfo;
    private String username;

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setBusiInfo(List<BusiInfo> busiInfo) {
        this.busiInfo = busiInfo;
    }

    public List<BusiInfo> getBusiInfo() {
        return busiInfo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


    public class UserInfo {

        @Override public String toString() {
            return "UserInfo{" +
                    "opId='" + opId + '\'' +
                    ", rsp='" + rsp + '\'' +
                    ", orgId='" + orgId + '\'' +
                    '}';
        }

        private String opId;
        private String rsp;
        private String orgId;

        public void setOpId(String opId) {
            this.opId = opId;
        }

        public String getOpId() {
            return opId;
        }

        public void setRsp(String rsp) {
            this.rsp = rsp;
        }

        public String getRsp() {
            return rsp;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getOrgId() {
            return orgId;
        }

    }

    public class BusiInfo {
        @Override public String toString() {
            return "BusiInfo{" +
                    "busiName='" + busiName + '\'' +
                    ", busiId='" + busiId + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }

        private String busiName;
        private String busiId;
        private String id;

        public void setBusiName(String busiName) {
            this.busiName = busiName;
        }

        public String getBusiName() {
            return busiName;
        }

        public void setBusiId(String busiId) {
            this.busiId = busiId;
        }

        public String getBusiId() {
            return busiId;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

    }
}

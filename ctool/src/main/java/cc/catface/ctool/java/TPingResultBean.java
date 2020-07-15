package cc.catface.ctool.java;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 * -
 * 封装ping操作结果信息的实体类
 */
public class TPingResultBean {

    private String ip;          // ping的目标ip
    private int pingCount;      // ping次数
    private int pingTimeout;    // ping超时
    private StringBuffer resultBuffer;  // ping操作信息
    private String pingTime;    // ping耗时
    private boolean result;     // 是否ping通

    public String getPingTime() {
        return pingTime;
    }

    public void setPingTime(String pingTime) {
        this.pingTime = pingTime;
    }

    public StringBuffer getResultBuffer() {
        return resultBuffer;
    }

    public void setResultBuffer(StringBuffer resultBuffer) {
        this.resultBuffer = resultBuffer;
    }

    public int getPingCount() {
        return pingCount;
    }

    public void setPingCount(int pingCount) {
        this.pingCount = pingCount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getPingTimeout() {
        return pingTimeout;
    }

    public void setPingTimeout(int pingTimeout) {
        this.pingTimeout = pingTimeout;
    }

    @Override
    public String toString() {
        return "PingResultBean{" + "ip='" + ip + '\'' + ", pingCount=" + pingCount + ", pingTimeout=" + pingTimeout + ", pingTime='" + pingTime + '\'' + ", result=" + result + '}';
    }
}
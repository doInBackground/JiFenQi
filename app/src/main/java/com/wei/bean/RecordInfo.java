package com.wei.bean;

/**
 * @Author WCL
 * @Date 2022/5/7 15:16
 * @Version
 * @Description 单句对局记录.
 */
public class RecordInfo {

    private boolean isCountZero;//本局总分数是否为0.
    private String time;//本局对局时间.
    private String log;//本局对局日志.

    public RecordInfo(boolean isCountZero, String time, String log) {
        this.isCountZero = isCountZero;
        this.time = time;
        this.log = log;
    }

    public boolean isCountZero() {
        return isCountZero;
    }

    public void setCountZero(boolean countZero) {
        isCountZero = countZero;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public String toString() {
        return "RecordInfo{" +
                "isCountZero=" + isCountZero +
                ", time='" + time + '\'' +
                ", log='" + log + '\'' +
                '}';
    }
}

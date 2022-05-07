package com.wei.bean;

/**
 * @Author WCL
 * @Date 2019/11/7 16:21
 * @Version 1.0
 * @Description 玩家信息.
 */
public class PlayerInfo {

    private int mNum;//位号(唯一标识)
    private String mName;//名字
    private int mGrade;//当前分数<也有用作记录当局变化分数的地方>


    public PlayerInfo(int num, String name, int grade) {
        mNum = num;
        mName = name;
        mGrade = grade;
    }

    public int getNum() {
        return mNum;
    }

//    public void setNum(int num) {
//        mNum = num;
//    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getGrade() {
        return mGrade;
    }

    public void setGrade(int grade) {
        mGrade = grade;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "mNum=" + mNum +
                ", mName='" + mName + '\'' +
                ", mGrade=" + mGrade +
                '}';
    }
}

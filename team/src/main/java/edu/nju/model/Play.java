package edu.nju.model;

import java.sql.Timestamp;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 21:29 2020/1/8
 */
public class Play extends Entity {

    private String playId;
    private String gameId;
    private Timestamp startTime;  //线下游戏开始时间
    private Timestamp endTime;    //线下游戏结束时间
    private String province;      //省
    private String city;          //市
    private String location;      //具体线下游戏的地址
    private int minPerson;        //游戏可以开始的最小人数
    private int maxPerson;        //参与游戏的最大人数
    private int minAdeptScore;    //游戏参与者的最小熟练分
    private int status;           //游戏的状态，0人数不全未开始，1人数合适未开始，2游戏组局失败，3游戏组局成功

    public Play() {
        super();
    }

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMinPerson() {
        return minPerson;
    }

    public void setMinPerson(int minPerson) {
        this.minPerson = minPerson;
    }

    public int getMaxPerson() {
        return maxPerson;
    }

    public void setMaxPerson(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    public int getMinAdeptScore() {
        return minAdeptScore;
    }

    public void setMinAdeptScore(int minAdeptScore) {
        this.minAdeptScore = minAdeptScore;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

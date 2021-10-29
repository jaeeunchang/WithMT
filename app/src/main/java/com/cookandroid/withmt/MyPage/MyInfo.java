package com.cookandroid.withmt.MyPage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyInfo {

    @SerializedName("id")
    private Integer id;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("gender")
    private Integer gender;

    @SerializedName("age")
    private Integer age;

    @SerializedName("friendship")
    private Integer friendship;

    @SerializedName("climbingMate")
    private Integer climbingMate;

    @SerializedName("climbingLevel")
    private Double climbingLevel;

    @SerializedName("imoji")
    private String imoji;

    @Override
    public String toString() {
        return "GetMyInfo{" +
                "id=" + id +
                "nickname=" + nickname +
                ", gender=" + gender +
                ", age=" + age +
                ", friendship=" + friendship +
                ", climbingMate=" + climbingMate +
                ", climbingLevel=" + climbingLevel +
                ", imoji=" + imoji + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getFriendship() {
        return friendship;
    }

    public void setFriendship(Integer friendship) {
        this.friendship = friendship;
    }

    public Integer getClimbingMate() {
        return climbingMate;
    }

    public void setClimbingMate(Integer climbingMate) {
        this.climbingMate = climbingMate;
    }

    public Double getClimbingLevel() {
        return climbingLevel;
    }

    public void setClimbingLevel(Double climbingLevel) {
        this.climbingLevel = climbingLevel;
    }

    public String getImoji() {
        return imoji;
    }

    public void setImoji(String imoji) {
        this.imoji = imoji;
    }
}
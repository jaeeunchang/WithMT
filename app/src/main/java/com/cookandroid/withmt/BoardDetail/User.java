package com.cookandroid.withmt.BoardDetail;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private Object id;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("imoji")
    private String imoji;
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

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImoji() {
        return imoji;
    }

    public void setImoji(String imoji) {
        this.imoji = imoji;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", imoji='" + imoji + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", friendship=" + friendship +
                ", climbingMate=" + climbingMate +
                ", climbingLevel=" + climbingLevel +
                '}';
    }
}

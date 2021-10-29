package com.cookandroid.withmt.MainPage;

import com.google.gson.annotations.SerializedName;

public class BoardResponse {

    @SerializedName("boardId")
    private Integer boardId;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("imoji")
    private String imoji;
    @SerializedName("title")
    private String title;
    @SerializedName("date")
    private String date;
    @SerializedName("updateTime")
    private String updateTime;
    @SerializedName("member")
    private Integer member;
    @SerializedName("gender")
    private Integer gender;

    public String toString() {
        return "BoardResponse{" +
                ", boardId=" + boardId +
                "nickname=" + nickname +
                ", gender=" + gender +
                ", title=" + title +
                ", date=" + date +
                ", imoji=" + imoji +'}';
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
package com.cookandroid.withmt.BoardDetail;

import com.google.gson.annotations.SerializedName;

public class Board {
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("date")
    private String date;
    @SerializedName("member")
    private Integer member;
    @SerializedName("gender")
    private Integer gender;
    @SerializedName("content")
    private String content;
    @SerializedName("link")
    private String link;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", member=" + member +
                ", gender=" + gender +
                ", content='" + content + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
